package com.hundsun.accountingsystem.TJj.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TPzb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.bean.TZqxx;
import com.hundsun.accountingsystem.Global.bean.TZqxxExample;
import com.hundsun.accountingsystem.Global.bean.TZqxxExample.Criteria;
import com.hundsun.accountingsystem.Global.mapper.TPzbMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.mapper.TZqxxMapper;
import com.hundsun.accountingsystem.Global.service.TSequenceService;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.TJj.service.TjjScpzService;

@Service
public class TjjScpzServiceImpl implements TjjScpzService {

	@Autowired
	TQsbMapper tqsbMapper;
	
	@Autowired
	TSequenceService getSequenceByName;
	
	@Autowired
	TPzbMapper tpzbMapper;
	
	@Autowired
	TZqxxMapper tzqxxMapper;
	
	@Override
	public void scpz(int ztbh, String date) {
		/*
		 * 1.根据日期和账套编号删除凭证表中的数据
		 * 2.根据账套编号以及日期和业务类别分别从清算表中查询出清算数据
		 * 3.根据业务类别对查询出来的数据进行生成凭证的计算
		 * */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", date));
		assist.setRequires(Assist.andEq("extende", "1004"));
		tpzbMapper.deleteTPzb(assist);
		
		//货币基金申购申请的凭证生成，ywlb=4101
		hbjjPz(ztbh,date);
		
		//非货币基金申购申请的凭证生成，ywlb=4201
		fhbjjPz(ztbh,date);
		
		//货币基金申购确认的凭证生成,ywlb = 4102
		hbjjsgqrPz(ztbh,date);
		
		//非货币基金申购确认的凭证生成,ywlb = 4202
		fhbjjsgqrPz(ztbh,date);
		
		//货币基金红利除权的凭证生成，ywlb = 4103
		hbjjhlcqPz(ztbh,date);
		
		//非货币基金估值增值的凭证生成，ywlb = 4203
		fhbjjgzzzPz(ztbh,date);
		
		//货币基金赎回确认的凭证生成，ywlb = 4105
		hbjjshqrPz(ztbh,date);
		
		//非货币基金赎回确认的凭证生成，ywlb = 4205
		fhbjjshqrPz(ztbh,date);
		
		//货币基金赎回到账的凭证生成，ywlb = 4106
		hbjjshdzPz(ztbh,date);
		
		//非货币基金赎回到账的凭证生成，ywlb = 4206
		fhbjjshdzPz(ztbh,date);
	}

	private void fhbjjshdzPz(int ztbh, String date) {
		/*
		 * 借：银行存款
		 * 		贷：证券清算款
		 * */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", date));
		assist.setRequires(Assist.andEq("ywlb", 4206));
		List<TQsb> list = tqsbMapper.selectTQsb(assist);
		Date dateByString = null;
		try {
			dateByString = DateFormatUtil.getDateByString(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<TPzb> pzList = new ArrayList<TPzb>();
		if(list!=null && list.size()>0) {
			for(int i=0;i<list.size();i++) {
				TQsb tQsb = list.get(i);
				
				TZqxxExample example = new TZqxxExample();
				Criteria criteria = example.createCriteria();
				criteria.andZqdmEqualTo(tQsb.getZqcode());
				criteria.andFxfsEqualTo(1);
				List<TZqxx> zqList = tzqxxMapper.selectByExample(example);
				String zy="";
				if(zqList!=null && zqList.size()>0) {
					zy = zqList.get(0).getZqjg();
				}
				
				int id = getSequenceByName.getSequenceByName("pz");
				TPzb tpzb2 = new TPzb();
				tpzb2.setZy("非货币基金赎回到账["+zy+"]");
				tpzb2.setKjkm("100201");
				tpzb2.setKmms("银行存款_活期存款");
				tpzb2.setBs("借");
				tpzb2.setZtbh(ztbh);
				tpzb2.setRq(dateByString);
				tpzb2.setJe(tQsb.getZqqsk());
				tpzb2.setExtendf("4206");
				tpzb2.setPzid(id);
				tpzb2.setExtende("1004");
				pzList.add(tpzb2);
				
				TPzb tpzb = new TPzb();
				tpzb.setZy("非货币基金赎回到账["+zy+"]");
				tpzb.setKjkm("1131");
				tpzb.setKmms("证券清算款_基金");
				tpzb.setBs("贷");
				tpzb.setZtbh(ztbh);
				tpzb.setRq(dateByString);
				tpzb.setJe(tQsb.getZqqsk());
				tpzb.setExtendf("4206");
				tpzb.setPzid(id);
				tpzb.setExtende("1004");
				pzList.add(tpzb);				
			}
			
			tpzbMapper.insertTPzbByBatch(pzList);
			
		}
		
	}

	private void hbjjshdzPz(int ztbh, String date) {
		/*
		 * 借：银行存款
		 * 		贷：证券清算款
		 * */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", date));
		assist.setRequires(Assist.andEq("ywlb", 4106));
		List<TQsb> list = tqsbMapper.selectTQsb(assist);
		Date dateByString = null;
		try {
			dateByString = DateFormatUtil.getDateByString(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<TPzb> pzList = new ArrayList<TPzb>();
		if(list!=null && list.size()>0) {
			for(int i=0;i<list.size();i++) {
				TQsb tQsb = list.get(i);
				
				TZqxxExample example = new TZqxxExample();
				Criteria criteria = example.createCriteria();
				criteria.andZqdmEqualTo(tQsb.getZqcode());
				criteria.andFxfsEqualTo(2);
				List<TZqxx> zqList = tzqxxMapper.selectByExample(example);
				String zy="";
				if(zqList!=null && zqList.size()>0) {
					zy = zqList.get(0).getZqjg();
				}
				
				int id = getSequenceByName.getSequenceByName("pz");
				TPzb tpzb2 = new TPzb();
				tpzb2.setZy("货币基金赎回到账["+zy+"]");
				tpzb2.setKjkm("100201");
				tpzb2.setKmms("银行存款_活期存款");
				tpzb2.setBs("借");
				tpzb2.setZtbh(ztbh);
				tpzb2.setRq(dateByString);
				tpzb2.setJe(tQsb.getZqqsk());
				tpzb2.setExtendf("4106");
				tpzb2.setPzid(id);
				tpzb2.setExtende("1004");
				pzList.add(tpzb2);
				
				TPzb tpzb = new TPzb();
				tpzb.setZy("货币基金赎回到账["+zy+"]");
				tpzb.setKjkm("1131");
				tpzb.setKmms("证券清算款_基金");
				tpzb.setBs("贷");
				tpzb.setZtbh(ztbh);
				tpzb.setRq(dateByString);
				tpzb.setJe(tQsb.getZqqsk());
				tpzb.setExtendf("4106");
				tpzb.setPzid(id);
				tpzb.setExtende("1004");
				pzList.add(tpzb);				
			}
			
			tpzbMapper.insertTPzbByBatch(pzList);
			
		}
		
	}

	private void fhbjjshqrPz(int ztbh, String date) {
		/*
		 * 借：证券清算款（或银行存款）
			     交易费用
			    贷：基金投资-成本
			            基金投资-估值增值
			 	 应付交易费用
			            投资收益-基金投资收益
		 * */
		
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", date));
		assist.setRequires(Assist.andEq("ywlb", 4205));
		List<TQsb> list = tqsbMapper.selectTQsb(assist);
		Date dateByString = null;
		try {
			dateByString = DateFormatUtil.getDateByString(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<TPzb> pzList = new ArrayList<TPzb>();
		if(list!=null && list.size()>0) {
			for(int i=0;i<list.size();i++) {
				TQsb tQsb = list.get(i);
				
				TZqxxExample example = new TZqxxExample();
				Criteria criteria = example.createCriteria();
				criteria.andZqdmEqualTo(tQsb.getZqcode());
				criteria.andFxfsEqualTo(1);
				List<TZqxx> zqList = tzqxxMapper.selectByExample(example);
				String zy="";
				if(zqList!=null && zqList.size()>0) {
					zy = zqList.get(0).getZqjg();
				}
				
				int id = getSequenceByName.getSequenceByName("pz");
				TPzb tpzb = new TPzb();
				tpzb.setZy("非货币基金赎回确认["+zy+"]");
				tpzb.setKjkm("1133");
				tpzb.setKmms("证券清算款_基金");
				tpzb.setBs("借");
				tpzb.setZtbh(ztbh);
				tpzb.setRq(dateByString);
				tpzb.setJe(tQsb.getZqqsk());
				tpzb.setExtendf("4205");
				tpzb.setPzid(id);
				tpzb.setExtende("1004");
				pzList.add(tpzb);
				
				TPzb tpzb2 = new TPzb();
				tpzb2.setZy("非货币基金赎回确认["+zy+"]");
				tpzb2.setKjkm("6203");
				tpzb2.setKmms("交易费用_基金");
				tpzb2.setBs("借");
				tpzb2.setZtbh(ztbh);
				tpzb2.setRq(dateByString);
				tpzb2.setJe(tQsb.getJsf()+tQsb.getZgf());
				tpzb2.setExtendf("4205");
				tpzb2.setPzid(id);
				tpzb2.setExtende("1004");
				pzList.add(tpzb2);
				
				TPzb tpzb3 = new TPzb();
				tpzb3.setZy("非货币基金赎回确认["+zy+"]");
				tpzb3.setKjkm(tQsb.getZqcode());				
				tpzb3.setKmms("基金投资成本_"+zy);
				tpzb3.setBs("贷");
				tpzb3.setZtbh(ztbh);
				tpzb3.setRq(dateByString);
				tpzb3.setJe(tQsb.getAmount());
				tpzb3.setExtendf("4205");
				tpzb3.setPzid(id);
				tpzb3.setExtende("1004");
				pzList.add(tpzb3);
				
				TPzb tpzb4 = new TPzb();
				tpzb4.setZy("非货币基金赎回确认["+zy+"]");
				tpzb4.setKjkm("6003");
				tpzb4.setKmms("投资收益_基金_估值增值");
				tpzb4.setBs("贷");
				tpzb4.setZtbh(ztbh);
				tpzb4.setRq(dateByString);
				tpzb4.setJe(tQsb.getGyjzbd());
				tpzb4.setExtendf("4205");
				tpzb4.setPzid(id);
				tpzb4.setExtende("1004");
				pzList.add(tpzb4);
				
				TPzb tpzb5 = new TPzb();
				tpzb5.setZy("非货币基金赎回确认["+zy+"]");
				tpzb5.setKjkm("2003");
				tpzb5.setKmms("应付交易费用_基金");
				tpzb5.setBs("贷");
				tpzb5.setZtbh(ztbh);
				tpzb5.setRq(dateByString);
				tpzb5.setJe(tQsb.getYj());
				tpzb5.setExtendf("4205");
				tpzb5.setPzid(id);
				tpzb5.setExtende("1004");
				pzList.add(tpzb5);
				
				double tzsy=(tQsb.getZqqsk()+tQsb.getZgf()+tQsb.getJsf())-(
						tQsb.getAmount()+tQsb.getGyjzbd()+tQsb.getYj());				
				TPzb tpzb6 = new TPzb();
				tpzb6.setZy("非货币基金赎回确认["+zy+"]");
				tpzb6.setKjkm("6003");
				tpzb6.setKmms("投资收益_基金");
				tpzb6.setBs("贷");
				tpzb6.setZtbh(ztbh);
				tpzb6.setRq(dateByString);
				tpzb6.setJe(tzsy);
				tpzb6.setExtendf("4205");
				tpzb6.setPzid(id);
				tpzb6.setExtende("1004");
				pzList.add(tpzb6);
				
			}
			
			tpzbMapper.insertTPzbByBatch(pzList);
			
		}
	}

	private void hbjjshqrPz(int ztbh, String date) {
		/*
		 * 借：证券清算款（或银行存款）
			     交易费用
			    贷：基金投资-成本
			            基金投资-估值增值
			 	 应付交易费用
			            投资收益-基金投资收益
		 * */
		
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", date));
		assist.setRequires(Assist.andEq("ywlb", 4105));
		List<TQsb> list = tqsbMapper.selectTQsb(assist);
		Date dateByString = null;
		try {
			dateByString = DateFormatUtil.getDateByString(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<TPzb> pzList = new ArrayList<TPzb>();
		if(list!=null && list.size()>0) {
			for(int i=0;i<list.size();i++) {
				TQsb tQsb = list.get(i);
				
				TZqxxExample example = new TZqxxExample();
				Criteria criteria = example.createCriteria();
				criteria.andZqdmEqualTo(tQsb.getZqcode());
				criteria.andFxfsEqualTo(2);
				List<TZqxx> zqList = tzqxxMapper.selectByExample(example);
				String zy="";
				if(zqList!=null && zqList.size()>0) {
					zy = zqList.get(0).getZqjg();
				}
				
				int id = getSequenceByName.getSequenceByName("pz");
				TPzb tpzb = new TPzb();
				tpzb.setZy("货币基金赎回确认["+zy+"]");
				tpzb.setKjkm("1133");
				tpzb.setKmms("证券清算款_基金");
				tpzb.setBs("借");
				tpzb.setZtbh(ztbh);
				tpzb.setRq(dateByString);
				tpzb.setJe(tQsb.getZqqsk());
				tpzb.setExtendf("4105");
				tpzb.setPzid(id);
				tpzb.setExtende("1004");
				pzList.add(tpzb);
				
				TPzb tpzb2 = new TPzb();
				tpzb2.setZy("货币基金赎回确认["+zy+"]");
				tpzb2.setKjkm("6203");
				tpzb2.setKmms("交易费用_基金");
				tpzb2.setBs("借");
				tpzb2.setZtbh(ztbh);
				tpzb2.setRq(dateByString);
				tpzb2.setJe(tQsb.getJsf()+tQsb.getZgf());
				tpzb2.setExtendf("4105");
				tpzb2.setPzid(id);
				tpzb2.setExtende("1004");
				pzList.add(tpzb2);
				
				TPzb tpzb3 = new TPzb();
				tpzb3.setZy("货币基金赎回确认["+zy+"]");
				tpzb3.setKjkm(tQsb.getZqcode());								
				tpzb3.setKmms("基金投资成本_"+zy);
				tpzb3.setBs("贷");
				tpzb3.setZtbh(ztbh);
				tpzb3.setRq(dateByString);
				tpzb3.setJe(tQsb.getAmount());
				tpzb3.setExtendf("4105");
				tpzb3.setPzid(id);
				tpzb3.setExtende("1004");
				pzList.add(tpzb3);
				
				TPzb tpzb4 = new TPzb();
				tpzb4.setZy("货币基金赎回确认["+zy+"]");
				tpzb4.setKjkm("6003");
				tpzb4.setKmms("投资收益_基金_估值增值");
				tpzb4.setBs("贷");
				tpzb4.setZtbh(ztbh);
				tpzb4.setRq(dateByString);
				tpzb4.setJe(tQsb.getGyjzbd());
				tpzb4.setExtendf("4105");
				tpzb4.setPzid(id);
				tpzb4.setExtende("1004");
				pzList.add(tpzb4);
				
				TPzb tpzb5 = new TPzb();
				tpzb5.setZy("货币基金赎回确认["+zy+"]");
				tpzb5.setKjkm("2003");
				tpzb5.setKmms("应付交易费用_基金");
				tpzb5.setBs("贷");
				tpzb5.setZtbh(ztbh);
				tpzb5.setRq(dateByString);
				tpzb5.setJe(tQsb.getYj());
				tpzb5.setExtendf("4105");
				tpzb5.setPzid(id);
				tpzb5.setExtende("1004");
				pzList.add(tpzb5);
				
				double tzsy=(tQsb.getZqqsk()+tQsb.getZgf()+tQsb.getJsf())-(
						tQsb.getAmount()+tQsb.getGyjzbd()+tQsb.getYj());				
				TPzb tpzb6 = new TPzb();
				tpzb6.setZy("货币基金赎回确认["+zy+"]");
				tpzb6.setKjkm("6003");
				tpzb6.setKmms("投资收益_基金");
				tpzb6.setBs("贷");
				tpzb6.setZtbh(ztbh);
				tpzb6.setRq(dateByString);
				tpzb6.setJe(tzsy);
				tpzb6.setExtendf("4105");
				tpzb6.setPzid(id);
				tpzb6.setExtende("1004");
				pzList.add(tpzb6);
				
			}
			
			tpzbMapper.insertTPzbByBatch(pzList);
			
		}
		
		
	}

	private void fhbjjgzzzPz(int ztbh, String date) {
		/*
		 * 借：基金投资-估值增值
				贷：公允价值变动损益
		 */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", date));
		assist.setRequires(Assist.andEq("ywlb", 4203));
		List<TQsb> list = tqsbMapper.selectTQsb(assist);
		Date dateByString = null;
		try {
			dateByString = DateFormatUtil.getDateByString(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<TPzb> pzList = new ArrayList<TPzb>();
		if(list!=null && list.size()>0) {
			for(int i=0;i<list.size();i++) {
				TQsb tQsb = list.get(i);
				
				TZqxxExample example = new TZqxxExample();
				Criteria criteria = example.createCriteria();
				criteria.andZqdmEqualTo(tQsb.getZqcode());
				criteria.andFxfsEqualTo(1);
				List<TZqxx> zqList = tzqxxMapper.selectByExample(example);
				String zy="";
				if(zqList!=null && zqList.size()>0) {
					zy = zqList.get(0).getZqjg();
				}
				
				int id = getSequenceByName.getSequenceByName("pz");
				TPzb tpzb = new TPzb();
				tpzb.setZy("非货币基金估值增值["+zy+"]");
				tpzb.setKjkm("6003");
				tpzb.setKmms("投资收益_基金_估值增值");
				tpzb.setBs("借");
				tpzb.setZtbh(ztbh);
				tpzb.setRq(dateByString);
				tpzb.setJe(tQsb.getGyjzbd());
				tpzb.setExtendf("4203");
				tpzb.setPzid(id);
				tpzb.setExtende("1004");
				pzList.add(tpzb);
				
				TPzb tpzb2 = new TPzb();
				tpzb2.setZy("非货币基金估值增值["+zy+"]");
				tpzb2.setKjkm("6103");
				tpzb2.setKmms("公允价值变动损益_基金");
				tpzb2.setBs("贷");
				tpzb2.setZtbh(ztbh);
				tpzb2.setRq(dateByString);
				tpzb2.setJe(tQsb.getGyjzbd());
				tpzb2.setExtendf("4203");
				tpzb2.setPzid(id);
				tpzb2.setExtende("1004");
				pzList.add(tpzb2);				
			}
			
			tpzbMapper.insertTPzbByBatch(pzList);
			
		}
		
	}

	private void hbjjhlcqPz(int ztbh, String date) {
		/*
		 * 借：应收红利
			贷：投资收益-基金投资收益
		 * */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", date));
		assist.setRequires(Assist.andEq("ywlb", 4103));
		List<TQsb> list = tqsbMapper.selectTQsb(assist);
		Date dateByString = null;
		try {
			dateByString = DateFormatUtil.getDateByString(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<TPzb> pzList = new ArrayList<TPzb>();
		if(list!=null && list.size()>0) {
			for(int i=0;i<list.size();i++) {
				TQsb tQsb = list.get(i);
				
				TZqxxExample example = new TZqxxExample();
				Criteria criteria = example.createCriteria();
				criteria.andZqdmEqualTo(tQsb.getZqcode());
				criteria.andFxfsEqualTo(2);
				List<TZqxx> zqList = tzqxxMapper.selectByExample(example);
				String zy="";
				if(zqList!=null && zqList.size()>0) {
					zy = zqList.get(0).getZqjg();
				}
				
				int id = getSequenceByName.getSequenceByName("pz");
				TPzb tpzb = new TPzb();
				tpzb.setZy("货币基金红利除权["+zy+"]");
				tpzb.setKjkm("1231");
				tpzb.setKmms("应收红利_基金");
				tpzb.setBs("借");
				tpzb.setZtbh(ztbh);
				tpzb.setRq(dateByString);
				tpzb.setJe(tQsb.getGyjzbd());
				tpzb.setExtendf("4103");
				tpzb.setPzid(id);
				tpzb.setExtende("1004");
				pzList.add(tpzb);
				
				TPzb tpzb2 = new TPzb();
				tpzb2.setZy("货币基金红利除权["+zy+"]");
				tpzb2.setKjkm("6003");
				tpzb2.setKmms("投资收益_基金");
				tpzb2.setBs("贷");
				tpzb2.setZtbh(ztbh);
				tpzb2.setRq(dateByString);
				tpzb2.setJe(tQsb.getGyjzbd());
				tpzb2.setExtendf("4103");
				tpzb2.setPzid(id);
				tpzb2.setExtende("1004");
				pzList.add(tpzb2);				
			}
			
			tpzbMapper.insertTPzbByBatch(pzList);
			
		}
		
	}

	private void fhbjjsgqrPz(int ztbh, String date) {
		/*
		 * 借：基金投资-成本
			     交易费用
			贷：证券清算款
			        应付交易费用
		 * */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", date));
		assist.setRequires(Assist.andEq("ywlb", 4202));
		List<TQsb> list = tqsbMapper.selectTQsb(assist);
		Date dateByString = null;
		try {
			dateByString = DateFormatUtil.getDateByString(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<TPzb> pzList = new ArrayList<TPzb>();
		if(list!=null && list.size()>0) {
			for(int i=0;i<list.size();i++) {
				TQsb tQsb = list.get(i);
				
				TZqxxExample example = new TZqxxExample();
				Criteria criteria = example.createCriteria();
				criteria.andZqdmEqualTo(tQsb.getZqcode());
				criteria.andFxfsEqualTo(1);
				List<TZqxx> zqList = tzqxxMapper.selectByExample(example);
				String zy="";
				if(zqList!=null && zqList.size()>0) {
					zy = zqList.get(0).getZqjg();
				}
				
				int id = getSequenceByName.getSequenceByName("pz");
				TPzb tpzb = new TPzb();
				tpzb.setZy("非货币基金申购确认["+zy+"]");
				tpzb.setKjkm(tQsb.getZqcode());				
				tpzb.setKmms("基金投资成本_"+zy);
				tpzb.setBs("借");
				tpzb.setZtbh(ztbh);
				tpzb.setRq(dateByString);
				tpzb.setJe(tQsb.getAmount());
				tpzb.setExtendf("4202");
				tpzb.setPzid(id);
				tpzb.setExtende("1004");
				pzList.add(tpzb);
				
				TPzb tpzb2 = new TPzb();
				tpzb2.setZy("非货币基金申购确认["+zy+"]");
				tpzb2.setKjkm("6203");
				tpzb2.setKmms("交易费用_基金");
				tpzb2.setBs("借");
				tpzb2.setZtbh(ztbh);
				tpzb2.setRq(dateByString);
				tpzb2.setJe(tQsb.getJsf()+tQsb.getZgf());
				tpzb2.setExtendf("4202");
				tpzb2.setPzid(id);
				tpzb2.setExtende("1004");
				pzList.add(tpzb2);
				
				TPzb tpzb3 = new TPzb();
				tpzb3.setZy("非货币基金申购确认["+zy+"]");
				tpzb3.setKjkm("1133");
				tpzb3.setKmms("证券清算款_基金");
				tpzb3.setBs("贷");
				tpzb3.setZtbh(ztbh);
				tpzb3.setRq(dateByString);
				tpzb3.setJe(tQsb.getZqqsk());
				tpzb3.setExtendf("4202");
				tpzb3.setPzid(id);
				tpzb3.setExtende("1004");
				pzList.add(tpzb3);
				
				TPzb tpzb4 = new TPzb();
				tpzb4.setZy("非货币基金申购确认["+zy+"]");
				tpzb4.setKjkm("2003");
				tpzb4.setKmms("应付交易费用_基金");
				tpzb4.setBs("贷");
				tpzb4.setZtbh(ztbh);
				tpzb4.setRq(dateByString);
				tpzb4.setJe(tQsb.getYj());
				tpzb4.setExtendf("4202");
				tpzb4.setPzid(id);
				tpzb4.setExtende("1004");
				pzList.add(tpzb4);
				
			}
			
			tpzbMapper.insertTPzbByBatch(pzList);
			
		}
	}

	private void hbjjsgqrPz(int ztbh, String date) {
		/*
		 * 借：基金投资-成本
			     交易费用
			贷：证券清算款
			        应付交易费用
		 * */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", date));
		assist.setRequires(Assist.andEq("ywlb", 4102));
		List<TQsb> list = tqsbMapper.selectTQsb(assist);
		Date dateByString = null;
		try {
			dateByString = DateFormatUtil.getDateByString(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<TPzb> pzList = new ArrayList<TPzb>();
		if(list!=null && list.size()>0) {
			for(int i=0;i<list.size();i++) {
				TQsb tQsb = list.get(i);
				
				TZqxxExample example = new TZqxxExample();
				Criteria criteria = example.createCriteria();
				criteria.andZqdmEqualTo(tQsb.getZqcode());
				criteria.andFxfsEqualTo(2);
				List<TZqxx> zqList = tzqxxMapper.selectByExample(example);
				String zy="";
				if(zqList!=null && zqList.size()>0) {
					zy = zqList.get(0).getZqjg();
				}
				
				int id = getSequenceByName.getSequenceByName("pz");
				TPzb tpzb = new TPzb();
				tpzb.setZy("货币基金申购确认["+zy+"]");
				tpzb.setKjkm(tQsb.getZqcode());
				tpzb.setKmms("基金投资成本_"+zy);
				tpzb.setBs("借");
				tpzb.setZtbh(ztbh);
				tpzb.setRq(dateByString);
				tpzb.setJe(tQsb.getAmount());
				tpzb.setExtendf("4102");
				tpzb.setPzid(id);
				tpzb.setExtende("1004");
				pzList.add(tpzb);
				
				TPzb tpzb2 = new TPzb();
				tpzb2.setZy("货币基金申购确认["+zy+"]");
				tpzb2.setKjkm("6203");
				tpzb2.setKmms("交易费用_基金");
				tpzb2.setBs("借");
				tpzb2.setZtbh(ztbh);
				tpzb2.setRq(dateByString);
				tpzb2.setJe(tQsb.getJsf()+tQsb.getZgf());
				tpzb2.setExtendf("4102");
				tpzb2.setPzid(id);
				tpzb2.setExtende("1004");
				pzList.add(tpzb2);
				
				TPzb tpzb3 = new TPzb();
				tpzb3.setZy("货币基金申购确认["+zy+"]");
				tpzb3.setKjkm("1133");
				tpzb3.setKmms("证券清算款_基金");
				tpzb3.setBs("贷");
				tpzb3.setZtbh(ztbh);
				tpzb3.setRq(dateByString);
				tpzb3.setJe(tQsb.getZqqsk());
				tpzb3.setExtendf("4102");
				tpzb3.setPzid(id);
				tpzb3.setExtende("1004");
				pzList.add(tpzb3);
				
				TPzb tpzb4 = new TPzb();
				tpzb4.setZy("货币基金申购确认["+zy+"]");
				tpzb4.setKjkm("2003");
				tpzb4.setKmms("应付交易费用_基金");
				tpzb4.setBs("贷");
				tpzb4.setZtbh(ztbh);
				tpzb4.setRq(dateByString);
				tpzb4.setJe(tQsb.getYj());
				tpzb4.setExtendf("4102");
				tpzb4.setPzid(id);
				tpzb4.setExtende("1004");
				pzList.add(tpzb4);
				
			}
			
			tpzbMapper.insertTPzbByBatch(pzList);
			
		}
		
		
	}

	private void fhbjjPz(int ztbh, String date) {
		/*
		 * 借：证券清算款
		 * 		贷：银行存款
		 * */
		
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", date));
		assist.setRequires(Assist.andEq("ywlb", 4201));
		List<TQsb> list = tqsbMapper.selectTQsb(assist);
		Date dateByString = null;
		try {
			dateByString = DateFormatUtil.getDateByString(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<TPzb> pzList = new ArrayList<TPzb>();
		if(list!=null && list.size()>0) {
			for(int i=0;i<list.size();i++) {
				TQsb tQsb = list.get(i);
				
				TZqxxExample example = new TZqxxExample();
				Criteria criteria = example.createCriteria();
				criteria.andZqdmEqualTo(tQsb.getZqcode());
				criteria.andFxfsEqualTo(1);
				List<TZqxx> zqList = tzqxxMapper.selectByExample(example);
				String zy="";
				if(zqList!=null && zqList.size()>0) {
					zy = zqList.get(0).getZqjg();
				}
				
				int id = getSequenceByName.getSequenceByName("pz");
				TPzb tpzb = new TPzb();
				tpzb.setZy("非货币基金申购申请["+zy+"]");
				tpzb.setKjkm("1131");
				tpzb.setKmms("证券清算款_基金");
				tpzb.setBs("借");
				tpzb.setZtbh(ztbh);
				tpzb.setRq(dateByString);
				tpzb.setJe(tQsb.getAmount());
				tpzb.setExtendf("4201");
				tpzb.setPzid(id);
				tpzb.setExtende("1004");
				pzList.add(tpzb);
				
				TPzb tpzb2 = new TPzb();
				tpzb2.setZy("非货币基金申购申请["+zy+"]");
				tpzb2.setKjkm("100201");
				tpzb2.setKmms("银行存款_活期存款");
				tpzb2.setBs("贷");
				tpzb2.setZtbh(ztbh);
				tpzb2.setRq(dateByString);
				tpzb2.setJe(tQsb.getAmount());
				tpzb2.setExtendf("4201");
				tpzb2.setPzid(id);
				tpzb2.setExtende("1004");
				pzList.add(tpzb2);				
			}
			
			tpzbMapper.insertTPzbByBatch(pzList);
			
		}
		
	}

	private void hbjjPz(int ztbh, String date) {
		/*
		 * 借：证券清算款
		 * 		贷：银行存款
		 * */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", date));
		assist.setRequires(Assist.andEq("ywlb", 4101));
		List<TQsb> list = tqsbMapper.selectTQsb(assist);
		Date dateByString = null;
		try {
			dateByString = DateFormatUtil.getDateByString(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<TPzb> pzList = new ArrayList<TPzb>();
		if(list!=null && list.size()>0) {
			for(int i=0;i<list.size();i++) {
				TQsb tQsb = list.get(i);
				
				TZqxxExample example = new TZqxxExample();
				Criteria criteria = example.createCriteria();
				criteria.andZqdmEqualTo(tQsb.getZqcode());
				criteria.andFxfsEqualTo(2);
				List<TZqxx> zqList = tzqxxMapper.selectByExample(example);
				String zy="";
				if(zqList!=null && zqList.size()>0) {
					zy = zqList.get(0).getZqjg();
				}
				
				int id = getSequenceByName.getSequenceByName("pz");
				TPzb tpzb = new TPzb();
				tpzb.setZy("货币基金申购申请["+zy+"]");
				tpzb.setKjkm("1131");
				tpzb.setKmms("证券清算款_基金");
				tpzb.setBs("借");
				tpzb.setZtbh(ztbh);
				tpzb.setRq(dateByString);
				tpzb.setJe(tQsb.getAmount());
				tpzb.setExtendf("4101");
				tpzb.setPzid(id);
				tpzb.setExtende("1004");
				pzList.add(tpzb);
				
				TPzb tpzb2 = new TPzb();
				tpzb2.setZy("货币基金申购申请["+zy+"]");
				tpzb2.setKjkm("100201");
				tpzb2.setKmms("银行存款_活期存款");
				tpzb2.setBs("贷");
				tpzb2.setZtbh(ztbh);
				tpzb2.setRq(dateByString);
				tpzb2.setJe(tQsb.getAmount());
				tpzb2.setExtendf("4101");
				tpzb2.setPzid(id);
				tpzb2.setExtende("1004");
				pzList.add(tpzb2);				
			}
			
			tpzbMapper.insertTPzbByBatch(pzList);
			
		}
		
	}
}
