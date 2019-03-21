package com.hundsun.accountingsystem;


import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.controller.TCjhbbController;
import com.hundsun.accountingsystem.Global.VO.TJyflVO;
import com.hundsun.accountingsystem.Global.mapper.TGhkMapper;
import com.hundsun.accountingsystem.Global.mapper.TJyflMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.util.FileParsing;
import com.hundsun.accountingsystem.TGp.service.XGService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountingSystemApplicationTests {

	@Autowired
	TGhkMapper tGhkMapper;

	@Autowired
	XGService xgService;

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
	public void test() throws IOException {
		System.out.println(xgService.insert_xg("F:\\HUNDSUN\\JYQS\\20180604\\JSMXjsmr1.dbf"));
	}
}
