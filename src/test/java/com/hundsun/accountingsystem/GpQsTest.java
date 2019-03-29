package com.hundsun.accountingsystem;

import java.util.Map;

import com.hundsun.accountingsystem.Global.service.LfjxQsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.Global.util.FilePathUtil;
import com.hundsun.accountingsystem.TGp.service.GPPZService;
import com.hundsun.accountingsystem.TGp.service.GPQSService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GpQsTest {
	
	@Autowired
	GPQSService gphg;
	
	@Autowired
	GPQSService gpjy;
	
	@Autowired
	GPPZService pzService;

	@Autowired
	LfjxQsService lfjxQsService;

	@Test
	public void test() throws Exception {
		String date = "2018-06-15";
		Map<String, String> res = FilePathUtil.getFilePathByDate(date);
		gphg.setPath(res.get("JSMX"), res.get("ZQBD"), res.get("SJSJG"));
		
		gphg.hgqs(10004, DateFormatUtil.getDateByString(date));
		
		gpjy.gpqs(10004, DateFormatUtil.getDateByString(date));
	}

	@Test
	public void pztest() throws Exception {
		pzService.insertGPPZ(10004, DateFormatUtil.getDateByString("2018-06-04"));
	}


	@Test
	public void lfjxtest() throws Exception {
		System.out.println(lfjxQsService.lfjxQs(10004, DateFormatUtil.getDateByString("2018-05-30")));;
	}

	@Test
	public void lfjxPzTest() throws  Exception{
		lfjxQsService.lfjxPz(10004, DateFormatUtil.getDateByString("2018-05-30"));
	}
}
