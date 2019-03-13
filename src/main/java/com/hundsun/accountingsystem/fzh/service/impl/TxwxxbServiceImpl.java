package com.hundsun.accountingsystem.fzh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.bean.TXwxxb;
import com.hundsun.accountingsystem.Global.bean.TXwxxbExample;
import com.hundsun.accountingsystem.Global.bean.TXwxxbExample.Criteria;
import com.hundsun.accountingsystem.Global.mapper.TXwxxbMapper;
import com.hundsun.accountingsystem.fzh.service.TxwxxbService;

@Service
public class TxwxxbServiceImpl implements TxwxxbService {

	@Autowired
	TXwxxbMapper txwxxbMapper;
	
	@Override
	public void insertXw(TXwxxb txwxxb) throws Exception {
		TXwxxbExample example=new TXwxxbExample();
		Criteria criteria = example.createCriteria();
		criteria.andXwbhEqualTo(txwxxb.getXwbh());
		List<TXwxxb> list = txwxxbMapper.selectByExample(example);
		if(list.size()!=0) {
			throw new Exception("席位编号不能重复!");
		}else {
			txwxxbMapper.insert(txwxxb);
		}
	}

	@Override
	public boolean updateXw(TXwxxb txwxxb) {
		TXwxxbExample example=new TXwxxbExample();
		Criteria criteria = example.createCriteria();
		criteria.andXwbhEqualTo(txwxxb.getXwbh());
		txwxxbMapper.updateByExampleSelective(txwxxb, example);
		return true;
	}

	@Override
	public List<TXwxxb> findXwList() {
		return txwxxbMapper.selectByExample(null);
	}

	@Override
	public TXwxxb findXwById(String xwbh) {
		return txwxxbMapper.selectByPrimaryKey(xwbh);
	}

	@Override
	public void deleteXwById(String xwbh) throws Exception {
		try {
			txwxxbMapper.deleteByPrimaryKey(xwbh);
		} catch (Exception e) {
			throw new Exception("删除失败！");
		}

	}

}
