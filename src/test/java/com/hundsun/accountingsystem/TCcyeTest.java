package com.hundsun.accountingsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TCcyeTest {
	@Autowired
	TCcyebMapper mapper;

	@Test
	public void test() throws Exception {
		TCcyeb ccyeb = mapper.selectTCcyebById(43);
		System.out.println(ccyeb);
	}

}
