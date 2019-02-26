package com.hundsun.accountingsystem;

import com.hundsun.accountingsystem.Global.util.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountingSystemApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println(MD5Util.encode("admin"));

	}

}
