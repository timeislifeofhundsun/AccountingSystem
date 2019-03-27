package com.hundsun.accountingsystem.Global.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TLfjxb;
import com.hundsun.accountingsystem.Global.mapper.TLfjxbMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.service.LfjxQsService;

@Service
public class LfjxQsServiceImpl implements LfjxQsService {

	private static final Logger log = LoggerFactory.getLogger(LfjxQsServiceImpl.class);


	@Autowired
	private TQsbMapper qsbMapper;

	@Autowired
	private TLfjxbMapper lfjxbMapper;

	@Override
	public boolean lfjxQs(int ztbh, Date ywrq) throws Exception {
		boolean res = false;
		List<TQsb> needInsertQsb = new ArrayList<>();
		//1.审计费
		TQsb sjf = this.lfqs(ztbh,ywrq,5101);
		if(sjf!=null){
			needInsertQsb.add(sjf);
		}
		//2.信息披露费
		TQsb plf = this.lfqs(ztbh,ywrq,5102);
		if (plf!=null){
			needInsertQsb.add(plf);
		}
		//插入到数据库中
		if(needInsertQsb.size()>0){
			qsbMapper.insertTQsbByBatch(needInsertQsb);
		}
		res = true;
		return res;
	}

	/**
	 * 计算披露费或者审计费
	 * @param ztbh
	 * @param ywrq
	 * @return
	 * @throws Exception
	 */
	private TQsb lfqs(int ztbh, Date ywrq,int ywlb) throws Exception{
		TQsb qsb = null;
		/**
		 * 根据账套、日期、业务类别查看清算数据
		 */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh",ztbh));
		assist.setRequires(Assist.andEq("rq",ywrq));
		assist.setRequires(Assist.andEq("ywlb",ywlb));

		List<TQsb> qsbs = qsbMapper.selectTQsb(assist);
		if(qsbs.size()>1){
			throw new Exception("今日产生多条两费"+ywlb+"[异常]");
		}else if(qsbs.size()==1){
			//不处理
		}else{
			List<TLfjxb> lfjxbs = lfjxbMapper.selectByExample(null);
			if(lfjxbs.size()!=1){
				log.error("两费计息异常，无法计算披露费");
			}
			TLfjxb lfjxb = lfjxbs.get(0);
			Double amount = null;
			if(ywlb==5101){
				amount = Double.valueOf(lfjxb.getXxplf())/lfjxb.getXxplcs();
			}else if(ywlb==5102){
				amount = Double.valueOf(lfjxb.getSjf())/lfjxb.getSjcs();
			}else{
				log.error("参数ywlb错误");
				return null;
			}
			qsb = new TQsb();
			qsb.setZtbh(ztbh);
			qsb.setRq(ywrq);
			qsb.setYwlb(ywlb);
			qsb.setAmount(amount);
		}
		return qsb;
	}


	
}
