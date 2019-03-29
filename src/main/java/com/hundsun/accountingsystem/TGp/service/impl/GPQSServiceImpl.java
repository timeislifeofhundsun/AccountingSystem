package com.hundsun.accountingsystem.TGp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TCjhbb;
import com.hundsun.accountingsystem.Global.bean.TCjhbbExample;
import com.hundsun.accountingsystem.Global.bean.TCjhbbExample.Criteria;
import com.hundsun.accountingsystem.Global.bean.THqb;
import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.TCjhbbMapper;
import com.hundsun.accountingsystem.Global.mapper.THqbMapper;
import com.hundsun.accountingsystem.Global.mapper.TJyflMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.TGp.service.GPQSService;

/**
 * 
 * <p>
 * Description:股票清算服务方法实现
 * <p>
 * Company: 恒生电子
 * 
 * @author gaozhen
 * @date 2019年3月19日
 * @Version 1.1
 */
@Service("gpjy")
public class GPQSServiceImpl implements GPQSService {

	private static final Logger log = LoggerFactory.getLogger(GPQSServiceImpl.class);

	// 买入交易费率
	private TJyfl mrjyfl;

	// 卖出交易费率
	private TJyfl mcjyfl;

	@Autowired
	private TJyflMapper jyflmapper;

	@Autowired
	private TCjhbbMapper tCjhbbMapper;

	@Autowired
	private TQsbMapper tQsbMapper;

	@Autowired
	private TCcyebMapper tCcyebMapper;

	@Autowired
	private THqbMapper THqbMapper;

	/**
	 * 1.重置股票数据 
	 * 2.获取交易费率 
	 * 3.获取需要清算的数据(账套、时间、业务类别筛选) 
	 * 4.根据证券代码分类 
	 * 5.计算股票买入 
	 * 6.计算股票卖出
	 * 7.计算估值增值
	 */
	@Override
	public boolean gpqs(int ztbh, Date ywrq) throws Exception {
		boolean returnData = false;
		/**
		 * 1.重置股票数据
		 */
		this.resetGpmr(ztbh, ywrq);
		this.resetGpmc(ztbh, ywrq);
		this.resetGpgz(ztbh, ywrq);

		/**
		 * 2.获取交易费率
		 */
		boolean res = this.getJyfl();
		if (!res) {
			throw new Exception("股票清算-获取交易费率失败");
		}
		/**
		 * 3.获取需要清算的数据(账套、时间、业务类别筛选)
		 */
		TCjhbbExample example = new TCjhbbExample();
		Criteria criteria = example.createCriteria();
		criteria.andZtbhEqualTo(ztbh);
		criteria.andYwlbEqualTo(1);
		criteria.andYwrqBetween(ywrq, ywrq);
		List<TCjhbb> tCjhbbs = tCjhbbMapper.selectByExample(example);
		/**
		 * 4.根据证券代码分类
		 */
		Map<String, List<TCjhbb>> zqdmMap = new HashMap<>();
		for (TCjhbb tCjhbb : tCjhbbs) {
			String zqdm = tCjhbb.getZqdm();
			List<TCjhbb> tcjs = zqdmMap.get(zqdm);
			if (tcjs == null) {
				tcjs = new ArrayList<>();
			}
			tcjs.add(tCjhbb);
			zqdmMap.put(zqdm, tcjs);
		}

		// for(String zqdm:zqdmMap.keySet()) {
		// List<TCjhbb> list = zqdmMap.get(zqdm);
		// System.out.println(list.get(0).getZqdm()+" "+list.get(0).getMmfx());
		// }

		/**
		 * 5.计算股票买入
		 */
		res = this.gpmr(zqdmMap, ztbh, ywrq);
		if (!res) {
			throw new Exception("股票清算-股票买入清算失败");
		}

		/**
		 * 6.计算股票卖出
		 */
		res = this.gpmc(zqdmMap, ztbh, ywrq);
		if (!res) {
			throw new Exception("股票清算-股票买入清算失败");
		}

		/**
		 * 7.计算估值增值
		 */
		res = this.gpgz(ztbh, ywrq);
		if (!res) {
			throw new Exception("股票清算-股票估增清算失败");
		}

		returnData = true;
		return returnData;
	}

	/**
	 * 
	 * @Description: 获取交易费率
	 * @param 参数说明
	 * @return boolean 返回类型
	 * @author gaozhen
	 */
	private boolean getJyfl() {
		boolean res = false;
		this.mrjyfl = jyflmapper.selectByPrimaryKey(1101);
		this.mcjyfl = jyflmapper.selectByPrimaryKey(1102);
		if (this.mrjyfl != null && this.mcjyfl != null) {
			res = true;
		}
		return res;
	}

	/**
	 * 
	 * @Description: 股票买入
	 * @param 参数说明
	 * @return boolean 返回类型
	 * @author gaozhen
	 */
	private boolean gpmr(Map<String, List<TCjhbb>> zqdmMap, int ztbh, Date ywrq) throws Exception {
		boolean res = false;

		/**
		 * 获取需要插入的数据
		 */
		List<TQsb> needInsertQsbs = new ArrayList<>();
		for (String zqdm : zqdmMap.keySet()) {
			TQsb tQsb = this.gpmr(zqdmMap.get(zqdm));
			if (tQsb != null) {
				needInsertQsbs.add(this.gpmr(zqdmMap.get(zqdm)));
			}
		}
		/**
		 * 插入新的qsb数据
		 */
		if(needInsertQsbs!=null && needInsertQsbs.size()>0) {
			tQsbMapper.insertTQsbByBatch(needInsertQsbs);
		}
		/**
		 * 插入或者修改持仓余额表
		 */
		for (TQsb tQsb : needInsertQsbs) {
			TCcyeb para = new TCcyeb();
			para.setZqdm(tQsb.getZqcode());
			para.setExtenda("11");
			TCcyeb tCcyeb = tCcyebMapper.selectTCcyebByObj(para);
			if (tCcyeb == null) {
				tCcyeb = new TCcyeb();
				tCcyeb.setCysl(tQsb.getQuantity());
				tCcyeb.setExtenda("11");
				tCcyeb.setZqcb(tQsb.getCost());
				tCcyeb.setZqdm(tQsb.getZqcode());
				tCcyeb.setZtbh(ztbh);
				tCcyeb.setFsrq(ywrq);
				tCcyeb.setLjgz(0.00);
				int effect = tCcyebMapper.insertNonEmptyTCcyeb(tCcyeb);
				if (effect != 1) {
					throw new Exception("股票买入-插入持仓余额表失败");
				}
			} else {
				tCcyeb.setCysl(tCcyeb.getCysl() + tQsb.getQuantity());
				tCcyeb.setZqcb(tCcyeb.getZqcb() + tQsb.getCost());
				tCcyeb.setFsrq(ywrq);
				int effect = tCcyebMapper.updateNonEmptyTCcyebById(tCcyeb);
				if (effect != 1) {
					throw new Exception("股票买入-修改持仓余额表失败");
				}
			}
		}
		res = true;
		return res;
	}

	/**
	 * 
	 * @Description: 计算经手费、印花税等
	 * @param 参数说明
	 * @return boolean 返回类型
	 * @author gaozhen
	 */
	private TQsb gpmr(List<TCjhbb> tCjhbbs) {
		TQsb qsb = null;
		// 校验参数
		if (tCjhbbs.size() > 0 && tCjhbbs.get(0).getMmfx().equals("B")) {
			double cjje = 0;
			double jsf = 0;
			double ghf = 0;
			double zgf = 0;
			double myj = 0;
			Integer cjsl = 0;
			for (TCjhbb tCjhbb : tCjhbbs) {
				double tempCjje = tCjhbb.getCjjg() * tCjhbb.getCjsl();
				cjje = cjje + tempCjje;
				cjsl = cjsl + tCjhbb.getCjsl();
				jsf = new BigDecimal(jsf + tempCjje * this.mrjyfl.getJsfl()).setScale(2, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
				ghf = new BigDecimal(ghf + tempCjje * this.mcjyfl.getGh()).setScale(2, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
				zgf = new BigDecimal(zgf + tempCjje * this.mcjyfl.getZg()).setScale(2, BigDecimal.ROUND_HALF_UP)
						.doubleValue();

			}
			myj = new BigDecimal(cjje * this.mcjyfl.getYj()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double cyj = myj - jsf - zgf;
			double jyfy = jsf + ghf + zgf + cyj;
			double zqqsk = cjje + jyfy - cyj;
			TCjhbb tempTCjhbb = tCjhbbs.get(0);
			/**
			 * 构建qsb对象
			 */
			qsb = new TQsb(null, tempTCjhbb.getZtbh(), tempTCjhbb.getYwrq(), tempTCjhbb.getZqdm(), 1101, // ywlb
					"B", cjsl, cjje, null, // 印花税
					jsf, ghf, zgf, cyj, jyfy, // 交易费用
					null, null, null, null, null, null, // 扩展字段
					null, // 公允价值变动
					zqqsk, null// 差价收入
					, tempTCjhbb.getJysc(), cjje);// 成本
		}
		return qsb;
	}

	/**
	 * 
	 * @Description: 股票卖出
	 * @param 参数说明
	 * @return boolean 返回类型
	 * @author gaozhen
	 */
	private boolean gpmc(Map<String, List<TCjhbb>> zqdmMap, int ztbh, Date ywrq) throws Exception {
		boolean res = false;
		/**
		 * 获取持仓余额表Map(计算卖出数据需要用到)
		 */
		HashMap<String, TCcyeb> ccyeMap = this.loadCcyeb(ztbh);
		/**
		 * 插入新的qsb数据
		 */
		List<TQsb> needInsertQsb = new ArrayList<>();
		for (String zqdm : zqdmMap.keySet()) {
			TQsb qsb = this.gpmc(zqdmMap.get(zqdm), ccyeMap);
			if (qsb != null) {
				needInsertQsb.add(qsb);
			}
		}
		if (needInsertQsb.size() == 0) {
			return true;
		}
		tQsbMapper.insertTQsbByBatch(needInsertQsb);
		/**
		 * 修改持仓余额表
		 */
		for (TQsb tQsb : needInsertQsb) {
			TCcyeb ccyeb = ccyeMap.get(tQsb.getZqcode());
			ccyeb.setCysl(ccyeb.getCysl() - tQsb.getQuantity());
			ccyeb.setZqcb(ccyeb.getZqcb() - tQsb.getCost());
			double templjgz = (ccyeb.getLjgz() - tQsb.getGyjzbd());
			double ljgz = new BigDecimal(templjgz).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			ccyeb.setLjgz(ljgz);
			tCcyebMapper.updateNonEmptyTCcyebById(ccyeb);
		}
		res = true;
		return res;
	}

	/**
	 * 
	 * @Description: 股票卖出计算经手费、印花税等
	 * @param 参数说明
	 * @return boolean 返回类型
	 * @author gaozhen
	 */
	private TQsb gpmc(List<TCjhbb> tCjhbbs, HashMap<String, TCcyeb> ccyeMap) {
		TQsb qsb = null;
		// 校验参数
		if (tCjhbbs.size() > 0 && tCjhbbs.get(0).getMmfx().equals("S")) {
			/**
			 * 基础数据
			 */
			String zqdm = tCjhbbs.get(0).getZqdm();
			double zqcb = ccyeMap.get(zqdm).getZqcb();
			double ljgz = ccyeMap.get(zqdm).getLjgz();
			double zsl = ccyeMap.get(zqdm).getCysl();
			/**
			 * 需要计算的数据
			 */
			double cjje = 0;
			double jsf = 0;
			double ghf = 0;
			double zgf = 0;
			double yhs = 0;
			double myj = 0;
			Integer cjsl = 0;
			for (TCjhbb tCjhbb : tCjhbbs) {
				int tempCjsl = tCjhbb.getCjsl();
				double tempCjje = tCjhbb.getCjjg() * tempCjsl;
				cjje = cjje + tempCjje;
				cjsl = cjsl + tempCjsl;
				jsf = new BigDecimal(jsf + tempCjje * this.mrjyfl.getJsfl()).setScale(2, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
				ghf = new BigDecimal(ghf + tempCjje * this.mcjyfl.getGh()).setScale(2, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
				zgf = new BigDecimal(zgf + tempCjje * this.mcjyfl.getZg()).setScale(2, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
				yhs = new BigDecimal(yhs + tempCjje * this.mcjyfl.getYh()).setScale(2, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
			}
			myj = new BigDecimal(cjje * this.mcjyfl.getYj()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double cyj = myj - jsf - zgf;
			// 交易费用
			double jyfy = jsf + ghf + zgf + yhs + cyj;
			// 证券清算款
			double zqqsk = cjje - jyfy + cyj;
			// 卖出证券成本
			double mczqcb = new BigDecimal(zqcb / zsl * cjsl).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			// 卖出估值增值
			double mcgz = new BigDecimal(ljgz / zsl * cjsl).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			// 应付佣金cyj
			// 差价收入
			double phs = jyfy + zqqsk - mczqcb - mcgz - cyj;

			TCjhbb tempTCjhbb = tCjhbbs.get(0);
			/**
			 * 构建qsb对象
			 */
			qsb = new TQsb(null, tempTCjhbb.getZtbh(), tempTCjhbb.getYwrq(), zqdm, 1102, // ywlb
					"S", cjsl, cjje, yhs, // 印花税
					jsf, ghf, zgf, cyj, jyfy, // 交易费用
					null, null, null, null, null, null, // 扩展字段
					mcgz, // 公允价值变动
					zqqsk, phs, // 差价收入
					tempTCjhbb.getJysc(), mczqcb);// 成本
		}
		return qsb;
	}

	/**
	 * 
	 * @Description: 计算股票估值增值
	 * @param 参数说明
	 * @return boolean 返回类型
	 * @author gaozhen
	 */
	private boolean gpgz(int ztbh, Date ywrq) {
		boolean res = false;
		HashMap<String, THqb> hqMap = new HashMap<>();
		/**
		 * 临时变量
		 */
		TCcyeb ccyePara = null;
		Assist assist = null;

		/**
		 * 获取行情
		 */
		assist = new Assist();
		assist.setRequires(Assist.andEq("zqnm", 1));
		assist.setRequires(Assist.andEq("hqrq", DateFormatUtil.getStringByDate(ywrq)));
		List<THqb> hqbs = THqbMapper.selectTHqb(assist);

		if (hqbs==null || hqbs.size()==0){
			log.info("今日无行情，股票不计算估值增值");
			return true;
		}

		for (THqb tHqb : hqbs) {
			hqMap.put(tHqb.getZqdm(), tHqb);
		}


		/**
		 * 获取需要计算估增的股票
		 */
		assist = new Assist();
		assist.setRequires(Assist.andEq("extenda", "11"));
		List<TCcyeb> tCcyebs = tCcyebMapper.selectTCcyeb(assist);
		/**
		 * 计算估值增值
		 */
		ArrayList<TQsb> needInsertQsbs = new ArrayList<>();
		for (TCcyeb tCcyeb : tCcyebs) {
			double jrspj = hqMap.get(tCcyeb.getZqdm()).getJrsp();
			double gz = tCcyeb.getCysl() * jrspj - tCcyeb.getZqcb() - tCcyeb.getLjgz();
			TQsb tQsb = new TQsb();
			tQsb.setZtbh(ztbh);
			tQsb.setRq(ywrq);
			tQsb.setZqcode(tCcyeb.getZqdm());
			tQsb.setYwlb(1103);
			tQsb.setGyjzbd(gz);
			needInsertQsbs.add(tQsb);
		}
		/**
		 * 将估增数据插入到清算库
		 */
		int effect = tQsbMapper.insertTQsbByBatch(needInsertQsbs);
		if (effect != needInsertQsbs.size()) {
			log.error("股票估增-数据插入清算库失败");
		}
		/**
		 * 插入或修改持仓余额表
		 */
		for (TQsb tQsb : needInsertQsbs) {
			ccyePara = new TCcyeb();
			ccyePara.setZqdm(tQsb.getZqcode());
			TCcyeb tCcyeb = tCcyebMapper.selectTCcyebByObj(ccyePara);
			if (tCcyeb == null) {
				log.error("股票估增-修改持仓余额表,持仓余额表为null【严重错误】");
			} else {
				tCcyeb.setFsrq(ywrq);
				tCcyeb.setLjgz(tCcyeb.getLjgz() + tQsb.getGyjzbd());
				tCcyebMapper.updateNonEmptyTCcyebById(tCcyeb);
			}
		}
		res = true;
		return res;
	}

	/**
	 * 
	 * @Description: 加载科目余额
	 * @param 参数说明
	 * @return HashMap<String,TCcyeb> 返回类型
	 * @author gaozhen
	 */
	private HashMap<String, TCcyeb> loadCcyeb(int ztbh) {
		HashMap<String, TCcyeb> ccyeMap = new HashMap<>();
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("extenda", 11));
		List<TCcyeb> ccyes = tCcyebMapper.selectTCcyeb(assist);
		for (TCcyeb ccye : ccyes) {
			ccyeMap.put(ccye.getZqdm(), ccye);
		}
		return ccyeMap;
	}

	/**
	 * 
	 * @Description: 重置股票买入数据
	 * @param 参数说明
	 * @return void 返回类型
	 * @author gaozhen
	 */
	private void resetGpmr(int ztbh, Date ywrq) throws Exception {
		/**
		 * 获取需要删除的清算表数据，以便于恢复持仓余额表
		 */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", DateFormatUtil.getStringByDate(ywrq)));
		assist.setRequires(Assist.andEq("ywlb", 1101));

		List<TQsb> tqsbs = tQsbMapper.selectTQsb(assist);
		/**
		 * 恢复持仓余额表数据
		 */
		for (TQsb tQsb : tqsbs) {
			TCcyeb para = new TCcyeb();
			para.setZqdm(tQsb.getZqcode());
			para.setExtenda("11");
			TCcyeb tCcyeb = tCcyebMapper.selectTCcyebByObj(para);
			if (tCcyeb != null) {
				tCcyeb.setCysl(tCcyeb.getCysl() - tQsb.getQuantity());
				tCcyeb.setZqcb(tCcyeb.getZqcb() - tQsb.getCost());
				tCcyeb.setFsrq(ywrq);
				int effect = tCcyebMapper.updateNonEmptyTCcyebById(tCcyeb);
				if (effect != 1) {
					throw new Exception("股票买入-恢复持仓余额表失败");
				}
			}
		}
		/**
		 * 删除清算表数据
		 */
		tQsbMapper.deleteTQsb(assist);
	}

	/**
	 * 
	 * @Description: 重置股票估增数据
	 * @param 参数说明
	 * @return void 返回类型
	 * @author gaozhen
	 */
	private void resetGpgz(int ztbh, Date ywrq) throws Exception {
		/**
		 * 临时变量
		 */
		TCcyeb ccyePara = null;
		Assist assist = null;

		/**
		 * 获取需要删除的清算表数据 恢复持仓余额表
		 */
		assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", DateFormatUtil.getStringByDate(ywrq)));
		assist.setRequires(Assist.andEq("ywlb", 1103));
		List<TQsb> needDeleteQSbs = tQsbMapper.selectTQsb(assist);
		for (TQsb tQsb : needDeleteQSbs) {
			ccyePara = new TCcyeb();
			ccyePara.setZqdm(tQsb.getZqcode());
			TCcyeb tCcyeb = tCcyebMapper.selectTCcyebByObj(ccyePara);
			tCcyeb.setLjgz(tCcyeb.getLjgz() - tQsb.getGyjzbd());
			tCcyebMapper.updateNonEmptyTCcyebById(tCcyeb);
		}
		/**
		 * 删除清算库表数据
		 */
		tQsbMapper.deleteTQsb(assist);
	}

	/**
	 * 
	 * @Description: 重置股票卖出数据
	 * @param 参数说明
	 * @return void 返回类型
	 * @author gaozhen
	 */
	private void resetGpmc(int ztbh, Date ywrq) throws Exception {
		/**
		 * 获取需要删除的清算表数据，以便于恢复持仓余额表
		 */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("rq", DateFormatUtil.getStringByDate(ywrq)));
		assist.setRequires(Assist.andEq("ywlb", 1102));
		List<TQsb> needDeleteQSbs = tQsbMapper.selectTQsb(assist);
		/**
		 * 恢复持仓余额表数据
		 */
		for (TQsb qsb : needDeleteQSbs) {
			TCcyeb para = new TCcyeb();
			para.setZqdm(qsb.getZqcode());
			para.setExtenda("11");
			TCcyeb tCcyeb = tCcyebMapper.selectTCcyebByObj(para);
			if (tCcyeb != null) {
				tCcyeb.setCysl(tCcyeb.getCysl() +qsb.getQuantity());
				tCcyeb.setZqcb(tCcyeb.getZqcb() +qsb.getCost());
				tCcyeb.setLjgz(tCcyeb.getLjgz() +qsb.getGyjzbd());
				tCcyeb.setFsrq(ywrq);
				int effect = tCcyebMapper.updateNonEmptyTCcyebById(tCcyeb);
				if (effect != 1) {
					throw new Exception("股票买入-恢复持仓余额表失败");
				}
			}
		}

		/**
		 * 删除清算表数据
		 */
		tQsbMapper.deleteTQsb(assist);
	}

	@Override
	public boolean hgqs(int ztbh, Date ywrq) throws Exception {
		//空方法
		return false;
	}

	@Override
	public void setPath(String jsmx, String zqbd, String sjsjg) {
		//空方法
	}

}
