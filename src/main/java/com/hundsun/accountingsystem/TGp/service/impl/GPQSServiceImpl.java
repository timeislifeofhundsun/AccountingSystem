package com.hundsun.accountingsystem.TGp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TCjhbb;
import com.hundsun.accountingsystem.Global.bean.TCjhbbExample;
import com.hundsun.accountingsystem.Global.bean.TCjhbbExample.Criteria;
import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.TCjhbbMapper;
import com.hundsun.accountingsystem.Global.mapper.TJyflMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
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
	private TJyflMapper jyflmapper;
	
	@Autowired
	private TCjhbbMapper tCjhbbMapper;
	
	@Autowired
	private TQsbMapper tQsbMapper;
	
	@Autowired
	private TCcyebMapper tCcyebMapper;
	
	/**
	 * 1.获取交易费率
	 * 2.获取需要清算的数据(账套、时间筛选)
	 * 3.计算股票买入
	 * 4.计算股票卖出
	 * 5.计算估值增值
	 */
	@Override
	public boolean gpqs(int ztbh, Date ywrq) throws Exception{
		boolean returnData = false;
		
		/**
		 * 1.获取交易费率
		 */
		boolean res = this.getJyfl();
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
		
		res = this.gpmr(zqdmMap, ztbh, ywrq);
		if(!res) {
			throw new Exception("股票清算-股票买入清算失败");
		}
		
		returnData = true;
		return returnData;
	}
	
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
	 * 
	* @Description: 股票买入 
	* @param  参数说明
	* @return boolean    返回类型
	* @author gaozhen
	 */
	private boolean gpmr(Map<String, List<TCjhbb>> zqdmMap,
			int ztbh, Date ywrq) throws Exception {
		boolean res = false;
		List<TQsb> tqsbs = new ArrayList<>();
		for(String zqdm:zqdmMap.keySet()) {
			tqsbs.add(this.gpmr(zqdmMap.get(zqdm)));
		}
		/**
		 * 删除qsb旧数据
		 */
        Assist assist = new Assist();
        assist.setRequires(Assist.andEq("ztbh",ztbh));
        assist.setRequires(Assist.andEq("rq",DateFormatUtil.getStringByDate(ywrq)));
        assist.setRequires(Assist.andEq("ywlb",1101));
        tQsbMapper.deleteTQsb(assist);
        /**
         * 恢复持仓余额表数据
         */
        for (TQsb tQsb : tqsbs) {
        	TCcyeb para = new TCcyeb();
        	para.setZqdm(tQsb.getZqcode());
        	para.setExtenda("1");
			TCcyeb tCcyeb = tCcyebMapper.selectTCcyebByObj(para);
			if(tCcyeb!=null) {
				tCcyeb.setCysl(tCcyeb.getCysl()-tQsb.getQuantity());
				tCcyeb.setZqcb(tCcyeb.getZqcb()-tQsb.getCost());
				tCcyeb.setFsrq(ywrq);
				int effect = tCcyebMapper.updateNonEmptyTCcyebById(tCcyeb);
				if(effect!=1) {
					throw new Exception("股票买入-恢复持仓余额表失败");
				}
			}
        }
		/**
		 * 插入新的qsb数据
		 */
        tQsbMapper.insertTQsbByBatch(tqsbs);
        /**
         * 根据qsb数据插入或者修改持仓余额表
         */
        for (TQsb tQsb : tqsbs) {
        	TCcyeb para = new TCcyeb();
        	para.setZqdm(tQsb.getZqcode());
        	para.setExtenda("1");
			TCcyeb tCcyeb = tCcyebMapper.selectTCcyebByObj(para);
			if(tCcyeb==null) {
				tCcyeb = new TCcyeb();
				tCcyeb.setCysl(tQsb.getQuantity());
				tCcyeb.setExtenda("1");
				tCcyeb.setZqcb(tQsb.getCost());
				tCcyeb.setZqdm(tQsb.getZqcode());
				tCcyeb.setZtbh(ztbh);
				tCcyeb.setFsrq(ywrq);
				int effect = tCcyebMapper.insertNonEmptyTCcyeb(tCcyeb);
				if(effect!=1) {
					throw new Exception("股票买入-插入持仓余额表失败");
				}
			}else {
				tCcyeb.setCysl(tCcyeb.getCysl()+tQsb.getQuantity());
				tCcyeb.setZqcb(tCcyeb.getZqcb()+tQsb.getCost());
				tCcyeb.setFsrq(ywrq);
				int effect = tCcyebMapper.updateNonEmptyTCcyebById(tCcyeb);
				if(effect!=1) {
					throw new Exception("股票买入-修改持仓余额表失败");
				}
			}
		}
        res = true;
		return res;
	} 
	
	
	/**
	 * 
	* @Description: 计算经手费、印花税等
	* @param  参数说明
	* @return boolean    返回类型
	* @author gaozhen
	 */
	private TQsb gpmr(List<TCjhbb> tCjhbbs) {
		TQsb qsb = null;
		//校验参数
		if(tCjhbbs.size()>0 && tCjhbbs.get(0).getMmfx().equals("B")) {
			double cjje = 0;
			double jsf = 0;
			double ghf = 0;
			double zgf= 0;
			double myj = 0;
			Integer cjsl = 0;
			for (TCjhbb tCjhbb : tCjhbbs) {
				double tempCjje = tCjhbb.getCjjg()*tCjhbb.getCjsl();
				cjje=cjje+tempCjje;
				cjsl=cjsl+tCjhbb.getCjsl();
				jsf=new BigDecimal(jsf+tempCjje*this.mrjyfl.getJsfl())
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				ghf=new BigDecimal(ghf+tempCjje*this.mcjyfl.getGh())
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				zgf=new BigDecimal(zgf+tempCjje*this.mcjyfl.getZg())
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				
			}
			myj = new BigDecimal(cjje*this.mcjyfl.getYj())
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double cyj = myj-jsf-zgf;
			double jyfy = jsf+ghf+zgf+cyj;
			double zqqsk = cjje+jyfy-cyj;
			TCjhbb tempTCjhbb = tCjhbbs.get(0);
			/**
			 * 构建qsb对象
			 */
			qsb = new TQsb(null, tempTCjhbb.getZtbh(), tempTCjhbb.getYwrq(), 
					tempTCjhbb.getZqdm(),1101, //ywlb
					"B", cjsl, cjje, null, //印花税
					jsf, ghf, zgf, cyj, jyfy, //交易费用
					null,null, null, null, null, null,//扩展字段
					null, //公允价值变动
					zqqsk,null//差价收入
					, tempTCjhbb.getJysc(), cjje);//成本
		}
		return qsb;
	}
	
	/**
	 * 
	* @Description: 股票买入 
	* @param  参数说明
	* @return boolean    返回类型
	* @author gaozhen
	 */
	private boolean gpmc(Map<String, List<TCjhbb>> zqdmMap,int ztbh, Date ywrq) {
		boolean res = false;
		List<TQsb> tcjhbbList = new ArrayList<>();
		for(String zqdm:zqdmMap.keySet()) {
			tcjhbbList.add(this.gpmc(zqdmMap.get(zqdm)));
		}
		/**
		 * 删除qsb旧数据
		 */
        Assist assist = new Assist();
        assist.setRequires(Assist.andEq("ztbh",ztbh));
        assist.setRequires(Assist.andEq("rq",DateFormatUtil.getStringByDate(ywrq)));
        assist.setRequires(Assist.andEq("ywlb",1102));
        tQsbMapper.deleteTQsb(assist);
		/**
		 * 插入新的qsb数据
		 */
        tQsbMapper.insertTQsbByBatch(tcjhbbList);
        res = true;
		return res;
	} 

	/**
	 * 
	* @Description: 计算经手费、印花税等
	* @param  参数说明
	* @return boolean    返回类型
	* @author gaozhen
	 */
	private TQsb gpmc(List<TCjhbb> tCjhbbs) {
		TQsb qsb = null;
		//校验参数
		if(tCjhbbs.size()>0 && tCjhbbs.get(0).getMmfx().equals("B")) {
			double cjje = 0;
			double jsf = 0;
			double ghf = 0;
			double zgf = 0;
			double yhs = 0;
			double myj = 0;
			Integer cjsl = 0;
			for (TCjhbb tCjhbb : tCjhbbs) {
				double tempCjje = tCjhbb.getCjjg()*tCjhbb.getCjsl();
				cjje=cjje+tempCjje;
				cjsl=cjsl+tCjhbb.getCjsl();
				jsf=new BigDecimal(jsf+tempCjje*this.mrjyfl.getJsfl())
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				ghf=new BigDecimal(ghf+tempCjje*this.mcjyfl.getGh())
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				zgf=new BigDecimal(zgf+tempCjje*this.mcjyfl.getZg())
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				yhs=new BigDecimal(yhs+tempCjje*this.mcjyfl.getYh())
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			myj = new BigDecimal(cjje*this.mcjyfl.getYj())
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double cyj = myj-jsf-zgf;
			double jyfy = jsf+ghf+zgf+yhs+cyj;
			double zqqsk = cjje-jyfy+cyj;
			TCjhbb tempTCjhbb = tCjhbbs.get(0);
			/**
			 * 构建qsb对象
			 */
			qsb = new TQsb(null, tempTCjhbb.getZtbh(), tempTCjhbb.getYwrq(), 
					tempTCjhbb.getZqdm(),1102, //ywlb
					"B", cjsl, cjje, null, //印花税
					jsf, ghf, zgf, cyj, jyfy, //交易费用
					null,null, null, null, null, null,//扩展字段
					null, //公允价值变动
					zqqsk,null//差价收入
					, tempTCjhbb.getJysc(), cjje);//成本
		}
		return qsb;
	}



}
