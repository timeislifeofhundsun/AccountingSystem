package com.hundsun.accountingsystem.Global.service;


import java.text.ParseException;

public interface TGhkService{

/**
 * @Aurhor yangjf25257
 * @MethodName readGhDataByFile
 * @Param [SHFilePath, SZFilePath, date]
 * @Return boolean
 * @Description 读取上海和深圳的交易文件
 **/
	public String readGhDataByFile(String SHFilePath,String SZFilePath,String date) throws ParseException;
}
