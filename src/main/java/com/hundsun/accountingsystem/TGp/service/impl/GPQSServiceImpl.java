package com.hundsun.accountingsystem.TGp.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.bean.TCjhbb;
import com.hundsun.accountingsystem.Global.bean.TCjhbbExample;
import com.hundsun.accountingsystem.Global.bean.TCjhbbExample.Criteria;
import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TCjhbbMapper;
import com.hundsun.accountingsystem.Global.mapper.TJyflMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.TGp.service.GPQSService;

/**
 * 
* <p>
* Description:股票清算服务方法实现
* <p>
* Company: 恒生电子 
* @author gaozhen
* @date 2019年3月19日
* @Version 1.1
 */
@Service
public class GPQSServiceImpl implements GPQSService{
	
	//买入交易费率
	private TJyfl mrjyfl;
	
	//卖出交易费率
	private TJyfl mcjyfl;
	
	@Autowired
	TJyflMapper jyflmapper;
	
	@Autowired
	TCjhbbMapper tCjhbbMapper;
	
	@Autowired
	TQsbMapper tQsbMapper;
	
	
	/**
	 * 
	* @Description: 获取交易费率
	* @param  参数说明
	* @return boolean    返回类型
	* @author gaozhen
	 */
	private boolean getJyfl() {
		boolean res = false;
		this.mrjyfl = jyflmapper.selectByPrimaryKey(1101);
		this.mcjyfl = jyflmapper.selectByPrimaryKey(1102);
		if(this.mrjyfl!=null && this.mcjyfl!=null) {
			res = true;
		}
		return res;
	}	
	
	
	/**
	 * 1.获取交易费率
	 * 2.获取需要清算的数据(账套、时间筛选)
	 * 3.根据买卖方向不同做处理
	 * 4.计算估值增值
	 */
	@Override
	public boolean gpqs(int ztbh, Date ywrq) throws Exception{
		boolean returnData = false;
		/**
		 * 1.获取交易费率
		 */
		boolean res = false;
		res = this.getJyfl();
		if(!res) {
			throw new Exception("股票清算-获取交易费率");
		}
		/**
		 * 2.获取需要清算的数据(账套、时间、业务类别筛选)
		 */
		TCjhbbExample  example = new TCjhbbExample();
		Criteria criteria = example.createCriteria();
		criteria.andZtbhEqualTo(ztbh);
		criteria.andYwlbEqualTo(1);
		criteria.andYwrqBetween(ywrq, ywrq);
		List<TCjhbb> tCjhbbs = tCjhbbMapper.selectByExample(example);
		/**
		 * 根据证券类别分类
		 */
		Map<String, List<TCjhbb>> zqdmMap = new HashMap<>();
		for (TCjhbb tCjhbb : tCjhbbs) {
			String zqdm = tCjhbb.getZqdm();
			List<TCjhbb> tcjs = zqdmMap.get(zqdm);
			if(tcjs==null) {
				tcjs = new ArrayList<>();
			}
			tcjs.add(tCjhbb);
			zqdmMap.put(zqdm,tcjs);
		}
		
		for(String zqdm:zqdmMap.keySet()) {
			this.gpmr(zqdmMap.get(zqdm));
		}
		
		returnData = true;
		return returnData;
	}
	
	/**
	 * 
	* @Description: 计算经手费、印花税 
	* @param  参数说明
	* @return boolean    返回类型
	* @author gaozhen
	 */
	private boolean gpmr(List<TCjhbb> tCjhbbs) {
		boolean res = false;
		//校验参数
		if(tCjhbbs.size()>0 && tCjhbbs.get(0).getMmfx().equals("B")) {
			double cjje = 0;
			double jsf = 0;
			double ghf = 0;
			double zgf= 0;
			double jyfy = 0;
			Integer cjsl = 0;
			for (TCjhbb tCjhbb : tCjhbbs) {
				double tempCjje = tCjhbb.getCjjg()*tCjhbb.getCjsl();
				cjje=cjje+tempCjje;
				jsf=jsf+tempCjje*this.mrjyfl.getJsfl();
				ghf=ghf+tempCjje*this.mcjyfl.getGh();
				zgf=zgf+tempCjje*this.mcjyfl.getZg();
				jyfy=jyfy+tempCjje*this.mrjyfl.getYj();
				cjsl=cjsl+tCjhbb.getCjsl();
			}
			double cyj = jyfy-jsf-zgf;
			double zqqsk = cjje+jyfy-cyj;
			TCjhbb tempTCjhbb = tCjhbbs.get(0);
			TQsb qsb = new TQsb(null, tempTCjhbb.getZtbh(), tempTCjhbb.getYwrq(), 
					tempTCjhbb.getZqdm(),1101, //ywlb
					"B", cjsl, cjje, null, //印花税
					jsf, ghf, zgf, cyj, null, //费用合计
					null,null, null, null, null, null,//扩展字段
					null, //公允价值变动
					zqqsk,null//差价收入
					, tempTCjhbb.getJysc(), cjje);//成本
			System.out.println(tQsbMapper.insertNonEmptyTQsb(qsb));;
		}
		
		res = true;
		return res;
	}

}
