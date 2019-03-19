package com.hundsun.accountingsystem.gz.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.mapper.TJyflMapper;
import com.hundsun.accountingsystem.gz.service.GPQSService;

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
		 * 2.获取需要清算的数据(账套、时间筛选)
		 */
		
		
		returnData = false;
		return returnData;
	}
	


}
