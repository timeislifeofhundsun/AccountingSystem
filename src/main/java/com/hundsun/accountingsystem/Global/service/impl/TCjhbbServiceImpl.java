package com.hundsun.accountingsystem.Global.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.VO.TCjhbbParamPojo;
import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TCjhbb;
import com.hundsun.accountingsystem.Global.bean.TGdxxb;
import com.hundsun.accountingsystem.Global.bean.TGdxxbExample;
import com.hundsun.accountingsystem.Global.bean.TGdxxbExample.Criteria;
import com.hundsun.accountingsystem.Global.bean.TGhk;
import com.hundsun.accountingsystem.Global.bean.TZqxx;
import com.hundsun.accountingsystem.Global.mapper.TCjhbbMapper;
import com.hundsun.accountingsystem.Global.mapper.TGdxxbMapper;
import com.hundsun.accountingsystem.Global.mapper.TGhkMapper;
import com.hundsun.accountingsystem.Global.service.TCjhbbService;
import com.hundsun.accountingsystem.Global.service.TZqxxService;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;

@Service
public class TCjhbbServiceImpl implements TCjhbbService {

	@Autowired
	TGdxxbMapper tgdxxbMapper;
	
	@Autowired
	TGhkMapper tghkMapper;
	
	@Autowired
	TCjhbbMapper tcjhbbMapper;
	
	@Autowired
	TZqxxService tzqxxServiceImpl;
		
	@Override
	public boolean insertCjhbbByRzqs(int ztbh, Date date) {
		/*
		 * 1.获取账套对应的股东代码和席位代码。
		 * 2.根据股东代码、席位代码以及日期查询出过户库的数据
		 * 3.对查询出来的数据根据证券代码进行业务判断操作
		 * 4.对合笔后的数据进行业务类别的判断（根据买卖方向以及证券代码）
		 * 5.保存账套编号以及业务类别、市场编号等，将数据插入到cjhbb里面。
		 * */
		
		//操作之前先根据账套编号删除今天cjhbb的数据
		String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
		TCjhbbParamPojo param=new TCjhbbParamPojo();
		param.setStartDate(format);
		param.setEndDate(format);
		param.setZtbh(ztbh);
		tcjhbbMapper.deleteByDate(param);
		
		//根据账套编号查询出股东代码和席位代码
		TGdxxbExample example=new TGdxxbExample();
		Criteria criteria = example.createCriteria();
		criteria.andZtbhEqualTo(ztbh);
		List<TGhk> ghkList=new ArrayList<TGhk>();
		//根据股东代码和席位代码查询出所有的过户库数据	
		List<TGdxxb> gdxxbList = tgdxxbMapper.selectByExample(example);
		if(gdxxbList.size()!=0) {
			for(int i=0;i<gdxxbList.size();i++) {
				Assist assist = new Assist();
				assist.setRequires(Assist.andEq("gdcode", gdxxbList.get(i).getGddm())
					      ,Assist.andEq("xwcode", gdxxbList.get(i).getXwbh()));
				assist.setRequires(Assist.andEq("bctime",DateFormatUtil.getStringByDate(date)));
				ghkList.addAll(tghkMapper.selectTGhk(assist));
			}
		}
		List<TCjhbb> tcjhbbList=new ArrayList<TCjhbb>();
		//对过户库中的所有交易数据进行判断业务操作操作
		Map<String,Integer> map=new HashMap<String,Integer>();
		//遍历集合中的每一条数据
		for(int i=0;i<ghkList.size();i++) {
			TCjhbb tcjhbb=new TCjhbb();
			tcjhbb.setGddm(ghkList.get(i).getGdcode());
			tcjhbb.setXwbh(ghkList.get(i).getXwcode());
			tcjhbb.setZtbh(ztbh);
			tcjhbb.setZqdm(ghkList.get(i).getZqcode());	
			tcjhbb.setJysc(ghkList.get(i).getSclb());
			tcjhbb.setMmfx(ghkList.get(i).getBs());
			if(tcjhbb.getJysc()==1) {
				tcjhbb.setCjsl(Math.abs((ghkList.get(i).getCjsl())));
			}else {
				tcjhbb.setCjsl(ghkList.get(i).getCjsl());
			}
			tcjhbb.setCjjg(ghkList.get(i).getCjjg());
			tcjhbb.setCjje(ghkList.get(i).getCjje());
			tcjhbb.setYwrq(date);
			//如果map中含有证券代码的键值对，取出值，并设置业务类别
			if(map.containsKey(ghkList.get(i).getZqcode())) {
				Integer ywlb = map.get(ghkList.get(i).getZqcode());
				tcjhbb.setYwlb(ywlb);
				
			}else {//如果没有对应证券代码的键值对，则新建一对键值对，并将查询出来的业务类别插入map中
				List<TZqxx> list = tzqxxServiceImpl.findByZqdm(ghkList.get(i).getZqcode());
				Integer ywlb = 0;
				if(list!=null&&list.size()>1) {
					ywlb=1;
				}
				if(list!=null&&list.size()==1){
					ywlb=list.get(0).getZqlb();
				}
				tcjhbb.setYwlb(ywlb);
				map.put(ghkList.get(i).getZqcode(), ywlb);
			}
			tcjhbbList.add(tcjhbb);
		}
		//批量插入到数据库
		try {
			if(tcjhbbList.size()>0) {
				tcjhbbMapper.insertList(tcjhbbList);
			}
		} catch (Exception e) {
			return false;
		}		
		return true;
	}
}
