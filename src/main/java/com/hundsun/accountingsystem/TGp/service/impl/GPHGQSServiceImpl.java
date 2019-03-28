package com.hundsun.accountingsystem.TGp.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.Global.util.HGReaderUtil;
import com.hundsun.accountingsystem.TGp.service.GPQSService;

/**
 * 
* <p>
* Description:红股清算
* <p>
* Company: 恒时电子 
* @author gaozhen
* @date 2019年3月25日
* @Version 1.1
 */
@Service("gphg")
public class GPHGQSServiceImpl implements GPQSService {
	
	private static final Logger log = LoggerFactory.getLogger(GPHGQSServiceImpl.class);
	
	private String jsmx;
	
	private String zqbd;
	
	private String sjsjg;
	
	@Autowired
	private HGReaderUtil hGReaderUtil;
	
	@Autowired
	private TCcyebMapper tCcyebMapper;

	@Autowired
	private TQsbMapper qsbMapper;
	
	@Override
	public boolean gpqs(int ztbh, Date ywrq) throws Exception {
		//没有实现
		return false;
	}

	/**
	 * 
	* @Description: 红股清算(包括红股到账，送股)
	* 1.删除清算表旧数据，恢复持仓余额表
	* 2.调用红利到账&送股清算
	* @param  参数说明
	* @return boolean    返回类型
	* @author gaozhen
	 */
	@Override
	public boolean hgqs(int ztbh, Date ywrq) throws Exception {
		boolean res = false;
		/**
		 * 1.删除清算表旧数据，恢复持仓余额表
		 */
		this.resetSG(ztbh, ywrq);
		this.resetHL(ztbh, ywrq);
		/**
		 * 2.调用红利到账&送股清算
		 */
		this.qs(ztbh, ywrq);
		res = true;
		return res;
	}

	/**
	 * 
	* @Description: 清算
	* @param  参数说明
	* @return boolean    返回类型
	* @author gaozhen
	 */
	private boolean qs(int ztbh, Date ywrq) throws Exception {
		boolean res = false;
		/**
		 * 1.插入清算库
		 */
		List<TQsb> needInsertQsb = null;
		//上海-红利到账		
		if(jsmx!=null) {
			needInsertQsb = hGReaderUtil.readJSMXjsmrDBf(jsmx);
		}else {
			log.info("今日无jsmx数据文件");
		}
		//深圳-红利到账&送股
		if(sjsjg!=null) {
			List<TQsb> temp = hGReaderUtil.readSJSJGDBf(sjsjg);
			if(needInsertQsb!=null) {
				needInsertQsb.addAll(temp);
			}
		}else {
			log.info("今日无SJSJG数据文件");
		}
		//上海-送股
		if(zqbd!=null) {
			List<TQsb> temp = hGReaderUtil.readZQBDjsmrDBf(zqbd);
			if(needInsertQsb!=null) {
				needInsertQsb.addAll(temp);
			}
		}
		else {
			log.info("今日无ZQBD数据文件");
		}
		if(needInsertQsb!=null && needInsertQsb.size()>0) {
			qsbMapper.insertTQsbByBatch(needInsertQsb);
		}
		/**
		 * 2.更新持仓表
		 */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("extenda", DateFormatUtil.getStringByDate(ywrq)));
		List<TQsb> needUpdateQsb = qsbMapper.selectTQsb(assist);
		for (TQsb qsb : needUpdateQsb) {
			//送股
			if(qsb.getYwlb()==1203) {
				TCcyeb para = new TCcyeb();
				para.setZqdm(qsb.getZqcode());
				TCcyeb ccye = tCcyebMapper.selectTCcyebByObj(para);
				ccye.setCysl(ccye.getCysl()+qsb.getQuantity());
				int effect = tCcyebMapper.updateNonEmptyTCcyebById(ccye);
				if(effect!=1) {
					log.error("送股更新持仓出错");
				}
			}
			//到账
		}
		res = true;
		return res;
	}

	/**
	 * 
	* @Description: 恢复送股
	* @param  参数说明
	* @return void    返回类型
	* @author gaozhen
	 */
	private void resetSG(int ztbh, Date ywrq) throws Exception  {
		/**
		 * 恢复持仓
		 */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", DateFormatUtil.getStringByDate(ywrq)));
		assist.setRequires(Assist.andEq("ywlb", 1203));
		List<TQsb> needDeleteQSbs = qsbMapper.selectTQsb(assist);
		for (TQsb qsb : needDeleteQSbs) {
			Date sgrq = DateFormatUtil.getDateByString(qsb.getExtenda());
			if(sgrq.getTime()<ywrq.getTime()) {
				TCcyeb para = new TCcyeb();
				para.setZqdm(qsb.getZqcode());
				TCcyeb ccye = tCcyebMapper.selectTCcyebByObj(para);
				ccye.setCysl(ccye.getCysl()-qsb.getQuantity());
			}
		}
		/**
		 * 删除清算表
		 */
		qsbMapper.deleteTQsb(assist);
	}
	
	/**
	 * 
	* @Description: 恢复红利到账
	* @param  参数说明
	* @return void    返回类型
	* @author gaozhen
	 */
	private void resetHL(int ztbh, Date ywrq) throws Exception  {
		/**
		 * 恢复持仓
		 */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", DateFormatUtil.getStringByDate(ywrq)));
		assist.setRequires(Assist.andEq("ywlb", 1202));
//		List<TQsb> needDeleteQSbs = qsbMapper.selectTQsb(assist);
//		for (TQsb qsb : needDeleteQSbs) {
//			Date sgrq = DateFormatUtil.getDateByString(qsb.getExtenda());
//			if(sgrq.getTime()>ywrq.getTime()) {
//				TCcyeb para = new TCcyeb();
//				para.setZqdm(qsb.getZqcode());
//				TCcyeb ccye = tCcyebMapper.selectTCcyebByObj(para);
//				ccye.setCysl(ccye.getCysl()-qsb.getQuantity());
//			}
//		}
		/**
		 * 删除清算表
		 */
		qsbMapper.deleteTQsb(assist);
	}
	
	@Override
	public void setPath(String jsmx, String zqbd, String sjsjg) {
		this.jsmx = jsmx;
		this.zqbd = zqbd;
		this.sjsjg = sjsjg;
	}
	
}
