package com.hundsun.accountingsystem.TJj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.THqb;
import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.bean.TZqxx;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.THqbMapper;
import com.hundsun.accountingsystem.Global.mapper.TJyflMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.service.TZqxxService;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.TJj.VO.TCcyebSh;
import com.hundsun.accountingsystem.TJj.service.TjjShqrService;

@Service
public class TjjShqrServiceImpl implements TjjShqrService {

	@Autowired
	TCcyebMapper tccyebMapper;
	
	@Autowired
	TZqxxService tzqxxServiceImpl;
	
	@Autowired
	TJyflMapper tjyfMapper;
	
	@Autowired
	THqbMapper thqbMapper;
	
	@Autowired
	TQsbMapper tqsbMapper;
	
	double ljgz;
	
	@Override
	public List<TCcyeb> selectByPage(int page, int limit) {
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("zqnm", 4));
		assist.setStartRow((page-1)*limit);
		assist.setRowSize(limit);
		return tccyebMapper.selectTCcyeb(assist);
	}

	@Override
	public int getCountsByZqnm(int zqnm) {
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("zqnm", zqnm));
		
		return (int) tccyebMapper.getTCcyebRowCount(assist);
	}

	@Override
	public List<TCcyeb> selectByPageAndZtbh(int page, int limit, int ztbh) {
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("zqnm", 4));
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setStartRow((page-1)*limit);
		assist.setRowSize(limit);
		return tccyebMapper.selectTCcyeb(assist);
		
	}

	@Override
	public int getCountsByZtbh(int ztbh) {
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("zqnm", 4));
		return (int) tccyebMapper.getTCcyebRowCount(assist);
	}

	@Override
	public List<TCcyeb> selectByPageAndDate(int page, int limit, String date) {
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("zqnm", 4));
		assist.setRequires(Assist.andEq("fsrq", date));
		assist.setStartRow((page-1)*limit);
		assist.setRowSize(limit);
		return tccyebMapper.selectTCcyeb(assist);
	}

	@Override
	public int getCountsByDate(String date) {
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("zqnm", 4));
		assist.setRequires(Assist.andEq("fsrq", date));
		return (int) tccyebMapper.getTCcyebRowCount(assist);
	}

	@Override
	public void confirmShxx(TCcyebSh tccyebSh) throws Exception {
		/*
		 * 1.计算出各种费用到并插入到清算表中（证券清算款，需要得到收盘价），经手费，证管费，应付交易费用（佣金）、证券成本（金额）
		 * 2.同步更新持仓表（计算估值增值、计算证券成本、计算持有数量）
		 * 3.同步更新余额表（计算证券清算款、计算交易费用、计算投资收益、应付交易费用）
		 * */
		
		//同步更新持仓表
		boolean flag2 = updateTccyeb(tccyebSh);
		if(!flag2) {
			throw new Exception("更新持仓表时出错");
		}
			
		//插入到清算表中
		boolean flag = insertIntoTqsb(tccyebSh);
		if(!flag) {
			throw new Exception("请检查该基金的当日行情信息以及赎回费率信息是否存在");
		}
		//同步更新到余额表中（计算证券清算款、计算交易费用、计算投资收益、应付交易费用）
		boolean flag3 = updateYe(tccyebSh);
		if(!flag3) {
			throw new Exception("更新余额表时出错");
		}
		
	}
	
	//同步更新到余额表中（计算证券清算款、计算交易费用、计算投资收益、应付交易费用）
	private boolean updateYe(TCcyebSh tccyebSh) {
		TJyfl shfl = tjyfMapper.selectByPrimaryKey(4102);
		//获取上一日收盘价(系统中是根据今天的日期去获取到收盘价的)
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("zqdm", tccyebSh.getZqdm()));
		assist.setRequires(Assist.andEq("zqnm", 4));
		assist.setRequires(Assist.andEq("hqrq", DateFormatUtil.getStringByDate(tccyebSh.getFsrq())));
		List<THqb> tHqbList = thqbMapper.selectTHqb(assist);
		double hq = 0.0;
		if(tHqbList!=null && tHqbList.size()>0) {
			hq = tHqbList.get(0).getJrsp();
		}else {
			return false;
		}
		double amount = (tccyebSh.getZqcb()/tccyebSh.getCysl())*tccyebSh.getShfe();	
		double zqqsk = tccyebSh.getShfe()*hq;
		double jyfy = zqqsk*shfl.getJsfl()+zqqsk*shfl.getZg();
		double yfjyfy = zqqsk*shfl.getYj()-jyfy;		
		double tzsy = (zqqsk + jyfy)-(amount + ljgz + yfjyfy);
		
		//更新证券清算款
		Assist assist1 = new Assist();
		assist1.setRequires(Assist.andEq("ztbh", tccyebSh.getZtbh()));
		assist1.setRequires(Assist.andEq("kjkmdm", "1133"));
		List<TCcyeb> zqList = tccyebMapper.selectTCcyeb(assist1);
		if(zqList!=null && zqList.size()>0) {
			TCcyeb tCcyeb = zqList.get(0);
			double zqcb = tCcyeb.getZqcb()+zqqsk;
			tCcyeb.setZqcb(zqcb);
			tccyebMapper.updateTCcyebById(tCcyeb);
		}else {
			return false;
		}
		
		//更新交易费用
		Assist assist2 = new Assist();
		assist2.setRequires(Assist.andEq("ztbh", tccyebSh.getZtbh()));
		assist2.setRequires(Assist.andEq("kjkmdm", "6203"));
		List<TCcyeb> jyList = tccyebMapper.selectTCcyeb(assist2);
		if(jyList!=null && jyList.size()>0) {
			TCcyeb tCcyeb = jyList.get(0);
			double zqcb = tCcyeb.getZqcb()+jyfy;
			tCcyeb.setZqcb(zqcb);
			tccyebMapper.updateTCcyebById(tCcyeb);
		}else {
			return false;
		}
		
		//更新应付交易费用
		Assist assist3 = new Assist();
		assist3.setRequires(Assist.andEq("ztbh", tccyebSh.getZtbh()));
		assist3.setRequires(Assist.andEq("kjkmdm", "2003"));
		List<TCcyeb> yfList = tccyebMapper.selectTCcyeb(assist3);
		if(yfList!=null && yfList.size()>0) {
			TCcyeb tCcyeb = yfList.get(0);
			double zqcb = tCcyeb.getZqcb()+yfjyfy;
			tCcyeb.setZqcb(zqcb);
			tccyebMapper.updateTCcyebById(tCcyeb);
		}else {
			return false;
		}
		
		//更新投资收益
		Assist assist4 = new Assist();
		assist4.setRequires(Assist.andEq("ztbh", tccyebSh.getZtbh()));
		assist4.setRequires(Assist.andEq("kjkmdm", "6003"));
		List<TCcyeb> tzList = tccyebMapper.selectTCcyeb(assist4);
		if(tzList!=null && tzList.size()>0) {
			TCcyeb tCcyeb = tzList.get(0);
			double zqcb = tCcyeb.getZqcb()+tzsy;
			tCcyeb.setZqcb(zqcb);
			tccyebMapper.updateTCcyebById(tCcyeb);
		}else {
			return false;
		}
		return true;
	}

	private boolean updateTccyeb(TCcyebSh tccyebSh) {
		//先从持仓表中查询出持仓数据
		TCcyeb tccyeb = tccyebMapper.selectTCcyebById(tccyebSh.getId());
		//更新持仓表中的：累计估增字段、证券成本字段、持有数量字段
		//赎回的时候累计估增采用平均值算法来计算
		ljgz = (tccyeb.getLjgz()/tccyeb.getCysl())*tccyebSh.getShfe();
		double zqcb = (tccyeb.getZqcb()/tccyeb.getCysl())*tccyebSh.getShfe();
		int cysl = tccyeb.getCysl() - tccyebSh.getShfe();
		tccyeb.setLjgz(tccyeb.getLjgz()-ljgz);
		tccyeb.setZqcb(tccyeb.getZqcb()-zqcb);
		tccyeb.setCysl(cysl);
		tccyeb.setFsrq(tccyebSh.getFsrq());
		try {
			tccyebMapper.updateTCcyebById(tccyeb);
		}catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	
	private boolean insertIntoTqsb(TCcyebSh tccyebSh){
		TQsb tqsb = new TQsb();
		tqsb.setZtbh(tccyebSh.getZtbh());
		tqsb.setRq(tccyebSh.getFsrq());
		tqsb.setZqcode(tccyebSh.getZqdm());
		tqsb.setBs("S");
		tqsb.setQuantity(tccyebSh.getShfe());
		List<TZqxx> list = tzqxxServiceImpl.findByZqdm(tqsb.getZqcode());
		if(list!=null && list.size()==1) {
			int flag = list.get(0).getFxfs();
			if(flag==1) {
				tqsb.setYwlb(4205);
			}else if(flag==2){
				tqsb.setYwlb(4105);
			}
		}
		
		//计算赎回成本=上一时刻总成本/上一时刻总数量 * 赎回份额
		double amount = (tccyebSh.getZqcb()/tccyebSh.getCysl())*tccyebSh.getShfe();
		tqsb.setAmount(amount);
		
		//获取费率，计算经手费、证管费、应付交易费用（佣金）、证券清算款
		TJyfl shfl = tjyfMapper.selectByPrimaryKey(4102);
		if(null==shfl) {
			return false;
		}
		//获取当日收盘价
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("zqdm", tccyebSh.getZqdm()));
		assist.setRequires(Assist.andEq("zqnm", 4));
		assist.setRequires(Assist.andEq("hqrq", DateFormatUtil.getStringByDate(tccyebSh.getFsrq())));
		List<THqb> tHqbList = thqbMapper.selectTHqb(assist);
		double hq = 0.0;
		if(tHqbList!=null && tHqbList.size()>0) {
			hq = tHqbList.get(0).getJrsp();
		}else {
			return false;
		}
		
		//计算证券清算款（成交金额）
		double zqqsk = tccyebSh.getShfe()*hq;
		tqsb.setZqqsk(zqqsk);
		
		double jsf = zqqsk*shfl.getJsfl();
		double zgf = zqqsk*shfl.getZg();
		double yfjyfy = zqqsk*shfl.getYj() - jsf - zgf;
		tqsb.setJsf(jsf);
		tqsb.setZgf(zgf);
		tqsb.setYj(yfjyfy);
		tqsb.setExtendc("402");
		tqsb.setExtendf("0");
		tqsb.setGyjzbd(ljgz);
		try {
			tqsbMapper.insertTQsb(tqsb);
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
