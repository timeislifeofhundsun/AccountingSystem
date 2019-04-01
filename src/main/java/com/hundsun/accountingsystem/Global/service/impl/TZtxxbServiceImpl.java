package com.hundsun.accountingsystem.Global.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.VO.TZtxxbParamPojo;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TZtxxb;
import com.hundsun.accountingsystem.Global.bean.TZtxxbExample;
import com.hundsun.accountingsystem.Global.bean.TZtxxbExample.Criteria;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.TZtxxbMapper;
import com.hundsun.accountingsystem.Global.service.TZtxxbService;

@Service
public class TZtxxbServiceImpl implements TZtxxbService {
	
	@Autowired
	TZtxxbMapper tztxxbMapper;
	
	@Autowired
	TCcyebMapper tccyebMapper;

	@Override
	public void insertZt(TZtxxb tztxxb) throws Exception {
		/*
		 * 1.将账套信息存入t_ztxxb
		 * 2.获取账套信息中的初始金额和初始份额，分别对应到t_ccyeb的证券成本和持有数量
		 * 3.将会账套编号、计科目信息、发生日期、证券成本、持有数量存入t_ccyeb中
		 * */
		TZtxxbExample example=new TZtxxbExample();
		Criteria criteria=example.createCriteria();
		criteria.andNameEqualTo(tztxxb.getName());
		
		TZtxxbExample example1=new TZtxxbExample();
		Criteria criteria1=example1.createCriteria();
		criteria1.andJjdmEqualTo(tztxxb.getJjdm());
		
		List<TZtxxb> list = tztxxbMapper.selectByExample(example);
		List<TZtxxb> list1 = tztxxbMapper.selectByExample(example1);
		if(list.size()!=0) {
			throw new Exception("账套名称不能重复");
		}
		if(list1.size()!=0) {
			throw new Exception("基金代码不能重复");
		}
		else
		{
			int maxZtbh = tztxxbMapper.getMaxZtbh();
			List<TCcyeb> tccyebList = new ArrayList<TCcyeb>();
			TCcyeb tccyeb = new TCcyeb();
			tccyeb.setZtbh(maxZtbh+1);
			tccyeb.setKjkmdm("100201");
			tccyeb.setFsrq(tztxxb.getCreatedate());
			tccyeb.setZqcb(tztxxb.getMoney());
			tccyeb.setExtenda("银行存款_活期存款");						
			tccyebList.add(tccyeb);
			
			TCcyeb tccyeb1 = new TCcyeb();
			tccyeb1.setZtbh(maxZtbh+1);
			tccyeb1.setKjkmdm("400101");
			tccyeb1.setFsrq(tztxxb.getCreatedate());
			tccyeb1.setCysl(tztxxb.getNumber());
			tccyeb1.setZqcb(tztxxb.getMoney());
			tccyeb1.setExtenda("实收基金_已实现");			
			tccyebList.add(tccyeb1);
			
			TCcyeb tccyeb2 = new TCcyeb();
			tccyeb2.setKjkmdm("6201");
			tccyeb2.setZtbh(maxZtbh+1);
			tccyeb2.setZqcb(0.0);
			tccyeb2.setFsrq(tztxxb.getCreatedate());
			tccyeb2.setExtenda("交易费用_股票");
			tccyebList.add(tccyeb2);
			
			TCcyeb tccyeb3 = new TCcyeb();
			tccyeb3.setKjkmdm("6202");
			tccyeb3.setZtbh(maxZtbh+1);
			tccyeb3.setZqcb(0.0);
			tccyeb3.setFsrq(tztxxb.getCreatedate());
			tccyeb3.setExtenda("交易费用_回购");
			tccyebList.add(tccyeb3);

			TCcyeb tccyeb4 = new TCcyeb();
			tccyeb4.setKjkmdm("6203");
			tccyeb4.setZtbh(maxZtbh+1);
			tccyeb4.setZqcb(0.0);
			tccyeb4.setFsrq(tztxxb.getCreatedate());
			tccyeb4.setExtenda("交易费用_基金");
			tccyebList.add(tccyeb4);
			
			TCcyeb tccyeb5 = new TCcyeb();
			tccyeb5.setKjkmdm("1131");
			tccyeb5.setZtbh(maxZtbh+1);
			tccyeb5.setZqcb(0.0);
			tccyeb5.setFsrq(tztxxb.getCreatedate());
			tccyeb5.setExtenda("证券清算款_股票");
			tccyebList.add(tccyeb5);
			
			TCcyeb tccyeb6 = new TCcyeb();
			tccyeb6.setKjkmdm("1132");
			tccyeb6.setZtbh(maxZtbh+1);
			tccyeb6.setZqcb(0.0);
			tccyeb6.setFsrq(tztxxb.getCreatedate());
			tccyeb6.setExtenda("证券清算款_回购");
			tccyebList.add(tccyeb6);
			
			TCcyeb tccyeb7 = new TCcyeb();
			tccyeb7.setKjkmdm("1133");
			tccyeb7.setZtbh(maxZtbh+1);
			tccyeb7.setZqcb(0.0);
			tccyeb7.setFsrq(tztxxb.getCreatedate());
			tccyeb7.setExtenda("证券清算款_基金");
			tccyebList.add(tccyeb7);
			
			TCcyeb tccyeb8 = new TCcyeb();
			tccyeb8.setKjkmdm("2001");
			tccyeb8.setZtbh(maxZtbh+1);
			tccyeb8.setZqcb(0.0);
			tccyeb8.setFsrq(tztxxb.getCreatedate());
			tccyeb8.setExtenda("应付交易费用_股票");
			tccyebList.add(tccyeb8);
			
			TCcyeb tccyeb9 = new TCcyeb();
			tccyeb9.setKjkmdm("2002");
			tccyeb9.setZtbh(maxZtbh+1);
			tccyeb9.setZqcb(0.0);
			tccyeb9.setFsrq(tztxxb.getCreatedate());
			tccyeb9.setExtenda("应付交易费用_回购");
			tccyebList.add(tccyeb9);
			
			TCcyeb tccyeb10 = new TCcyeb();
			tccyeb10.setKjkmdm("2003");
			tccyeb10.setZtbh(maxZtbh+1);
			tccyeb10.setZqcb(0.0);
			tccyeb10.setFsrq(tztxxb.getCreatedate());
			tccyeb10.setExtenda("应付交易费用_基金");
			tccyebList.add(tccyeb10);
			
			TCcyeb tccyeb11 = new TCcyeb();
			tccyeb11.setKjkmdm("6001");
			tccyeb11.setZtbh(maxZtbh+1);
			tccyeb11.setZqcb(0.0);
			tccyeb11.setFsrq(tztxxb.getCreatedate());
			tccyeb11.setExtenda("投资收益_股票");
			tccyebList.add(tccyeb11);
			
			TCcyeb tccyeb12 = new TCcyeb();
			tccyeb12.setKjkmdm("6002");
			tccyeb12.setZtbh(maxZtbh+1);
			tccyeb12.setZqcb(0.0);
			tccyeb12.setFsrq(tztxxb.getCreatedate());
			tccyeb12.setExtenda("投资收益_回购");
			tccyebList.add(tccyeb12);
			
			TCcyeb tccyeb13 = new TCcyeb();
			tccyeb13.setKjkmdm("6003");
			tccyeb13.setZtbh(maxZtbh+1);
			tccyeb13.setZqcb(0.0);
			tccyeb13.setFsrq(tztxxb.getCreatedate());
			tccyeb13.setExtenda("投资收益_基金");
			tccyebList.add(tccyeb13);
			
			tccyebMapper.insertTCcyebByBatch(tccyebList);
			tztxxbMapper.insert(tztxxb);
		}
		
	}

	@Override
	public boolean updateZt(TZtxxb tztxxb) throws Exception {
		TZtxxbExample example=new TZtxxbExample();
		Criteria criteria=example.createCriteria();
		criteria.andZtbhEqualTo(tztxxb.getZtbh());	
		
		TZtxxbExample example1=new TZtxxbExample();
		Criteria criteria1=example1.createCriteria();
		criteria1.andNameEqualTo(tztxxb.getName());
		TZtxxb bean = tztxxbMapper.selectByPrimaryKey(tztxxb.getZtbh());
		if(tztxxb.getName().equals(bean.getName())) {
			tztxxbMapper.updateByExample(tztxxb, example);
		}else {
			List<TZtxxb> list = tztxxbMapper.selectByExample(example1);
			if(list.size()!=0) {
				throw new Exception("账套名称重复了");
			}
			tztxxbMapper.updateByExample(tztxxb, example);
		}	
		return true;
	}

	@Override
	public List<TZtxxb> findZtList() {		
		return tztxxbMapper.selectByExample(null);
	}

	@Override
	public TZtxxb findZtById(int ztbh) {
		return tztxxbMapper.selectByPrimaryKey(ztbh);
	}

	@Override
	public void deleteZtById(int ztbh) throws Exception {
		try {
			tztxxbMapper.deleteByPrimaryKey(ztbh);
		} catch (Exception e) {
			throw new Exception("删除失败！");
		}		
		
	}

	@Override
	public List<TZtxxb> findZtListByPage(int page, int limit) {
		int start = (page-1)*limit;
		int end = limit;
		TZtxxbParamPojo params = new TZtxxbParamPojo();
		params.setStart(start);
		params.setEnd(end);
		return tztxxbMapper.selectByLimit(params);
	}

	@Override
	public int getCounts() {		
		return tztxxbMapper.countByExample(null);
	}
	
	
}
