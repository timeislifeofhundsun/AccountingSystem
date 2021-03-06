package com.hundsun.accountingsystem.Global.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

/**
 * 
 * <p>
 * Description:获取接口文件的路径
 * <p>
 * Company: SMARTLAB
 * 
 * @author gaozhen
 * @date 2019年3月13日
 * @Version 1.1
 */
public class FilePathUtil {
	public static Map<String, String> getFilePathByDate(String ywrq) {
		Map<String, String> res = new HashMap<>();
		try {
			Properties pps = new Properties();
			pps.load(new ClassPathResource("application.properties").getInputStream());
			//根目录
			String root = pps.getProperty("dataFileRoot");
			String cash = root+ywrq+"/cashsecurityclosemd_"+ywrq.replaceAll("-", "")+".xml";
			String gh = root+ywrq+"/GH32562.dbf";
			String mkt = root+ywrq+"/mktdt00.txt";
			String temp = ywrq.replaceAll("-", "").substring(4, 8);
			String sjsmx = root+ywrq+"/SJSMX1"+temp+".dbf";
			String jsmx = root+ywrq+"/JSMXjsmr1.dbf";
			String zqbd = root+ywrq+"/ZQBDjsmr1.dbf";
			String sjsfx = root+ywrq+"/SJSFX"+temp+".dbf";
			String sjsjg = root+ywrq+"/SJSJG"+temp+".dbf";
			File ftemp = new File(cash);
			if(ftemp.exists()) {
				res.put("cashsecurityclosemd", cash);
			}else {
				res.put("cashsecurityclosemd", null);
			}
			
			ftemp = new File(gh);
			if(ftemp.exists()) {
				res.put("GH32562", gh);
			}else {
				res.put("GH32562", null);
			}
			
			ftemp = new File(mkt);
			if(ftemp.exists()) {
				res.put("mktdt00", mkt);
			}else {
				res.put("mktdt00", null);
			}
			
			ftemp = new File(sjsmx);
			if(ftemp.exists()) {
				res.put("SJSMX1", sjsmx);
			}else {
				res.put("SJSMX1", null);
			}
			
			ftemp = new File(jsmx);
			if(ftemp.exists()) {
				res.put("JSMX", jsmx);
			}else {
				res.put("JSMX", null);
			}
			
			ftemp = new File(zqbd);
			if(ftemp.exists()) {
				res.put("ZQBD", zqbd);
			}else {
				res.put("ZQBD", null);
			}

			ftemp = new File(sjsjg);
			if(ftemp.exists()) {
				res.put("SJSJG", sjsjg);
			}else {
				res.put("SJSJG", null);
			}

			ftemp = new File(sjsfx);
			if(ftemp.exists()){
				res.put("SJSFX",sjsfx);
			}else{
				res.put("SJSFX",null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
