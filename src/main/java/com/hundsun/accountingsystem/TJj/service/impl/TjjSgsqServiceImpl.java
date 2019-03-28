package com.hundsun.accountingsystem.TJj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.VO.TQsbParamPojo;
import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TPzb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.TPzbMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.TJj.service.TjjSgsqService;

@Service
public class TjjSgsqServiceImpl implements TjjSgsqService {

	@Autowired
	TQsbMapper tqsbMapper;
	
	@Autowired
	TCcyebMapper tccyebMapper;
	
	@Autowired
	TPzbMapper tpzbMapper;
	
	@Override
	public List<TQsb> selectByPage(int page, int limit) {
		/*
		 * 根据业务类别为（4101和4201从清算表里面查出数据，这里不按账套进行区分）
		 * */
		//pojo,根据4101  4201 以及limit查询出结果，自己写sql语句		
		TQsbParamPojo params = new TQsbParamPojo();
		params.setStart((page-1)*limit);
		params.setEnd(limit);
	
		List<TQsb> list = tqsbMapper.selectByYwlbAndLimit(params);
		return list;
	}

	@Override
	public int getCounts() {
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ywlb", "4101"));
		assist.setRequires(Assist.orEq("ywlb", "4201"));
		return (int)tqsbMapper.getTQsbRowCount(assist);
	}

	@Override
	public List<TQsb> selectByPageAndZtbh(int page, int limit, int ztbh) {
		TQsbParamPojo params = new TQsbParamPojo();
		params.setStart((page-1)*limit);
		params.setEnd(limit);
		params.setZtbh(ztbh);
		return tqsbMapper.selectByPageAndZtbh(params);
	}

	@Override
	public int getCountsByZtbh(int ztbh) {
		TQsbParamPojo params = new TQsbParamPojo();
		params.setZtbh(ztbh);
		return tqsbMapper.getCountsByZtbh(params);
	}

	@Override
	public List<TQsb> selectByPageAndDate(int page, int limit, String date) {
		TQsbParamPojo params = new TQsbParamPojo();
		params.setEnd(limit);
		params.setStart((page-1)*limit);
		params.setDate(date);
		return tqsbMapper.selectByPageAndDate(params);
	}

	@Override
	public int getCountsByDate(String date) {
		TQsbParamPojo params = new TQsbParamPojo();
		params.setDate(date);
		return tqsbMapper.getCountsByDate(params);
	}

	@Override
	public void insertIntoQsb(TQsb tqsb) {
		tqsbMapper.insertTQsb(tqsb);
		
	}

	@Override
	public Map<String, Double> getCcyebXx(int ztbh) {
		
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("kjkmdm", "100201"));
		List<TCcyeb> yhck = tccyebMapper.selectTCcyeb(assist);
		Map<String,Double> map = new HashMap<String,Double>();
		if(yhck!=null && yhck.size()==1) {
			Double zqcb = yhck.get(0).getZqcb();
			map.put("100201", zqcb);
			map.put("yhckid", (double)yhck.get(0).getId());
		}
		
		Assist assist1 = new Assist();
		assist1.setRequires(Assist.andEq("ztbh", ztbh));
		assist1.setRequires(Assist.andEq("kjkmdm", "1133"));
		List<TCcyeb> zqqsk = tccyebMapper.selectTCcyeb(assist1);
		if(zqqsk!=null && zqqsk.size()==1) {
			Double zqcb = zqqsk.get(0).getZqcb();
			map.put("1133", zqcb);
			map.put("zqqskid",(double)zqqsk.get(0).getId());
		}else {
			TCcyeb tccyeb = new TCcyeb();
			tccyeb.setZtbh(ztbh);
			tccyeb.setKjkmdm("1133");
			tccyeb.setZqcb(0.0);
			int i = tccyebMapper.insertTCcyeb(tccyeb);
			if(i>0) {
				Assist assist2 = new Assist();
				assist2.setRequires(Assist.andEq("ztbh", ztbh));
				assist2.setRequires(Assist.andEq("kjkmdm", "1133"));
				List<TCcyeb> selectTCcyeb = tccyebMapper.selectTCcyeb(assist2);
				if(selectTCcyeb!=null && selectTCcyeb.size()==1) {
					map.put("zqqskid",(double)selectTCcyeb.get(0).getId());
				}
			}
			map.put("1133", 0.0);
			
		}
		return map;
	}

	@Override
	public void updateCcyeb(TCcyeb tyhck) {
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", tyhck.getZtbh()));
		assist.setRequires(Assist.andEq("kjkmdm", tyhck.getKjkmdm()));
		tccyebMapper.updateTCcyeb(tyhck, assist);	
	}

	@Override
	public boolean isHavePz(Integer id) {
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("extendf", id+""));
		List<TPzb> list = tpzbMapper.selectTPzb(assist);
		if(list!=null && list.size()>0) {
			return false;
		}
		return true;
	}

	@Override
	public void updateTqsb(TQsb tqsb) {
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("id", tqsb.getId()));
		tqsbMapper.updateTQsb(tqsb, assist);
	}

	@Override
	public TQsb getTqsbByiD(Integer id) {
		
		return tqsbMapper.selectTQsbById(id);
	}

	@Override
	public void deleteTqsbById(int id) {
		tqsbMapper.deleteTQsbById(id);
	}

}
