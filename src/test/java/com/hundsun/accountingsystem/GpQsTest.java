package com.hundsun.accountingsystem;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.TGp.service.GPQSService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GpQsTest {
	@Autowired
	GPQSService service;

	@Test
	public void test() throws Exception {
		service.gpqs(10004, DateFormatUtil.getDateByString("2018-05-30"));
		
	}

}
