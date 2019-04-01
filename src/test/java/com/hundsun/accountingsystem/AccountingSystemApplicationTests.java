package com.hundsun.accountingsystem;


import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.controller.TCjhbbController;
import com.hundsun.accountingsystem.Global.VO.TJyflVO;
import com.hundsun.accountingsystem.Global.mapper.TGhkMapper;
import com.hundsun.accountingsystem.Global.mapper.TJyflMapper;
import com.hundsun.accountingsystem.Global.service.TGhkService;
import com.hundsun.accountingsystem.TGp.controller.TQskController;
import com.hundsun.accountingsystem.TGp.service.XgPzbService;
import com.hundsun.accountingsystem.TGp.service.XgQsbService;
import com.hundsun.accountingsystem.TGp.service.impl.XgQsbServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountingSystemApplicationTests {

	@Autowired
	TQskController tQskController;

	@Autowired
	XgPzbService pz2;

	@Autowired
	XgQsbServiceImpl xgQsbService;

	@Autowired
	TGhkMapper tGhkMapper;

	@Autowired
	TGhkService tGhkService;

	@Autowired
	TJyflMapper tJyflMapper;

	@Autowired
	TCjhbbController tcjhbbController;
	
	@Test
	public void contextLoads() throws IOException {
		List<TJyfl> allTJyfl = tJyflMapper.findAllTJyfl();
		TJyflVO layuiJson = new TJyflVO();
		layuiJson.setCode(0);
		layuiJson.setCount(allTJyfl.size());
		layuiJson.setMsg("");
		layuiJson.setData(allTJyfl);
		String jsonString = JSON.toJSONString(layuiJson);
		System.out.println(jsonString);
	}

	@Test
	public void test() throws IOException, ParseException {
		//System.out.println(xgQsbService.insert_xg_qsk("F:\\HUNDSUN\\JYQS\\20180604\\JSMXjsmr1.dbf"));//放入清算库
		//tGhkService.readGhDataByFile("","F:\\HUNDSUN\\JYQS\\20180531\\SJSMX10531.dbf","");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//System.out.println(xgQsbService.insert_gzzz_qsk(10000,sdf.parse("20180606")));//估值增值测试
		//System.out.println(xgQsbService.insert_sclt_qsk("F:\\HUNDSUN\\JYQS\\20180608\\ZQBDjsmr1.dbf"));//上市流通
		System.out.println(xgQsbService.xgqs("F:\\HUNDSUN\\hsdatasource\\2018-06-04\\SJSFX0604.dbf",null,10004,sdf.parse("20180531")));
	}

	@Test
	public void xg_wx() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		TQsb tQsb = new TQsb();
		tQsb.setZtbh(10004).setRq(sdf.parse("20180531")).setZqcode("600570").setExtendc("55").setQuantity(10000);
		//tQskController.xg_wx(tQsb);
		TQsb tQsb_sclt = new TQsb();
		tQsb_sclt.setZtbh(10004).setRq(sdf.parse("20180607")).setZqcode("600570").setExtendc("55").setQuantity(10000);
		tQskController.sclt_wx(tQsb_sclt);
		//xgQsbService.xgqs(null,null,10004,sdf.parse("20180601"));
	}

}
