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
import com.hundsun.accountingsystem.TJj.service.TjjFhService;

@Service
public class TjjFhServiceImpl implements TjjFhService{

	@Autowired
	TQsbMapper tqsbMapper;
	
	@Autowired
	TCcyebMapper tccyebMapper;
	
	@Autowired
	THqbMapper thqbMapper;
	
	@Autowired
	TZqxxMapper tzqxxMapper;
	
	@Override
	public void countJjfh(String date) throws Exception {
		/*
		 * 点击基金分红按钮后，后台会接收到一个时间数据
		 * 1.根据该时间去清算表中查询是否有当天的分红数据，如果有数据则提示“当天基金已经分红成功，请不要重复分红”，程序结束
		 * 2.如果从清算表中查询不到当天的估值数据，则进行如下操作：
		 * 3.从持仓表中拿出到今天为止的所有货币基金持仓数据
		 * 4.遍历每一条持仓数据做如下操作：更新持仓的估值增值，并往清算表中插入一条估值数据
		 * */
		
		//根据ywlb = 4103 和date去清算表中查询数据
		boolean flag = selectTqsb(date);
		if(!flag) {
			throw new Exception("当天基金已经分红成功，请不要重复分红");
		}
		
		//从持仓表中拿出到今天为止的所有货币基金持仓数据
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
			assist.setRequires(Assist.andEq("cjsl", 2));
			
			TZqxxExample example = new TZqxxExample();
			Criteria criteria = example.createCriteria();
			criteria.andZqlbEqualTo(4);
			criteria.andFxfsEqualTo(2);
			List<TZqxx> zqxxList = tzqxxMapper.selectByExample(example);
			int length = 0;
			if(zqxxList!=null && zqxxList.size()>0) {
				length = zqxxList.size();
			}
			
			List<THqb> tHqbList = thqbMapper.selectTHqb(assist);
			if(tHqbList!=null && tHqbList.size()==length) {
				//遍历循环每一天持仓数据
				for(int i=0;i<ccList.size();i++) {
					//获取持仓数据
					TCcyeb tCcyeb = ccList.get(i);
					//根据持仓数据中的证券代码查出行情信息
					double cjje = 0.0;
					for(int j = 0;j<tHqbList.size();j++) {
						THqb tHqb = tHqbList.get(j);
						if(tHqb.getZqdm().equals(tCcyeb.getZqdm())) {
							cjje = tHqb.getCjje();
							break;
						}
					}
					/*
					 * 应收红利=round（（当前时刻总成本+当前时刻累计应收股利）/10000*登记日的货币基金万份收益率，2）
					 * */
					
					if(cjje>0) {
						double hl = ((tCcyeb.getZqcb()+tCcyeb.getLjgz())/10000)*cjje;
						tCcyeb.setLjgz(hl+tCcyeb.getLjgz());
						try {
							tCcyeb.setFsrq(DateFormatUtil.getDateByString(date));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						tccyebMapper.updateTCcyebById(tCcyeb);	
						insertIntoTqsb(tCcyeb,hl);
						
					}					
				}
				
			}else {
				
				return false;
			}

			return true;
		}
		
		return true;
	}
	
	private void insertIntoTqsb(TCcyeb tCcyeb,double hl) {
		TQsb tqsb = new TQsb();
		tqsb.setZtbh(tCcyeb.getZtbh());
		tqsb.setRq(tCcyeb.getFsrq());
		tqsb.setZqcode(tCcyeb.getZqdm());
		tqsb.setYwlb(4103);
		tqsb.setGyjzbd(hl);
		tqsb.setExtendc("403");
		tqsb.setExtendf("0");
		tqsbMapper.insertTQsb(tqsb);
	}
	
	private List<TCcyeb> selectTccyeb(String date){
		TjjGzParamsVO params = new TjjGzParamsVO();
		params.setDate(date);
		params.setFxfs(2);
		params.setZqnm(4);
		
		return tccyebMapper.selectTccyebByTwoTable(params);
	}
	
	private boolean selectTqsb(String date){
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ywlb", 4103));
		assist.setRequires(Assist.andEq("rq", date));
		
		List<TQsb> list = tqsbMapper.selectTQsb(assist);
		if(list!=null && list.size()>0){
			return false;
		}
		
		return true;
	}
	
}
