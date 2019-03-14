package com.hundsun.accountingsystem;


import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.hundsun.accountingsystem.Global.bean.TGhk;
import com.hundsun.accountingsystem.Global.service.TGhkService;
import com.hundsun.accountingsystem.Global.util.FileParsing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hundsun.accountingsystem.Global.mapper.TGhkMapper;
import com.hundsun.accountingsystem.Global.mapper.TJyflMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountingSystemApplicationTests {

	@Autowired
	TGhkMapper tGhkMapper;
	@Autowired
	TJyflMapper tJyflMapper;

	@Autowired
	TGhkService tGhkService;
	

	@Test
	public void contextLoads() throws IOException {
		FileParsing fileParsing = new FileParsing();
		List<TGhk> tGhks = fileParsing.ReadDbf("C:\\Users\\yangjf25257\\Desktop\\JYQS\\20180530\\GH32562.dbf");
//		System.out.println(	tGhkMapper.insert_list(tGhks));
		int i = tGhks.size();
		for (int j = 0; j < i; j ++){
			tGhkMapper.insert(tGhks.get(j));
		}

	}

}
