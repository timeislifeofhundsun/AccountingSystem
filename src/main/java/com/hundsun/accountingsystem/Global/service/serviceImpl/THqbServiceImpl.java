package com.hundsun.accountingsystem.Global.service.serviceImpl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.Assist.WhereRequire;
import com.hundsun.accountingsystem.Global.bean.THqb;
import com.hundsun.accountingsystem.Global.mapper.THqbMapper;
import com.hundsun.accountingsystem.Global.service.THqbService;

@Service
public class THqbServiceImpl implements THqbService {
	
	private static final Logger log = LoggerFactory.getLogger(THqbServiceImpl.class);
	
	@Autowired
	private THqbMapper tHqbMapper;
	
	@Override
	public boolean readHqDataByFile(String SHFilePath,String SZFilePath,Date date) {
		boolean res = false;
		Assist assist = new Assist();
		WhereRequire<?> require = assist.new WhereRequire<Object>(
			"hqrq = " +"'"+new SimpleDateFormat("yyyy-MM-dd").format(date) +"'", null);
		assist.setRequires(require);
		tHqbMapper.deleteTHqb(assist);
		try {
			List<THqb> tHqbs = this.getbeanByXML(SZFilePath, date);
			List<THqb> temp = this.getBeanByTxt(SHFilePath, date);
			tHqbs.addAll(temp);
			tHqbMapper.insertTHqbByBatch(tHqbs);
			res = true;
		}catch (NullPointerException e) {
			log.error("行情文件可能不存在");
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error("读取行情文件出错");
			log.error(e.getMessage());
		}
		return res;
	}

	private List<THqb> getbeanByXML(String path,Date date) throws Exception{
		//结果集
		List<THqb> tHqbs = new ArrayList<>();
		
		SAXReader reader = new SAXReader();
		InputStream is = new FileInputStream(path);
		Document doc = reader.read(is);
		//文档root
		Element root = doc.getRootElement();
		//遍历
		@SuppressWarnings("unchecked")
		Iterator<Element> it = root.elementIterator();
		while (it.hasNext()) {
			Element e = it.next();// 获取子元素
			// 通过元素对象获取子元素对象
			Element eSecurityID = e.element("SecurityID");
			// 证券名称
			Element eSymbol = e.element("Symbol");
			//昨日收盘价
			Element ePrevClosePx = e.element("PrevClosePx");
			//今日开盘价
			Element eOpenPrice = e.element("OpenPrice");
			//今日收盘价
			Element eclosePx = e.element("ClosePx");
			//成交数量
//			Element eTotalVolumeTrade = e.element("TotalVolumeTrade");
			//成交金额
//			Element eTotalValueTrade = e.element("TotalValueTrade");
			THqb tHqb = new THqb();
			tHqb.setHqrq(date);
			if(!eOpenPrice.getText().equals("")) {
				tHqb.setJrkp(Double.parseDouble(eOpenPrice.getText()));
			}
			if(!eclosePx.getText().equals("")) {
				tHqb.setJrsp(Double.parseDouble(eclosePx.getText()));
			}
			if(!ePrevClosePx.getText().equals("")) {
				tHqb.setZrspj(Double.parseDouble(ePrevClosePx.getText()));
			}
			tHqb.setZqdm(eSecurityID.getText());
			tHqb.setZqmc(eSymbol.getText());
//			tHqb.setZqnm(zqnm)
			tHqbs.add(tHqb);
		}
		return tHqbs;
	}
	
	private List<THqb> getBeanByTxt(String filePath,Date date) throws Exception {
		//结果集
		List<THqb> tHqbs = new ArrayList<>();
		FileInputStream fr = new FileInputStream(filePath);
		InputStreamReader is = new InputStreamReader(fr,"GBK");
		BufferedReader br=new BufferedReader(is);
		String line = "";
		String[] arrs = null;
		while ((line = br.readLine()) != null) {
			arrs = line.split("\\|");
			if(arrs[0].contains("MD")) {
//				System.out.println("成交数量:"+arrs[3].trim());
//				System.out.println("成交金额:"+arrs[4].trim());
				String zqdmStr = arrs[1].trim();
				String zqmcStr = arrs[2].trim();
				String zrspjStr = arrs[5].trim();
				String jrkpj = arrs[6].trim();
				String jrspj = arrs[9].trim();
				THqb tHqb = new THqb();
				tHqb.setHqrq(date);
				if(!jrkpj.equals("")) {
					tHqb.setJrkp(Double.parseDouble(jrkpj));
				}
				if(!jrspj.equals("")) {
					tHqb.setJrsp(Double.parseDouble(jrspj));
				}
				if(!zrspjStr.equals("")) {
					tHqb.setZrspj(Double.parseDouble(zrspjStr));
				}
				tHqb.setZqdm(zqdmStr);
				tHqb.setZqmc(zqmcStr);
				tHqbs.add(tHqb);
			}else {
				continue;
			}
		}
		br.close();
		is.close();
		fr.close();
		return tHqbs;
	}
	
}
