package com.hundsun.accountingsystem;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hundsun.accountingsystem.Global.bean.TGhk;
import com.hundsun.accountingsystem.Global.mapper.TGhkMapper;
import com.hundsun.accountingsystem.Global.util.FileParsing;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TGHKTest {
	@Autowired
	TGhkMapper mapper;

	@Test
	public void testSelect() throws Exception {
		System.out.println(mapper.selectTGhk(null));
	}

	@Test
	public void testInsert() throws Exception {
		List<TGhk> tGhks = new FileParsing().ReadDbf("/Users/gaozhen/userApp/hsdatasource/2018-05-30/GH32562.dbf");
		System.out.println(mapper.insertTGhkByBatch(tGhks));
	}
}
