package com.hundsun.accountingsystem.Global.service.serviceImpl;

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
import com.hundsun.accountingsystem.Global.bean.TCjhbbExample;
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
		 * 3.对查询出来的数据根据证券代码进行合笔计算
		 * 4.对合笔后的数据进行业务类别的判断（根据买卖方向以及证券代码）
		 * 5.保存账套编号以及业务类别、市场编号等，将数据插入到cjhbb里面。
		 * */
		
		//操作之前先删除今天cjhbb的数据
		String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
		TCjhbbParamPojo param=new TCjhbbParamPojo();
		param.setStartDate(format);
		param.setEndDate(format);
		tcjhbbMapper.deleteByDate(param);
		
		//根据账套编号查询出股东代码和席位代码
		TGdxxbExample example=new TGdxxbExample();
		Criteria criteria = example.createCriteria();
		criteria.andZtbhEqualTo(ztbh);
		List<TGhk> ghkList=new ArrayList<TGhk>();
		//根据股东代码和席位代码查询出所有的过户库数据
		Assist assist = new Assist();		
		List<TGdxxb> gdxxbList = tgdxxbMapper.selectByExample(example);
		if(gdxxbList.size()!=0) {
			for(int i=0;i<gdxxbList.size();i++) {
				assist.setRequires(Assist.andEq("gdcode", gdxxbList.get(i).getGddm())
					      ,Assist.andEq("xwcode", gdxxbList.get(i).getXwbh()));
				ghkList.addAll(tghkMapper.selectTGhk(assist));
			}
		}
		//对过户库中同一证券代码的所有交易数据进行合笔操作
		Map<String,Map<String,Double>> map=new HashMap<String,Map<String,Double>>();
		//存储不同的证券代码，用于以后遍历map集合用（map的健值都存储到sb中）
		StringBuilder sb = new StringBuilder();
		//遍历集合中的每一条数据
		//System.out.println("数据："+ghkList);
		for(int i=0;i<ghkList.size();i++) {
			//如果map中含有证券代码的键值对，取出map集合，对取出的map集合中的键值对进行合并操作
			if(map.containsKey(ghkList.get(i).getZqcode())) {
				Map<String, Double> map2 = map.get(ghkList.get(i).getZqcode());
				double cjsl=map2.get("cjsl");
				double cjje=map2.get("cjje");
				//如果是B，则进行相加
				if(ghkList.get(i).getBs().equals("B")) {
					cjsl+=ghkList.get(i).getCjsl();
					cjje+=ghkList.get(i).getCjje();
					map2.put("cjsl", cjsl);
					map2.put("cjje", cjje);
				}else {//反之进行相减
					cjsl-=ghkList.get(i).getCjsl();
					cjje-=ghkList.get(i).getCjje();
					map2.put("cjsl", cjsl);
					map2.put("cjje", cjje);
				}
			}else {//如果没有对应证券代码的键值对，则新建一对键值对
				sb.append(ghkList.get(i).getZqcode()+",");
				Map<String, Double> map2 =new HashMap<String, Double>();
				double cjsl=(double)ghkList.get(i).getCjsl();
				double cjje=ghkList.get(i).getCjje();
				//将该证券对应的交易市场类别存进去
				double jysc=ghkList.get(i).getSclb();
				//如果是S，则将cjsl、cjje设置为负值
				if(ghkList.get(i).getBs().equals("S")) {
					cjsl=-cjsl;
					cjje=-cjje;
				}
				map2.put("cjsl",cjsl);
				map2.put("cjje",cjje);
				map2.put("jysc", jysc);
				//往map中插入一对键值对
				map.put(ghkList.get(i).getZqcode(), map2);
			}
		}
		sb.deleteCharAt(sb.length()-1);
		String[] string;
		if(sb.length()>7) {
			string=sb.toString().split(",");
		}else {
			string= new String[]{sb.toString()};
		}
		
		for(int i=0;i<string.length;i++) {
			//System.out.println(string[i]);
			TCjhbb tcjhbb=new TCjhbb();
			tcjhbb.setGddm(gdxxbList.get(0).getGddm());
			tcjhbb.setXwbh(gdxxbList.get(0).getXwbh());
			tcjhbb.setZtbh(ztbh);
			tcjhbb.setZqdm(string[i]);	
			String cjsl=Double.toString(map.get(string[i]).get("cjsl"));
			tcjhbb.setJysc(Integer.parseInt(Double.toString(map.get(string[i]).get("jysc")).substring(0, 1)));
			tcjhbb.setCjsl(Integer.parseInt(cjsl.substring(0, cjsl.length()-2)));
			double cjje=map.get(string[i]).get("cjje");			
			if(cjje>0) {
				tcjhbb.setMmfx("B");
			}else {
				tcjhbb.setMmfx("S");
			}
			tcjhbb.setCjje(cjje);
			tcjhbb.setYwrq(new Date());
			//根据证券代码查出业务类别
			List<TZqxx> list = tzqxxServiceImpl.findByZqdm(string[i]);
			if(list!=null&&list.size()>1) {
				System.out.println("待处理！！！");
				throw new RuntimeException("证券代码不唯一！！！");
			}
			if(list!=null&&list.size()==1){
				tcjhbb.setYwlb(list.get(0).getZqlb());
			}
						
			//插入到成交汇报表
			tcjhbbMapper.insert(tcjhbb);
			//System.out.println("insert successful!!!");
		}
		return true;
	}

}
