package com.hundsun.accountingsystem;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hundsun.accountingsystem.Global.mapper.TJyflMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TJYFLTest {
	@Autowired
	TJyflMapper mapper;

	@Test
	public void testSelect() throws IOException, ParseException {
		System.out.println(mapper.selectByPrimaryKey(1101));
		System.out.println(mapper.selectByPrimaryKey(1102));
	}

}
