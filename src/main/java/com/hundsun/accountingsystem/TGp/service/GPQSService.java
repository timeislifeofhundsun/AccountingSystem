package com.hundsun.accountingsystem.TGp.service;

import java.util.Date;

/**
 * 
* <p>
* Description:股票清算方法定义
* <p>
* Company: SMARTLAB 
* @author gaozhen
* @date 2019年3月19日
* @Version 1.1
 */
public interface GPQSService {
	
	/**
	 * 
	* @Description: 股票清算(包括计算股票买入，股票卖出，股票估值增值)
	* @param  参数说明
	* @return boolean    返回类型
	* @author gaozhen
	 */
	public boolean gpqs(int ztbh,Date ywrq) throws Exception;
}
