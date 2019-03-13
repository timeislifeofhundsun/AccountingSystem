package com.hundsun.accountingsystem.Global.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
* <p>
* Description:格式化时间工具类 
* <p>
* Company: SMARTLAB 
* @author gaozhen
* @date 2019年3月13日
* @Version 1.1
 */
public class DateFormatUtil {
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String getStringByDate(Date date) {
		return df.format(date);
	}
	
	public static Date getDateByString(String date) throws ParseException {
		return df.parse(date);
	}
	
}
