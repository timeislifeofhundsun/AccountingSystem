package com.hundsun.accountingsystem.Global.service.serviceImpl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.service.RZQSService;

@Service
public class RZQSServiceImpl implements RZQSService {

	/**
	* @Description: 日终清算
	* 1.读取交易数据
	* 2.读取行情数据
	* 3.进行合笔计算，存储到成交回报表
	* 4.进行清算，存储到清算库
	* 
	* @param  参数说明
	* ztbh 账套编号 ywrq 业务日期
	* 
	* @return boolean    返回类型
	* 
	* @author gaozhen
	 */
	@Override
	public boolean rzqs(int ztbh, Date ywrq) {
		boolean res =  false;
		/**
		 * 1.读取交易数据
		 */
		
		
		
		return res;
	}

}
