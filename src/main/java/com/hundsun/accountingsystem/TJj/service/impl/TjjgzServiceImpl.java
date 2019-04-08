package com.hundsun.accountingsystem.TJj.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.THqb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.bean.TZqxx;
import com.hundsun.accountingsystem.Global.bean.TZqxxExample;
import com.hundsun.accountingsystem.Global.bean.TZqxxExample.Criteria;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.THqbMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.mapper.TZqxxMapper;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.TJj.VO.TjjGzParamsVO;
import com.hundsun.accountingsystem.TJj.service.TjjgzService;

@Service
public class TjjgzServiceImpl implements TjjgzService {

	@Autowired
	TQsbMapper tqsbMapper;
	
	@Autowired
	TCcyebMapper tccyebMapper;
	
	@Autowired
	THqbMapper thqbMapper;
	
	@Autowired
	TZqxxMapper tzqxxMapper;
	
	@Override
	public void countJjgz(String date) throws Exception {
		/*
		 * 点击基金估值按钮后，后台会接收到一个时间数据
		 * 1.根据该时间去清算表中查询是否有当天的估值数据，如果有数据则提示“当天基金已经估值成功，请不要重复估值”，程序结束
		 * 2.如果从清算表中查询不到当天的估值数据，则进行如下操作：
		 * 3.从持仓表中拿出到今天为止的所有非货币基金持仓数据
		 * 4.遍历每一条持仓数据做如下操作：更新持仓的估值增值，并往清算表中插入一条估值数据
		 * */
		//根据ywlb=4203 和 rq = date 去清算表中查询数据，并判断是否有当天的估值数据
		boolean flag = selectTqsb(date);
		if(!flag) {
			throw new Exception("当天基金已经估值成功，请不要重复 估值");
		}
		//从持仓表中拿出到今天为止的所有非货币基金持仓数据
		List<TCcyeb> ccList = selectTccyeb(date);
		//遍历每一条持仓数据做如下操作：更新持仓的估值增值，并往清算表中插入一条估值数据
		boolean flag2 = updateCcyeb(ccList,date);
		if(!flag2) {
			throw new Exception("更新持仓表、清算表时出错，请检查是否已经录入了当天的行情信息");
		}
	}
	
	private boolean updateCcyeb(List<TCcyeb> ccList,String date) {
		if(ccList!=null && ccList.size()>0) {
			//获取当天的收盘价（hqrq=date and zqnm = 4）
			Assist assist = new Assist();
			assist.setRequires(Assist.andEq("hqrq", date));
			assist.setRequires(Assist.andEq("zqnm", 4));
			assist.setRequires(Assist.andEq("cjsl", 1));
			List<THqb> tHqbList = thqbMapper.selectTHqb(assist);
			
			TZqxxExample example = new TZqxxExample();
			Criteria criteria = example.createCriteria();
			criteria.andZqlbEqualTo(4);
			criteria.andFxfsEqualTo(1);
			List<TZqxx> zqxxList = tzqxxMapper.selectByExample(example);
			int length = 0;
			if(zqxxList!=null && zqxxList.size()>0) {
				length = zqxxList.size();
			}
			
			if(tHqbList!=null && tHqbList.size()==length) {
				//遍历循环每一天持仓数据
				for(int i=0;i<ccList.size();i++) {
					//获取持仓数据
					TCcyeb tCcyeb = ccList.get(i);
					//根据持仓数据中的证券代码查出行情信息
					double jrspj = 0.0;
					for(int j = 0;j<tHqbList.size();j++) {
						THqb tHqb = tHqbList.get(j);
						if(tHqb.getZqdm().equals(tCcyeb.getZqdm())) {
							jrspj = tHqb.getJrsp();
							break;
						}
					}
					if(jrspj>0) {
						double gz = (jrspj * tCcyeb.getCysl()) - tCcyeb.getZqcb() - tCcyeb.getLjgz();
						tCcyeb.setLjgz(gz+tCcyeb.getLjgz());
						try {
							tCcyeb.setFsrq(DateFormatUtil.getDateByString(date));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						tccyebMapper.updateTCcyebById(tCcyeb);	
						insertIntoTqsb(tCcyeb,gz);
						
					}					
				}
				
			}else {
				
				return false;
			}

			return true;
		}
		
		return true;
	}
	
	private void insertIntoTqsb(TCcyeb tCcyeb,double gz) {
		TQsb tqsb = new TQsb();
		tqsb.setZtbh(tCcyeb.getZtbh());
		tqsb.setRq(tCcyeb.getFsrq());
		tqsb.setZqcode(tCcyeb.getZqdm());
		tqsb.setYwlb(4203);
		tqsb.setGyjzbd(gz);
		tqsb.setExtendc("404");
		tqsb.setExtendf("0");
		tqsbMapper.insertTQsb(tqsb);
	}
	
	private List<TCcyeb> selectTccyeb(String date){
		TjjGzParamsVO params = new TjjGzParamsVO();
		params.setDate(date);
		params.setFxfs(1);
		params.setZqnm(4);
		
		return tccyebMapper.selectTccyebByTwoTable(params);
	}
	
	
	private boolean selectTqsb(String date){
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ywlb", 4203));
		assist.setRequires(Assist.andEq("rq", date));
		
		List<TQsb> list = tqsbMapper.selectTQsb(assist);
		if(list!=null && list.size()>0){
			return false;
		}
		
		return true;
	}
	

}
