package com.hundsun.accountingsystem.Global.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TLfjxb;
import com.hundsun.accountingsystem.Global.bean.TPzb;
import com.hundsun.accountingsystem.Global.mapper.TLfjxbMapper;
import com.hundsun.accountingsystem.Global.mapper.TPzbMapper;
import com.hundsun.accountingsystem.Global.service.TSequenceService;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
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

	@Autowired
	private TSequenceService sequenceService;

	@Autowired
	private TPzbMapper pzbMapper;

	/**
	 * 两费计息清算
	 * @param ztbh
	 * @param ywrq
	 * @return
	 * @throws Exception
	 */
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

	@Override
	public boolean lfjxPz(int ztbh, Date ywrq) throws Exception {
		boolean res = false;
		/**
		 * 删除旧的凭证
		 */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh",ztbh));
		assist.setRequires(Assist.andEq("rq",ywrq));
		assist.setRequires(Assist.andEq("extendf",51));
		pzbMapper.deleteTPzb(assist);

		/**
		 * 插入两费凭证
		 */
		List<TPzb> pzbs = new ArrayList<>();
		//待摊信息披露费凭证
		pzbs.addAll(this.insertLfPz(ztbh,ywrq,5101));
		//审计费
		pzbs.addAll(this.insertLfPz(ztbh,ywrq,5102));

		if(pzbs.size()>0){
			pzbMapper.insertTPzbByBatch(pzbs);
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
				amount = Double.valueOf(Integer.valueOf(lfjxb.getXxplf()))/Integer.valueOf(lfjxb.getXxplcs());
			}else if(ywlb==5102){
				amount = Double.valueOf(Integer.valueOf(lfjxb.getSjf()))/Integer.valueOf(lfjxb.getSjcs());
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


	private List<TPzb> insertLfPz(int ztbh, Date ywrq,int ywlb){
		/**
		 * 生成凭证
		 */
		List<TPzb> pzs = new ArrayList<>();

		TQsb para = new TQsb();
		para.setZtbh(ztbh);
		para.setRq(ywrq);
		para.setYwlb(ywlb);
		TQsb qsb = qsbMapper.selectTQsbByObj(para);

		//无数据
		if(qsb==null){
			return pzs;
		}

		String zhaiyao = null;
		TPzb jie = null;
		TPzb dai = null;
		int pzid = sequenceService.getSequenceByName("pz");
		if (ywlb==5101){
			zhaiyao = "["+DateFormatUtil.getStringByDate(ywrq)+"]待摊信息披露费";
			jie = new TPzb(null, "51", null, null, null, null, null //id,extenda=51(类别),extend
					, "借", pzid,null //kjkm
					, ztbh, "其他费用-信息披露费", qsb.getAmount()
					,zhaiyao,ywrq);
			dai = new TPzb(null, "51", null, null, null, null, null //id,extenda=11(类别),extend
					, "借", pzid,null //kjkm
					, ztbh, "待摊费用-待摊信息披露费",qsb.getAmount()
					,zhaiyao,ywrq);
		}else if(ywlb==5102){
			zhaiyao = "["+DateFormatUtil.getStringByDate(ywrq)+"]预提审计费";

			jie = new TPzb(null, "51", null, null, null, null, null //id,extenda=51(类别),extend
					, "借", pzid,null //kjkm
					, ztbh, "其他费用-审计费用", qsb.getAmount()
					,zhaiyao,ywrq);
			dai = new TPzb(null, "51", null, null, null, null, null //id,extenda=11(类别),extend
					, "借", pzid,null //kjkm
					, ztbh, "预提费用-审计费用",qsb.getAmount()
					,zhaiyao,ywrq);
		}else{
			log.info("生成两费计息凭证，参数错误");
		}



		pzs.add(jie);
		pzs.add(dai);
		return pzs;
	}

	
}
