package com.hundsun.accountingsystem;

import com.hundsun.accountingsystem.Global.bean.JYFL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountingSystemApplicationTests {
//	@Autowired
//	private JYFLMapper jyflMapper;

	@Test
	public void contextLoads() {
		JYFL jyfl = new JYFL();
//		jyfl.setGh(1.0).setYwcode(1).setJsfl(1.0).setYj(1.0).setZg(1.0).setYwname("1").setYh(1.0);
//		System.out.println(jyflMapper.insert_jyfl(jyfl));
//		System.out.println(jyflMapper.get_jyfl(1).toString());
//		System.out.println(jyflMapper.delete_jyfl(1));
	}

}
