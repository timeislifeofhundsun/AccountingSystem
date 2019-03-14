package com.hundsun.accountingsystem.gz.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadTxtTest {
	
//	public static void main(String[] args) {
//		try {
//			getBeanByTxt("/Users/gaozhen/Desktop/恒生毕设/接口文件/");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static void getBeanByTxt(String filePath) throws Exception {
		FileInputStream fr = new FileInputStream(filePath);
		InputStreamReader is = new InputStreamReader(fr,"GBK");
		BufferedReader br=new BufferedReader(is);
		String line = "";
		String[] arrs = null;
		while ((line = br.readLine()) != null) {
			arrs = line.split("\\|");
			if(arrs[0].contains("MD")) {
				System.out.println("证券代码:"+arrs[1].trim());
				System.out.println("证券名称:"+arrs[2].trim());
				System.out.println("成交数量:"+arrs[3].trim());
				System.out.println("成交金额:"+arrs[4].trim());
				System.out.println("昨日收盘价:"+arrs[5].trim());
				System.out.println("今日开盘价:"+arrs[6].trim());
				System.out.println("今日最高价:"+arrs[7].trim());
				System.out.println("今日最低价"+arrs[8].trim());
				System.out.println("今日收盘价"+arrs[9].trim());
				System.out.println("---------------");
			}else {
				continue;
			}
		}
		br.close();
		is.close();
		fr.close();
	}

}
