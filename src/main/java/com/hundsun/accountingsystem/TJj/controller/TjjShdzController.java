package com.hundsun.accountingsystem.TJj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.VO.TQsbVO;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.bean.TZtxxb;
import com.hundsun.accountingsystem.Global.service.TZtxxbService;
import com.hundsun.accountingsystem.TJj.VO.UnionVO_sg;
import com.hundsun.accountingsystem.TJj.VO.Union_sg;
import com.hundsun.accountingsystem.TJj.service.TjjShdzService;

@RestController
public class TjjShdzController {

	@Autowired
	TjjShdzService tjjshdzServiceImpl;
	
	@Autowired
	TZtxxbService tztxxbServiceImpl;
	
	@GetMapping("/TShxx")
	public String findShxxList(int page,int limit) {
		List<TQsb> findList = tjjshdzServiceImpl.selectByPage(page,limit);
		int count = tjjshdzServiceImpl.getCounts();
		UnionVO_sg layuiJson = changeDateForamt(findList);
		layuiJson.setCount(count);
		return JSON.toJSONString(layuiJson);
	}
	
	@GetMapping("/findShxxByZtbh")
	public String findListByZtbh(int page,int limit,int ztbh) {
		/*
		 * 根据业务类别为（4101和4201从清算表里面查出数据，这里按账套进行区分）
		 * */
		List<TQsb> findList = tjjshdzServiceImpl.selectByPageAndZtbh(page,limit,ztbh);
		int count = tjjshdzServiceImpl.getCountsByZtbh(ztbh);
		UnionVO_sg layuiJson = changeDateForamt(findList);
		layuiJson.setCount(count);
		return JSON.toJSONString(layuiJson);
	}
	
	@GetMapping("/TShxxByDate")
	public String findListByDate(int page,int limit,String date) {
		/*
		 * 根据业务类别为（4101和4201从清算表里面查出数据，这里按账套进行区分）
		 * */
		List<TQsb> findList = tjjshdzServiceImpl.selectByPageAndDate(page,limit,date);
		int count = tjjshdzServiceImpl.getCountsByDate(date);
		UnionVO_sg layuiJson = changeDateForamt(findList);
		layuiJson.setCount(count);
		return JSON.toJSONString(layuiJson);
	}
	
	private UnionVO_sg changeDateForamt(List<TQsb> findList) {
		/*
		 * 这里要对查询出来的所有数据进行再一次的封装操作
		 * 1.遍历每一条数据，根据账套编号去账套信息表里面查出账套名称
		 * 2.将账套编号和账套名称结合起来，用一个新的对象来存储数据
		 * 3.将新的对象放入到list集合中，返回给前台页面
		 * */
		Map<String,String> map = new HashMap<String,String>();
		List<Union_sg> list = new ArrayList<Union_sg>();
		if(findList!=null && findList.size()>0) {
			for(int i=0;i<findList.size();i++) {
				Union_sg union = new Union_sg();
				TQsb tQsb = findList.get(i);
				union.setId(tQsb.getId());
				union.setRq(tQsb.getRq());
				union.setZqcode(tQsb.getZqcode());
				union.setYwlb(tQsb.getYwlb());
				union.setQuantity(tQsb.getQuantity());
				union.setAmount(tQsb.getAmount());
				union.setJsf(tQsb.getJsf());
				union.setGhf(tQsb.getGhf());
				union.setZgf(tQsb.getZgf());
				union.setYj(tQsb.getYj());
				union.setZtbh(tQsb.getZtbh());
				union.setZqqsk(tQsb.getZqqsk());
				String ztbh = tQsb.getZtbh()+"";
				if(map.containsKey(ztbh)) {
					union.setZtbhname(ztbh+"_"+map.get(ztbh));
				}else {
					TZtxxb tztxxb = tztxxbServiceImpl.findZtById(tQsb.getZtbh());
					map.put(ztbh+"", tztxxb.getName());
					union.setZtbhname(ztbh+"_"+tztxxb.getName());
				}
				
				list.add(union);
			}
		}	
		UnionVO_sg layuiJson = new UnionVO_sg();
		layuiJson.setCode(0);
	    layuiJson.setCount(0);
	    layuiJson.setMsg("");
	    layuiJson.setData(list);
		return layuiJson;
	}
	
	
	
	@PostMapping("/TShxx")
	public String insertShxxAndUpdateCcye(@RequestParam(value = "Shxx",required = true) String data) {
		TQsb tqsb = JSON.parseObject(data,TQsb.class);
		try {
			tjjshdzServiceImpl.insertShxxAndUpdateCcye(tqsb);
		} catch (Exception e) {
			return e.getMessage();
		}
		return String.valueOf(1);
	}
}
