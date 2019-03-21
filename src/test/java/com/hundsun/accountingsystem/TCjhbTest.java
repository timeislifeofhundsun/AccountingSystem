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

import com.hundsun.accountingsystem.Global.bean.TCjhbbExample;
import com.hundsun.accountingsystem.Global.bean.TCjhbbExample.Criteria;
import com.hundsun.accountingsystem.Global.mapper.TCjhbbMapper;
import com.hundsun.accountingsystem.Global.service.THqbService;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TCjhbTest {
	@Autowired
	TCjhbbMapper tCjhbbMapper;

	@Test
	public void testSelect() throws IOException, ParseException {
		int ztbh = 10004;
		Date ywrq = DateFormatUtil.getDateByString("2018-05-31");
		TCjhbbExample  example = new TCjhbbExample();
		Criteria criteria = example.createCriteria();
		criteria.andZtbhEqualTo(ztbh);
		criteria.andYwlbEqualTo(1);
		criteria.andYwrqBetween(ywrq, ywrq);
		System.out.println(tCjhbbMapper.selectByExample(example));;
	}

}
