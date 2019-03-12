package com.hundsun.accountingsystem;


import com.hundsun.accountingsystem.Global.bean.TGhk;
import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.bean.TKjkmb;
import com.hundsun.accountingsystem.Global.mapper.TGhkMapper;
import com.hundsun.accountingsystem.Global.mapper.TJyflMapper;
import com.hundsun.accountingsystem.Global.mapper.TKjkmbMapper;
import com.hundsun.accountingsystem.Global.util.FileParsing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountingSystemApplicationTests {

	@Autowired
	TGhkMapper tGhkMapper;
	@Autowired
	TJyflMapper tJyflMapper;

	@Test
	public void contextLoads() throws IOException {
		/*FileParsing fileParsing = new FileParsing();
		List<TGhk> list= fileParsing.ReadDbf("C:\\Users\\wanggk23608\\Desktop\\GH32562.dbf");
//		int i = 1;
//		for (TGhk ghk : list){
//			ghkMapper.insert(ghk);
//			System.out.println(i);
//			i ++;
//		}
		tGhkMapper.insert_list(list);*/
		/*List<TKjkmb> allKJKM = tKjkmbMapper.findAllKJKM();

		for (TKjkmb tkjkmb:allKJKM
			 ) {
			System.out.println(tkjkmb.toString());
		}*/
		/*TJyfl tJyfl = new TJyfl();
		tJyfl.setGh(12.0).setJsfl(23.6).setYh(45.23).setYj(456.32).setYwcode(12314).setYwname("dwadwa").setZg(1646.32);
		TJyfl tJyfl1 = tJyflMapper.selectByPrimaryKey(12314);
		System.out.println(tJyfl1.toString());*/
	}

}
