package com.hundsun.accountingsystem.Global.service.impl;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.service.RzqsService;
import com.hundsun.accountingsystem.Global.service.TCjhbbService;
import com.hundsun.accountingsystem.Global.service.TGhkService;
import com.hundsun.accountingsystem.Global.service.THqbService;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.Global.util.FilePathUtil;

@Service
public class RzqsServiceImpl implements RzqsService {
	
	private static final Logger log = LoggerFactory.getLogger(RzqsServiceImpl.class);

	@Autowired
	private THqbService tHqbService;
	
	@Autowired
	private TGhkService tGhkService;
	
	@Autowired
	private TCjhbbService tCjhbbService;
	
	/**
	* @Description: 日终清算
	* 1.读取交易数据
	* 2.读取行情数据
	* 3.进行合笔计算，存储到成交回报表
	* 4.进行清算，存储到清算库
	* @param
	* ztbh 账套编号 ywrq 业务日期
s	* @return boolean    返回类型
	* @author gaozhen
	 * @throws Exception 
	 */
	@Override
	public boolean rzqs(int ztbh, Date ywrq) throws Exception {
		boolean returnData =  false;
		Map<String,String> files = FilePathUtil.
				getFilePathByDate(DateFormatUtil.getStringByDate(ywrq));
		String cash = files.get("cashsecurityclosemd");
		String mkt = files.get("mktdt00");
		String sjsmx =files.get("SJSMX1");
		String gh = files.get("GH32562");
		if(cash==null||mkt==null) {
			throw new Exception("接口文件不全");
		}
		/**
		 * 1.读取交易数据
		 */
		String res = "";
			res	= tGhkService.readGhDataByFile(gh,sjsmx,DateFormatUtil.getStringByDate(ywrq));
		if(res.equals("")) {
			throw new Exception("读取交易文件失败");
		}
		
		/**
		 * 2.读取行情数据
		 */
		boolean resu = false;
		resu = tHqbService.readHqDataByFile(mkt, cash, ywrq);
		if(!resu) {
			throw new Exception("读取行情文件失败");
		}
		
		/**
		 * 3.进行合笔计算，存储到成交回报表
		 */
		resu = tCjhbbService.insertCjhbbByRzqs(ztbh, ywrq);
		if(!resu) {
			throw new Exception("合笔失败");
		}
		
		returnData = true;
		return returnData;
	}

}
