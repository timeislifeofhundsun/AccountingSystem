package com.hundsun.accountingsystem;


import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.controller.TCjhbbController;
import com.hundsun.accountingsystem.Global.VO.TJyflVO;
import com.hundsun.accountingsystem.Global.mapper.TGhkMapper;
import com.hundsun.accountingsystem.Global.mapper.TJyflMapper;
import com.hundsun.accountingsystem.Global.service.TGhkService;
import com.hundsun.accountingsystem.TGp.service.XgPzbService;
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
		//System.out.println(xgQsbService.insert_xg_qsk("F:\\HUNDSUN\\JYQS\\20180604\\JSMXjsmr1.dbf"));;
		//tGhkService.readGhDataByFile("","F:\\HUNDSUN\\JYQS\\20180531\\SJSMX10531.dbf","");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		System.out.println(xgQsbService.insete_gzzz_qsk(10000,sdf.parse("20180531")));

	}
}
