package com.hundsun.accountingsystem;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.Global.util.FilePathUtil;
import com.hundsun.accountingsystem.TGp.service.GPQSService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GpQsTest {
	
	@Autowired
	GPQSService hg;
	
	@Autowired
	GPQSService gpjy;

	@Test
	public void test() throws Exception {
		String date = "2018-06-06";
		Map<String, String> res = FilePathUtil.getFilePathByDate(date);
		hg.setPath(res.get("JSMX"), res.get("ZQBD"), res.get("SJSJG"));
		
		
		hg.hgqs(10004, DateFormatUtil.getDateByString(date));
		
		gpjy.gpqs(10004, DateFormatUtil.getDateByString(date));
	}

	@Test
	public void hg() throws Exception {
		
	}
	
	
	
}
