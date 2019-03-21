package com.hundsun.accountingsystem.TGp.service;

import java.util.Date;

/**
 * 
* <p>
* Description:股票凭证服务方法定义
* <p>
* Company: 恒生电子 
* @author gaozhen
* @date 2019年3月20日
* @Version 1.1
 */
public interface GPPZService {
	
	/**
	 * 
	* @Description: 插入股票凭证
	* @param  参数说明
	* @return boolean    返回类型
	* @author gaozhen
	 */
	public boolean insertGPPZ(int ztbh,Date ywrq) throws Exception;
	
}
