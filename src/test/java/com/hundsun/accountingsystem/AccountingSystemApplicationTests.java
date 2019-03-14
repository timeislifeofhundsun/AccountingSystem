package com.hundsun.accountingsystem;


import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.bean.TJyflVO;
import com.hundsun.accountingsystem.Global.mapper.TGhkMapper;
import com.hundsun.accountingsystem.Global.mapper.TJyflMapper;
import com.hundsun.accountingsystem.Global.service.TGhkService;
import com.hundsun.accountingsystem.Global.service.serviceImpl.TGhkServiceImpl;
import com.hundsun.accountingsystem.Global.util.FileParsing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountingSystemApplicationTests {

	@Autowired
	TGhkMapper tGhkMapper;
	@Autowired
	TJyflMapper tJyflMapper;
	@Autowired
	TGhkService service;

	@Test
	public void contextLoads() throws IOException, ParseException {
//		service.readGhDataByFile("12","!2","2018-05-31");

   	    tGhkMapper.insertTGhkByBatch(FileParsing.ReadSJSDbf("C:\\Users\\yangjf25257\\Desktop\\JYQS\\20180530\\SJSMX10530.dbf"));
	}

}
