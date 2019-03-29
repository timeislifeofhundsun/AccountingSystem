package com.hundsun.accountingsystem.Global.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.service.*;
import com.hundsun.accountingsystem.TGp.service.XgQsbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.Global.util.FilePathUtil;
import com.hundsun.accountingsystem.TGp.service.GPQSService;

@Service
public class RzqsServiceImpl implements RzqsService {
	
//	private static final Logger log = LoggerFactory.getLogger(RzqsServiceImpl.class);

	@Autowired
	private THqbService tHqbService;
	
	@Autowired
	private TGhkService tGhkService;
	
	@Autowired
	private TCjhbbService tCjhbbService;
	
	@Autowired
	private GPQSService gphg;
	
	@Autowired
	private GPQSService gpjy;

	@Autowired
	private XgQsbService xgqs;

	@Autowired
	private LfjxQsService lfjxQsService;

	@Autowired
	private TQsbMapper qsbMapper;

	/**
	* @Description: 日终清算
	* 1.读取交易数据
	* 2.读取行情数据
	* 3.进行合笔计算，存储到成交回报表
	* 4.进行清算，存储到清算库
	* @param
	* ztbh 账套编号 ywrq 业务日期
s	* @return boolean    返回类型
	* @author gaozhen
	 * @throws Exception 
	 */
	@Override
	public boolean rzqs(int ztbh, Date ywrq) throws Exception {
		boolean returnData =  false;
		/**
		 * 校验接口文件
		 */
		Map<String,String> files = FilePathUtil.
				getFilePathByDate(DateFormatUtil.getStringByDate(ywrq));
		String cash = files.get("cashsecurityclosemd");
		String mkt = files.get("mktdt00");
		String sjsmx =files.get("SJSMX1");
		String gh = files.get("GH32562");
		String jsmx = files.get("JSMX");
		String zqbd = files.get("ZQBD");
		String sjsjg = files.get("SJSJG");

		/**
		 * 校验日期
		 */
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ywrq);
		calendar.roll(Calendar.DAY_OF_YEAR, 1);
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ywlb",5101));
		assist.setRequires(Assist.andEq("rq",calendar.getTime()));
		long count = qsbMapper.getTQsbRowCount(assist);
		if(count>0){
			throw new Exception(DateFormatUtil.getStringByDate(ywrq)
					+"已经完成清算！");
		}

		/**
		 * 工作日
		 */
		if(cash!=null&& mkt!=null) {
			/**
			 * 1.读取交易数据
			 */
			String res = "";
			res	= tGhkService.readGhDataByFile(gh,sjsmx,DateFormatUtil.getStringByDate(ywrq));
			if(res.equals("")) {
				throw new Exception("读取交易文件失败");
			}

			/**
			 * 2.读取行情数据
			 */
			returnData = tHqbService.readHqDataByFile(mkt, cash, ywrq);
			if(!returnData) {
				throw new Exception("读取行情文件失败");
			}

			/**
			 * 3.进行合笔计算，存储到成交回报表
			 */
			returnData = tCjhbbService.insertCjhbbByRzqs(ztbh, ywrq);
			if(!returnData) {
				throw new Exception("成交回报失败");
			}

			/**
			 * 4.红股清算
			 */
			gphg.setPath(jsmx,zqbd,sjsjg);
			returnData = gphg.hgqs(ztbh, ywrq);
			if(!returnData) {
				throw new Exception("红股清算失败");
			}

			/**
			 *5.新股清算
			 */
			xgqs.xgqs(jsmx,zqbd,ztbh,ywrq);

			/**
			 * 6.股票交易清算
			 */
			returnData = gpjy.gpqs(ztbh, ywrq);
			if(!returnData) {
				throw new Exception("股票交易清算失败");
			}

			/**
			 * 7.回购清算
			 */

			/**
			 * 8.回购清算
			 */

		}

		/**
		 * 9.其他清算
		 */
		returnData = lfjxQsService.lfjxQs(ztbh,ywrq);
		if(!returnData) {
			throw new Exception("其他费用清算失败");
		}

		return returnData;
	}

}
