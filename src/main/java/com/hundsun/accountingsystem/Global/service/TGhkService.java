package com.hundsun.accountingsystem.Global.service;


public interface TGhkService {

	/**
	 * 
	* @Description: 读取上海和深圳的交易文件
	* @param  参数说明
	* @return boolean    返回类型
	* @author gaozhen
	 */
	public boolean readGhDataByFile(String SHFilePath,String SZFilePath,String date);
}
