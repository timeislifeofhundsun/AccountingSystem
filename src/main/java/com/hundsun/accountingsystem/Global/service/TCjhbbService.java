package com.hundsun.accountingsystem.Global.service;

import java.util.Date;

/**
 * 
* <p>
* Description:成交回报表Service接口方法定义 
* <p>
* Company: 恒生电子 
* @author gaozhen
* @date 2019年3月13日
* @Version 1.1
 */
public interface TCjhbbService {

	/**
	 * 
	* @Description: 把过户表进行合笔，插入到成交回报表中
	* @param  参数说明
	* @return boolean    返回类型
	* @author gaozhen
	 */
	public boolean insertCjhbbByRzqs(int ztbh,Date date);
}
