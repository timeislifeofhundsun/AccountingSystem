package com.hundsun.accountingsystem.gz.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXMLTest {
	
	public void getbeanByXML(String path) throws Exception{
		SAXReader reader = new SAXReader();
		InputStream is = new FileInputStream(path);
		Document doc = reader.read(is);
		//文档root
		Element root = doc.getRootElement();
		//遍历
		@SuppressWarnings("unchecked")
		Iterator<Element> it = root.elementIterator();
		int i = 0;
		while (it.hasNext()) {
			Element e = it.next();// 获取子元素
			// 通过元素对象获取子元素对象
			Element eSecurityID = e.element("SecurityID");
			Element eSecurityType = e.element("SecurityType");
			//昨日收盘价
			Element ePrevClosePx = e.element("PrevClosePx");
			//今日开盘价
			Element eOpenPrice = e.element("OpenPrice");
			//今日收盘价
			Element eclosePx = e.element("ClosePx");
			//成交数量
			Element eTotalVolumeTrade = e.element("TotalVolumeTrade");
			//成交金额
			Element eTotalValueTrade = e.element("TotalValueTrade");
			System.out.println("SecurityID: "+eSecurityID.getText());
			System.out.println("SecurityType: "+eSecurityType.getText());
			System.out.println("昨日收盘价: "+ePrevClosePx.getText());
			System.out.println("今日开盘价: "+eOpenPrice.getText());
			System.out.println("今日收盘价: "+eclosePx.getText());
			System.out.println("成交数量: "+eTotalVolumeTrade.getText());
			System.out.println("成交金额: "+eTotalValueTrade.getText());
			System.out.println("--------------");
			i++;
			if(i>3)
				break;
		}
	}
	
	
//	public static void main(String[] args) {
//		String path ="/Users/gaozhen/Desktop/恒生毕设/接口文件/cashsecurityclosemd_20180530.xml";
//		try {
//			new ReadXMLTest().getbeanByXML(path);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
