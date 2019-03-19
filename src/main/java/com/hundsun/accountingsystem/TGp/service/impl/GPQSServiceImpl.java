package com.hundsun.accountingsystem.TGp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hundsun.accountingsystem.Global.bean.TCjhbb;
import com.hundsun.accountingsystem.Global.bean.TCjhbbExample;
import com.hundsun.accountingsystem.Global.bean.TCjhbbExample.Criteria;
import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.mapper.TCjhbbMapper;
import com.hundsun.accountingsystem.Global.mapper.TJyflMapper;
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
public class GPQSServiceImpl implements GPQSService{
	
	//买入交易费率
	private TJyfl mrjyfl;
	
	//卖出交易费率
	private TJyfl mcjyfl;
	
	@Autowired
	TJyflMapper jyflmapper;
	
	@Autowired
	TCjhbbMapper tCjhbbMapper;
	
	
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
		
		
		returnData = false;
		return returnData;
	}
	
	private boolean gpmr(TCjhbb tCjhbb) {
		boolean res = false;
		
		
		res = true;
		return res;
	}

}
