package com.hundsun.accountingsystem;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hundsun.accountingsystem.Global.service.THqbService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class THqTest {
	@Autowired
	THqbService tHqbService;

	@Test
	public void testReadFile() throws IOException, ParseException {
		Date date = null;
		String SHFilePath = "/Users/gaozhen/Desktop/恒生毕设/接口文件/2018-05-30/mktdt00.txt";
		String SZFilePath = "/Users/gaozhen/Desktop/恒生毕设/接口文件/2018-05-30/cashsecurityclosemd_20180530.xml";
		// 这里会有一个异常，所以要用try catch捕获异常
		date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-30");
		boolean res = tHqbService.readHqDataByFile(SHFilePath, SZFilePath, date);
		System.out.println(res);
	}

}
