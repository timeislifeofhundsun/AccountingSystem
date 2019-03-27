package com.hundsun.accountingsystem.TGp.service;

import com.alibaba.fastjson.JSONArray;

import java.util.Date;

/**
 * 
* <p>
* Description:股票管理方法定义
* <p>
* Company: 恒生电子 
* @author gaozhen
* @date 2019年3月27日
* @Version 1.1
 */
public interface GPManageService {

	public JSONArray selectQsb(int ztbh, Date ywrq) throws Exception;
	
}
