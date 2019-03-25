package com.hundsun.accountingsystem.TGp.service;

import java.util.Date;

public interface HGQSService {

	/**
	 * 
	* @Description: 红股清算
	* @param  参数说明
	* @return boolean    返回类型
	* @author gaozhen
	 */
	public boolean hgqs(int ztbh,Date ywrq) throws Exception;
	
}
