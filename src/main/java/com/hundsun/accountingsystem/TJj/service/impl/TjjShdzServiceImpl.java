package com.hundsun.accountingsystem.TJj.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.VO.TQsbParamPojo;
import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.TJj.service.TjjSgsqService;
import com.hundsun.accountingsystem.TJj.service.TjjShdzService;


@Service
public class TjjShdzServiceImpl implements TjjShdzService {

	@Autowired
	TQsbMapper tqsbMapper;
	
	@Autowired
	TjjSgsqService tjjSgServiceImpl;
	
	private double yhck;
	private double zqqsk;
	private String yhckid;
	private String zqqskid;
	
	@Override
	public List<TQsb> selectByPage(int page, int limit) {
		TQsbParamPojo params = new TQsbParamPojo();
		params.setStart((page-1)*limit);
		params.setEnd(limit);
		return tqsbMapper.selectShxxByYwlbAndLimit(params);
	}
	
	@Override
	public int getCounts() {
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ywlb", "4105"));
		assist.setRequires(Assist.orEq("ywlb", "4205"));
		return (int)tqsbMapper.getTQsbRowCount(assist);
	}

	@Override
	public List<TQsb> selectByPageAndZtbh(int page, int limit, int ztbh) {
		TQsbParamPojo params = new TQsbParamPojo();
		params.setStart((page-1)*limit);
		params.setEnd(limit);
		params.setZtbh(ztbh);
		return tqsbMapper.selectShxxByPageAndZtbh(params);
	}

	@Override
	public int getCountsByZtbh(int ztbh) {
		TQsbParamPojo params = new TQsbParamPojo();
		params.setZtbh(ztbh);
		return tqsbMapper.getShxxCountsByZtbh(params);
	}

	@Override
	public List<TQsb> selectByPageAndDate(int page, int limit, String date) {
		TQsbParamPojo params = new TQsbParamPojo();
		params.setStart((page-1)*limit);
		params.setEnd(limit);
		params.setDate(date);
		return tqsbMapper.selectShxxByPageAndDate(params);
	}

	@Override
	public int getCountsByDate(String date) {
		TQsbParamPojo params = new TQsbParamPojo();
		params.setDate(date);
		return tqsbMapper.getShxxCountsByDate(params);
	}

	@Override
	public void insertShxxAndUpdateCcye(TQsb tqsb) throws Exception {
		/*
		 * 为了完成申购信息的确认，我们必须要做的操作有
		 * 1.解析页面传递过来的数据信息
		 * 2.更新赎回数据的状态为已确认状态
		 * 3.将申购确认的信息插入到清算表中
		 * 4.同步更新余额表（赎回到账的时候是不会变更持仓表的，持仓表的变动咋赎回确认的时候已经变更了）
		 * */
		
		//解析数据构建清算表对象并插入到清算表中
		boolean flag = insertIntoTqsb(tqsb);
		if(!flag) {
			throw new Exception("插入到清算表中出错");
		}
		
		//更新赎回确认信息的状态为以确认状态
		boolean flag2 = updateShxxStatus(tqsb);
		if(!flag2) {
			throw new Exception("更新赎回数据状态出错");
		}
		//查询出对应账套的余额信息
		selectCcyebAmount(tqsb.getZtbh());
		
		double money = tqsb.getZqqsk();
		//更新余额表
		boolean flag3 = updateCcyeb(tqsb.getZtbh(),money,tqsb.getRq());
		if(!flag3) {
			throw new Exception("更新余额表时出错");
		}
	}

	
	private void selectCcyebAmount(int ztbh) {
		Map<String,Double> map = tjjSgServiceImpl.getCcyebXx(ztbh);
		yhck=map.get("100201");
		zqqsk=map.get("1133");
		zqqskid = map.get("zqqskid")+"";
		yhckid = map.get("yhckid")+"";
	}
	
	private boolean updateCcyeb(int ztbh,double money,Date rq) throws Exception{
		yhck+=money;
		zqqsk-=money;
		TCcyeb tyhck = new TCcyeb();
		tyhck.setKjkmdm("100201");
		tyhck.setExtenda("银行存款_活期存款");
		tyhck.setZqcb(yhck);
		tyhck.setZtbh(ztbh);
		tyhck.setFsrq(rq);	
		tyhck.setId(Integer.parseInt(yhckid.substring(0,yhckid.length()-2)));
		
		TCcyeb tzqqsk = new TCcyeb();
		tzqqsk.setKjkmdm("1133");
		tzqqsk.setExtenda("证券清算款");
		tzqqsk.setZqcb(zqqsk);
		tzqqsk.setZtbh(ztbh);
		tzqqsk.setFsrq(rq);
		tzqqsk.setId(Integer.parseInt(zqqskid.substring(0,zqqskid.length()-2)));
		
		try {
			tjjSgServiceImpl.updateCcyeb(tyhck);
			tjjSgServiceImpl.updateCcyeb(tzqqsk);
		}catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	
	private boolean updateShxxStatus(TQsb tqsb) {
		TQsb tqsbAfter = tqsbMapper.selectTQsbById(tqsb.getId());
		tqsbAfter.setExtendf("1");
		try {
			tqsbMapper.updateTQsbById(tqsbAfter);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	private boolean insertIntoTqsb(TQsb tqsbBefore) {
		TQsb tqsb = new TQsb();
		tqsb.setZtbh(tqsbBefore.getZtbh());
		tqsb.setRq(tqsbBefore.getRq());
		tqsb.setZqcode(tqsbBefore.getZqcode());
		int ywlb = tqsbBefore.getYwlb();
		if(ywlb == 4105) {
			tqsb.setYwlb(4106);
		}else {
			tqsb.setYwlb(4206);
		}
		tqsb.setBs("S");
		tqsb.setExtendc("402");
		tqsb.setZqqsk(tqsbBefore.getZqqsk());
		try {
			tqsbMapper.insertTQsb(tqsb);
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}
