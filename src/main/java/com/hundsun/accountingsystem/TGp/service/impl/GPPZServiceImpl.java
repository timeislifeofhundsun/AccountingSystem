package com.hundsun.accountingsystem.TGp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.TGp.service.GPPZService;

/**
 * 
* <p>
* Description:GPPZService的实现
* <p>
* Company: SMARTLAB 
* @author gaozhen
* @date 2019年3月20日
* @Version 1.1
 */
public class GPPZServiceImpl implements GPPZService {

	@Autowired
	private TQsbMapper tQsbMapper;
	
	@Override
	public boolean insertGPPZ(int ztbh, Date ywrq) throws Exception {
		boolean res = false;
		
		this.insertGpmrpz(ztbh, ywrq);
		
		res = true;
		return res;
	}
	
	private boolean insertGpmrpz(int ztbh, Date ywrq){
		boolean res = false;
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", DateFormatUtil.getStringByDate(ywrq)));
		assist.setRequires(Assist.andEq("ywlb", 1101));
		List<TQsb> qsbs = tQsbMapper.selectTQsb(assist);
		for (TQsb tQsb : qsbs) {
			System.out.println(tQsb);
		}
		res = true;
		return res;
	}

}
