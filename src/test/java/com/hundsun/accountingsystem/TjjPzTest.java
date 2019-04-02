package com.hundsun.accountingsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hundsun.accountingsystem.TJj.service.TjjScpzService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TjjPzTest {

	@Autowired
	TjjScpzService tjjScpzServiceImpl;
	
	@Test
	public void test() {
		tjjScpzServiceImpl.scpz(10004, "2019-03-29");
	}
}
