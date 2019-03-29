package com.hundsun.accountingsystem.Global.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * <p>
 * Description:格式化时间工具类
 * <p>
 * Company: SMARTLAB
 * 
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

	public static Date getNextWorkDay(Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		@SuppressWarnings("deprecation")
		int today = now.getTime().getDay();// 取得今天的星期值
		if (today == 5) {        //判断是否是周五
			now.roll(Calendar.DAY_OF_YEAR, +3);
		} else {
			now.roll(Calendar.DAY_OF_YEAR, 1);
		}
		return now.getTime();
	}

	public static boolean  isWorkDay (Date date) {
		boolean res = false;
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		@SuppressWarnings("deprecation")
		int today = now.getTime().getDay();// 取得今天的星期值
		if (today != 6 && today != 7) {
			res = true;
		}
		return res;
	}

}
