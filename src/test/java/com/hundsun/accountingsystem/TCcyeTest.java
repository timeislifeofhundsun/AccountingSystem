package com.hundsun.accountingsystem;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TCcyeTest {
	@Autowired
	TCcyebMapper mapper;

	@Test
	public void testGpjyUpdateYe() throws Exception {
		Map<String, Object> para = new HashMap<>();
		para.put("ztbh", 10014);
		para.put("ywrq","2018-06-01");
		mapper.gpjyResetYe(para);
		mapper.gpjyUpdateYe(para);
	}

}
