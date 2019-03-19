package com.hundsun.accountingsystem.Global.service;

import java.util.Date;

/**
 * 
* <p>
* Description:日终清算服务方法定义
* <p>
* Company: 恒生电子
* @author gaozhen
* @date 2019年3月13日
* @Version 1.1
 */
public interface RzqsService {
	
	/**
	* @Description: 日终清算
	* @param
	* ztbh 账套编号 ywrq 业务日期
	* @return boolean    返回类型
	* @author gaozhen
	 */
	public boolean rzqs(int ztbh,Date ywrq) throws Exception;
}
