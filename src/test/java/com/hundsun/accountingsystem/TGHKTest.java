package com.hundsun.accountingsystem;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TGhk;
import com.hundsun.accountingsystem.Global.bean.Assist.WhereRequire;
import com.hundsun.accountingsystem.Global.mapper.TGhkMapper;
import com.hundsun.accountingsystem.Global.util.FileParsing;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TGHKTest {
	@Autowired
	TGhkMapper mapper;

	@Test
	public void testSelect() throws Exception {
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("gdcode", "B880580627")
				          ,Assist.andEq("xwcode", "32562"));
		System.out.println(mapper.selectTGhk(assist));
	}

	@Test
	public void testInsert() throws Exception {
		List<TGhk> tGhks = new FileParsing().ReadDbf("/Users/gaozhen/userApp/hsdatasource/2018-05-30/GH32562.dbf");
		System.out.println(mapper.insertTGhkByBatch(tGhks));
	}
}
