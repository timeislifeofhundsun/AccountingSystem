package com.hundsun.accountingsystem.Global.service;

import java.util.Date;

/**
 * 
* <p>
* Description: 计算信息披露费、审计费、银行存款计息
* <p>
* Company: 恒生电子 
* @author gaozhen
* @date 2019年3月27日
* @Version 1.1
 */
public interface LfjxQsService {
	
	/**
	 * 
	* @Description: 两费计息清算
	* @param  参数说明
	* @return boolean    返回类型
	* @author gaozhen
	 */
	public boolean lfjxQs(int ztbh,Date ywrq) throws Exception;


	/**
	 * 两费计息生成凭证
	 * @param ztbh
	 * @param ywrq
	 * @return
	 * @throws Exception
	 */
	public boolean lfjxPz(int ztbh,Date ywrq) throws Exception;
	
}
