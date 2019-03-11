package com.hundsun.accountingsystem;


import com.hundsun.accountingsystem.Global.bean.GHK;
import com.hundsun.accountingsystem.Global.mapper.GHKMapper;
import com.hundsun.accountingsystem.Global.util.FileParsing;
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
	GHKMapper ghkMapper;

	@Test
	public void contextLoads() throws IOException {
		FileParsing fileParsing = new FileParsing();
		List<GHK> list= fileParsing.ReadDbf("C:\\Users\\yangjf25257\\Desktop\\JYQS\\GH32562.dbf");
//		int i = 1;
//		for (GHK ghk : list){
//			ghkMapper.insert(ghk);
//			System.out.println(i);
//			i ++;
//		}
		ghkMapper.insert_list(list);
	}

}
