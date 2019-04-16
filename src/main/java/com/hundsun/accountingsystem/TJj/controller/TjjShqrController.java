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
import com.hundsun.accountingsystem.TJj.VO.Union_sh;
import com.hundsun.accountingsystem.TJj.VO.UnionVO_sh;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TZtxxb;
import com.hundsun.accountingsystem.Global.service.TZtxxbService;
import com.hundsun.accountingsystem.TJj.VO.TCcyebSh;
import com.hundsun.accountingsystem.TJj.service.TjjShqrService;

@RestController
public class TjjShqrController {

	@Autowired
	TjjShqrService tjjshqrServiceImpl;
	
	@Autowired
	TZtxxbService tztxxbServiceImpl;
	
	@GetMapping("/TShqr")
	public String findListFromTccyeb(int page,int limit) {
		/*
		 * 根据zqnm从持仓表里面得到所有账套的基金持仓，并展示到前台页面
		 * 后续可以根据账套编号或者日期进行持仓的检索
		 * */
		List<TCcyeb> findList = tjjshqrServiceImpl.selectByPage(page,limit);	
		return JSON.toJSONString(changeDateForamt(findList));
	}
	
	private UnionVO_sh changeDateForamt(List<TCcyeb> findList) {
		/*
		 * 这里要对查询出来的所有数据进行再一次的封装操作
		 * 1.遍历每一条数据，根据账套编号去账套信息表里面查出账套名称
		 * 2.将账套编号和账套名称结合起来，用一个新的对象来存储数据
		 * 3.将新的对象放入到list集合中，返回给前台页面
		 * */
		Map<String,String> map = new HashMap<String,String>();
		List<Union_sh> list = new ArrayList<Union_sh>();
		if(findList!=null && findList.size()>0) {
			for(int i=0;i<findList.size();i++) {
				Union_sh union = new Union_sh();
				TCcyeb tCcyeb = findList.get(i);
				union.setId(tCcyeb.getId());
				union.setFsrq(tCcyeb.getFsrq());
				union.setZqdm(tCcyeb.getZqdm());
				union.setCysl(tCcyeb.getCysl());
				union.setZqcb(tCcyeb.getZqcb());
				union.setExtenda(tCcyeb.getExtenda());
				union.setLjgz(tCcyeb.getLjgz());
				union.setZtbh(tCcyeb.getZtbh());
				String ztbh = tCcyeb.getZtbh()+"";
				if(map.containsKey(ztbh)) {
					union.setZtbhname(ztbh+"_"+map.get(ztbh));
				}else {
					TZtxxb tztxxb = tztxxbServiceImpl.findZtById(tCcyeb.getZtbh());
					map.put(ztbh+"", tztxxb.getName());
					union.setZtbhname(ztbh+"_"+tztxxb.getName());
				}
				
				list.add(union);
			}
		}	
		int count = tjjshqrServiceImpl.getCountsByZqnm(4);
		UnionVO_sh layuiJson = new UnionVO_sh();
		layuiJson.setCode(0);
	    layuiJson.setCount(count);
	    layuiJson.setMsg("");
	    layuiJson.setData(list);
		return layuiJson;
	}
	
	@GetMapping("/findTShqrByZtbh")
	public String findListFromTccyebByZtbh(int page,int limit,int ztbh) {
		List<TCcyeb> findList = tjjshqrServiceImpl.selectByPageAndZtbh(page,limit,ztbh);
		int count = tjjshqrServiceImpl.getCountsByZtbh(ztbh);
		UnionVO_sh layuiJson = changeDateForamt(findList);
		layuiJson.setCount(count);
		return JSON.toJSONString(layuiJson);
	}
	
	@PostMapping("/TShqr")
	public String findListFromTccyebByDate(int page,int limit,String date) {
		List<TCcyeb> findList = tjjshqrServiceImpl.selectByPageAndDate(page,limit,date);
		int count = tjjshqrServiceImpl.getCountsByDate(date);
		UnionVO_sh layuiJson = changeDateForamt(findList);
		layuiJson.setCount(count);   
		return JSON.toJSONString(layuiJson);
	}
	
	@PostMapping("/TConfirmTSh")
	public String confirmTSh(@RequestParam(value = "shxx",required = true)String data) {
		TCcyebSh tccyeb = JSON.parseObject(data,TCcyebSh.class);
		/*
		 * 1.计算出各种费用到并插入到清算表中（证券清算款，需要得到收盘价），经手费，证管费，应付交易费用（佣金）
		 * 2.同步更新持仓表（计算估值增值、计算证券成本、计算持有数量）
		 * 3.同步更新余额表（计算证券清算款、计算交易费用、计算投资收益、应付交易费用）
		 * */
		try {
			tjjshqrServiceImpl.confirmShxx(tccyeb);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return String.valueOf(1);
	}
	
}
