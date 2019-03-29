package com.hundsun.accountingsystem.TGp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.hundsun.accountingsystem.TGp.service.GPPZService;

/**
 * 
* <p>
* Description:GPPZService的实现
* <p>
* Company: SMARTLAB 
* @author gaozhen
* @date 2019年3月20日
* @Version 1.1
 */
@Service
public class GPPZServiceImpl implements GPPZService {

	@Autowired
	private TQsbMapper tQsbMapper;
	
	@Autowired
	private TSequenceService sequenceService;
	
	@Autowired
	private TZqxxMapper zqxxMapper;
	
	@Autowired
	private TPzbMapper pzbMapper;
	
	private Map<String, String> zqxxMap;
	
	@Override
	public boolean insertGPPZ(int ztbh, Date ywrq) throws Exception {
		boolean res = false;
		List<TPzb> needInsertPzs = null;
		//删除旧凭证
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq",ywrq));
		assist.setRequires(Assist.andEq("extendf", 11));
		pzbMapper.deleteTPzb(assist);
		//加载证券信息
		this.loadZqxxMap();
		//股票买入
		needInsertPzs = this.insertGpmrpz(ztbh, ywrq);
		//股票卖出
		needInsertPzs.addAll(this.insertGpmcpz(ztbh, ywrq));
		//估值增值
		needInsertPzs.addAll(this.insertGzpz(ztbh, ywrq));
		//红利到账
		needInsertPzs.addAll(this.insertHldzPz(ztbh,ywrq));
		//送股
		needInsertPzs.addAll(this.insertSGPz(ztbh,ywrq));
		//插入数据库
		if (needInsertPzs.size()>0) {
			int effect = pzbMapper.insertTPzbByBatch(needInsertPzs);
			if(effect!=needInsertPzs.size()) {
				throw new Exception("股票生成凭证异常");
			}
		}
		res = true;
		return res;
	}
	
	/**
	 * 
	* @Description:生成股票买入凭证
	* @param  参数说明
	* @return boolean    返回类型
	* @author gaozhen
	 */
	private List<TPzb> insertGpmrpz(int ztbh, Date ywrq){
		List<TPzb> gpmrpzs = new ArrayList<>();
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", DateFormatUtil.getStringByDate(ywrq)));
		assist.setRequires(Assist.andEq("ywlb", 1101));
		List<TQsb> qsbs = tQsbMapper.selectTQsb(assist);
		for (TQsb tQsb : qsbs) {
			String zhaiyao = "["+DateFormatUtil.getStringByDate(ywrq)+"]买入股票["
				    +tQsb.getZqcode()+"]"+"["+zqxxMap.get(tQsb.getZqcode())+"]";
			int pzid = sequenceService.getSequenceByName("pz");
			TPzb gpcb = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "借", pzid,null //kjkm
					, ztbh, "股票投资成本", tQsb.getCost()
					,zhaiyao,ywrq);
			TPzb jyfy = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "借", pzid,null //kjkm
					, ztbh, "交易费用", tQsb.getLumpsum()
					,zhaiyao,ywrq);
			TPzb zqqsk = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "贷", pzid,null //kjkm
					, ztbh, "证券清算款", tQsb.getZqqsk()
					,zhaiyao,ywrq);
			TPzb yj = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "贷", pzid,null //kjkm
					, ztbh, "应付佣金", tQsb.getYj()
					,zhaiyao,ywrq);
			gpmrpzs.add(gpcb);
			gpmrpzs.add(jyfy);
			gpmrpzs.add(zqqsk);
			gpmrpzs.add(yj);
		}
		return gpmrpzs;
	}
	
	/**
	 * 
	* @Description: 插入股票卖出凭证
	* @param  参数说明
	* @return List<TPzb>    返回类型
	* @author gaozhen
	 */
	private List<TPzb> insertGpmcpz(int ztbh, Date ywrq){
		List<TPzb> gpmcpzs = new ArrayList<>();
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", DateFormatUtil.getStringByDate(ywrq)));
		assist.setRequires(Assist.andEq("ywlb", 1102));
		List<TQsb> qsbs = tQsbMapper.selectTQsb(assist);
		for (TQsb tQsb : qsbs) {
			String zy = "["+DateFormatUtil.getStringByDate(ywrq)+"]卖出股票["
				    +tQsb.getZqcode()+"]"+"["+zqxxMap.get(tQsb.getZqcode())+"]";
			int pzid = sequenceService.getSequenceByName("pz");
			/**
			 * 卖出股票凭证
			 */
			TPzb zqqsk = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "借", pzid,null //kjkm
					, ztbh, "证券清算款", tQsb.getZqqsk()
					,zy,ywrq);
			TPzb jyfy = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "借", pzid,null //kjkm
					, ztbh, "交易费用", tQsb.getLumpsum()
					,zy,ywrq);
			TPzb mccb = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "贷", pzid,null //kjkm
					, ztbh, "卖出股票成本", tQsb.getCost()
					,zy,ywrq);
			TPzb mcgz = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "贷", pzid,null //kjkm
					, ztbh, "卖出估值增值", tQsb.getGyjzbd()
					,zy,ywrq);
			TPzb yj = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "贷", pzid,null //kjkm
					, ztbh, "应付佣金", tQsb.getYj()
					,zy,ywrq);
			TPzb phs = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "贷", pzid,null //kjkm
					, ztbh, "差价收入", tQsb.getCjsr()
					,zy,ywrq);
			/**
			 * 公允价值结转
			 */	
			zy = "["+DateFormatUtil.getStringByDate(ywrq)+"]卖出股票["
				    +tQsb.getZqcode()+"]"+"["+zqxxMap.get(tQsb.getZqcode())+"]"
				    +"公允价值结转";
			pzid = sequenceService.getSequenceByName("pz");
			TPzb gyjzbdsy = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "贷", pzid,null //kjkm
					, ztbh, "公允价值变动损益", -tQsb.getCjsr()
					,zy,ywrq);
			TPzb jzphs = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "贷", pzid,null //kjkm
					, ztbh, "股票差价收入", tQsb.getCjsr()
					,zy,ywrq);
			
			gpmcpzs.add(zqqsk);
			gpmcpzs.add(jyfy);
			gpmcpzs.add(mccb);
			gpmcpzs.add(mcgz);
			gpmcpzs.add(yj);
			gpmcpzs.add(phs);
			gpmcpzs.add(gyjzbdsy);
			gpmcpzs.add(jzphs);
		}
		return gpmcpzs;
	}
	
	/**
	 * 
	* @Description: 插入估值增值凭证
	* @param  参数说明
	* @return List<TPzb>    返回类型
	* @author gaozhen
	 */
	private List<TPzb> insertGzpz(int ztbh, Date ywrq){
		List<TPzb> gzpzs = new ArrayList<>();
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", DateFormatUtil.getStringByDate(ywrq)));
		assist.setRequires(Assist.andEq("ywlb", 1103));
		List<TQsb> qsbs = tQsbMapper.selectTQsb(assist);
		for (TQsb tQsb : qsbs) {
			String zhaiyao = "["+DateFormatUtil.getStringByDate(ywrq)+"]股票估值增值["
				    +tQsb.getZqcode()+"]"+"["+zqxxMap.get(tQsb.getZqcode())+"]";
			int pzid = sequenceService.getSequenceByName("pz");
			TPzb gz = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "借", pzid,null //kjkm
					, ztbh, "A股估值增值", tQsb.getGyjzbd()
					,zhaiyao,ywrq);
			TPzb gyjzbd = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "贷", pzid,null //kjkm
					, ztbh, "公允价值变动损益",tQsb.getGyjzbd()
					,zhaiyao,ywrq);
			gzpzs.add(gz);
			gzpzs.add(gyjzbd);
		}
		return gzpzs;
	}

	/**
	 * 插入红利到账凭证
	 * @param ztbh
	 * @param ywrq
	 * @return
	 */
	private List<TPzb> insertHldzPz(int ztbh,Date ywrq){
		List<TPzb> gzpzs = new ArrayList<>();
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("extenda", DateFormatUtil.getStringByDate(ywrq)));
		assist.setRequires(Assist.andEq("ywlb", 1202));
		List<TQsb> qsbs = tQsbMapper.selectTQsb(assist);
		for (TQsb tQsb : qsbs) {
			String zhaiyao = "["+DateFormatUtil.getStringByDate(ywrq)+"]股票红利到账["
					+tQsb.getZqcode()+"]"+"["+zqxxMap.get(tQsb.getZqcode())+"]";
			int pzid = sequenceService.getSequenceByName("pz");
			TPzb yhck = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "借", pzid,null //kjkm
					, ztbh, "银行存款", tQsb.getAmount()
					,zhaiyao,ywrq);
			TPzb hlsr = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "贷", pzid,null //kjkm
					, ztbh, "股票红利收入",tQsb.getAmount()
					,zhaiyao,ywrq);
			gzpzs.add(yhck);
			gzpzs.add(hlsr);
		}
		return gzpzs;
	}

	/**
	 * 插入送股凭证
	 * @param ztbh
	 * @param ywrq
	 * @return
	 */
	private List<TPzb> insertSGPz(int ztbh,Date ywrq){
		List<TPzb> gzpzs = new ArrayList<>();
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("extenda", DateFormatUtil.getStringByDate(ywrq)));
		assist.setRequires(Assist.andEq("ywlb", 1203));
		List<TQsb> qsbs = tQsbMapper.selectTQsb(assist);
		for (TQsb tQsb : qsbs) {
			String zhaiyao = "["+DateFormatUtil.getStringByDate(ywrq)+"]股票送股["
					+tQsb.getZqcode()+"]"+"["+zqxxMap.get(tQsb.getZqcode())+"]";
			int pzid = sequenceService.getSequenceByName("pz");
			TPzb tzcb1 = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "借", pzid,null //kjkm
					, ztbh, "股票投资成本", 0.01
					,zhaiyao,ywrq);
			TPzb tzcb2 = new TPzb(null, "11", null, null, null, null, null //id,extenda=11(类别),extend
					, "借", pzid,null //kjkm
					, ztbh, "股票红利收入",-0.01
					,zhaiyao,ywrq);
			gzpzs.add(tzcb1);
			gzpzs.add(tzcb2);
		}
		return gzpzs;
	}



	/**
	 * 
	* @Description: 加载证券信息
	* @param  参数说明
	* @return void    返回类型
	* @author gaozhen
	 */
	private void loadZqxxMap() {
		this.zqxxMap = new HashMap<>();
		TZqxxExample example = new TZqxxExample();
		Criteria criteria = example.createCriteria();
		criteria.andZqlbEqualTo(1);
		List<TZqxx> zqxxs = zqxxMapper.selectByExample(example);
		for (TZqxx tZqxx : zqxxs) {
			this.zqxxMap.put(tZqxx.getZqdm(), tZqxx.getZqjg());
		}
	}
}
