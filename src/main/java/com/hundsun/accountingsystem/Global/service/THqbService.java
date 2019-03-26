package com.hundsun.accountingsystem.Global.service;

import java.util.Date;
import java.util.List;

import com.hundsun.accountingsystem.Global.bean.THqb;

/**
 * 
* <p>
* Description:定义行情服务接口方法 
* <p>
* Company: 恒生电子
* @author gaozhen
* @date 2019年3月12日
* @Version 1.1
 */
public interface THqbService {
	
	/**
	 * 
	* @Description: 从接口文件读取行情数据（包括上海和深圳的）
	* @param  参数说明 SHFilePath上海行情文件路径 
	*                 SZFilePath深圳行情文件路径
	*                 date 清算日期
	* @return boolean    返回类型
	* @author gaozhen
	 */
	public boolean readHqDataByFile(String SHFilePath,String SZFilePath,Date date);

	public List<THqb> findHqxxByPage(int page, int limit);

	public int selectCounts();

	public List<THqb> findByZqdm(int zqdm);

	public List<THqb> findByDate(String date);

	public void updateHqxx(THqb thqb);
}
