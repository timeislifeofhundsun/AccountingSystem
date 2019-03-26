package com.hundsun.accountingsystem.Global.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hundsun.accountingsystem.Global.bean.TGdxxb;
import com.hundsun.accountingsystem.Global.bean.TGdxxbExample;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.bean.TGdxxbExample.Criteria;
import com.hundsun.accountingsystem.Global.mapper.TGdxxbMapper;

@Component
public class HGReaderUtil {

	private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private TGdxxbMapper gdxxbMapper;
	
//	public static void main(String[] args) throws Exception {
//		HGReaderUtil hg = new HGReaderUtil();
//		List<TQsb> qsbs = null;
//		System.out.println("上海");
//		qsbs = hg.readJSMXjsmrDBf("/Users/gaozhen/userApp/hsdatasource/2018-06-01/JSMXjsmr1.dbf");
//		for (TQsb tQsb : qsbs) {
//			System.out.println("->"+tQsb);
//		}
//		qsbs = hg.readZQBDjsmrDBf("/Users/gaozhen/userApp/hsdatasource/2018-06-01/ZQBDjsmr1.dbf");
//		for (TQsb tQsb : qsbs) {
//			System.out.println("->"+tQsb);
//		}
//		System.out.println("深圳");
//		qsbs = hg.readSJSJGDBf("/Users/gaozhen/userApp/hsdatasource/2018-06-01/SJSJG0601.dbf");
//		for (TQsb tQsb : qsbs) {
//			System.out.println("->"+tQsb);
//		}
//	}

	/**
	 * 
	 * @Description: 读JSMXjsmr文件,上海-红利到账
	 * @param 参数说明
	 * @return List<TQsb> 返回类型
	 * @author gaozhen
	 */
	public List<TQsb> readJSMXjsmrDBf(String path) throws Exception {
		List<TQsb> list = new ArrayList<TQsb>();
		InputStream fis = null;
		int n = 0;
		try {
			fis = new FileInputStream(path);
			DBFReader reader = new DBFReader(fis);
			reader.setCharactersetName("GBK");
			DBFHeader dbfHeader = reader.header;
			for (DBFField dbfField : dbfHeader.fieldArray) {
				byte[] fieldName = dbfField.fieldName;
				for (int i = 0; i < fieldName.length; i++) {
					if (fieldName[i] == 0) {
						n = i;
						break;
					}
				}
				byte[] dest = new byte[n];
				System.arraycopy(fieldName, 0, dest, 0, n);
			}
			Object[] rowValues;
			TQsb tQsb = null;
			while ((rowValues = reader.nextRecord()) != null) {
				if (rowValues[47].toString().startsWith("红利清算")) {
					//获取账套编号
					String gddm = rowValues[22].toString().trim();
					String xwdm = rowValues[18].toString().trim();
					Integer ztbh = this.getZtbhByGdAndXw(gddm, xwdm);
					/**
					 * 创建对象
					 */
					tQsb = new TQsb();
					//账套
					tQsb.setZtbh(ztbh);
					//日期
					tQsb.setRq(sdf1.parse(rowValues[12].toString().trim()));
					//到账日期
					tQsb.setExtenda(sdf2.format(sdf1.parse(rowValues[13].toString().trim())));
					//证券代码
					tQsb.setZqcode(rowValues[23].toString().trim());
					//业务类别
					tQsb.setYwlb(1202);
					//金额
					String amountStr = rowValues[36].toString().trim();
					tQsb.setAmount(Double.valueOf(amountStr));
					list.add(tQsb);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description: 读取ZQBDjsmr文件-红利送股
	 * @param 参数说明
	 * @return List<TQsb> 返回类型
	 * @author gaozhen
	 */
	public List<TQsb> readZQBDjsmrDBf(String path) throws IOException {
		List<TQsb> list = new ArrayList<TQsb>();
		InputStream fis = null;
		int n = 0;
		try {
			fis = new FileInputStream(path);
			DBFReader reader = new DBFReader(fis);
			reader.setCharactersetName("GBK");
			DBFHeader dbfHeader = reader.header;
			for (DBFField dbfField : dbfHeader.fieldArray) {
				byte[] fieldName = dbfField.fieldName;
				for (int i = 0; i < fieldName.length; i++) {
					if (fieldName[i] == 0) {
						n = i;
						break;
					}
				}
				byte[] dest = new byte[n];
				System.arraycopy(fieldName, 0, dest, 0, n);
			}
			Object[] rowValues;
			TQsb tQsb = null;
			while ((rowValues = reader.nextRecord()) != null) {
				if(rowValues[7].toString().trim().contains("S")) {
					//获取账套编号
					String gddm = rowValues[2].toString().trim();
					String xwdm = rowValues[3].toString().trim();
					Integer ztbh = this.getZtbhByGdAndXw(gddm, xwdm);
					/**
					 * 创建对象
					 */
					tQsb = new TQsb();
					//账套
					tQsb.setZtbh(ztbh);
					//日期
					Date rq = sdf1.parse(rowValues[11].toString().trim());
					tQsb.setRq(rq);
					//到账日期
					tQsb.setExtenda(sdf2.format(DateFormatUtil.getNextWorkDay(rq)));
					//证券代码
					tQsb.setZqcode(rowValues[4].toString().trim());
					//业务类别
					tQsb.setYwlb(1203);
					//送股数量
					String quantityStr = rowValues[9].toString().trim();
					tQsb.setQuantity(Integer.valueOf(quantityStr));
					list.add(tQsb);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description: 读取SJSJG，深圳-红利到账&红利送股
	 * @param 参数说明
	 * @return List<TQsb> 返回类型
	 * @author gaozhen
	 */
	public  List<TQsb> readSJSJGDBf(String path) throws IOException {
		List<TQsb> list = new ArrayList<TQsb>();
		InputStream fis = null;
		int n = 0;
		try {
			fis = new FileInputStream(path);
			DBFReader reader = new DBFReader(fis);
			reader.setCharactersetName("GBK");
			DBFHeader dbfHeader = reader.header;
			for (DBFField dbfField : dbfHeader.fieldArray) {
				byte[] fieldName = dbfField.fieldName;
				for (int i = 0; i < fieldName.length; i++) {
					if (fieldName[i] == 0) {
						n = i;
						break;
					}
				}
				byte[] dest = new byte[n];
				System.arraycopy(fieldName, 0, dest, 0, n);
			}
			Object[] rowValues;
			TQsb tQsb = null;
			while ((rowValues = reader.nextRecord()) != null) {
				//获取账套编号
				String gddm = rowValues[1].toString().trim();
				String xwdm = rowValues[6].toString().trim();
				Integer ztbh = this.getZtbhByGdAndXw(gddm, xwdm);
				/**
				 * 创建对象
				 */
				tQsb = new TQsb();
				//账套
				tQsb.setZtbh(ztbh);
				//日期
				Date ywrq = sdf1.parse(rowValues[37].toString().trim());
				tQsb.setRq(ywrq);
				//到账日期
				tQsb.setExtenda(sdf2.format(DateFormatUtil.getNextWorkDay(ywrq)));
				//证券代码
				tQsb.setZqcode(rowValues[4].toString().trim());
				
				if(rowValues[3].toString().contains("QPPX")) {
					/**
					 * 深圳-红利到账
					 */
					//业务类别
					tQsb.setYwlb(1202);
					//送股数量
					String amountStr = rowValues[34].toString().trim();
					tQsb.setAmount(Double.valueOf(amountStr)*2);
					list.add(tQsb);
				}else if(rowValues[3].toString().contains("QPHG")) {
					/**
					 * 深圳-红利送股
					 */
					//业务类别
					tQsb.setYwlb(1203);
					//送股数量
					String quantityStr = rowValues[14].toString().trim();
					tQsb.setQuantity(Double.valueOf(quantityStr).intValue()*2);
					list.add(tQsb);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		return list;
	}
	
	private Integer getZtbhByGdAndXw(String gddm,String xwdm) throws Exception{
		Integer ztbh = null;
		TGdxxbExample gdxxbExample = new TGdxxbExample();
		Criteria criteria = gdxxbExample.createCriteria();
		criteria.andGddmEqualTo(gddm);
		criteria.andXwbhEqualTo(xwdm);
		List<TGdxxb> gdxxbs =gdxxbMapper.selectByExample(gdxxbExample);
		if(gdxxbs.size()!=1) {
			throw new Exception("账套查询异常");
		}
		ztbh = gdxxbs.get(0).getZtbh();
		return ztbh;
	}
}
