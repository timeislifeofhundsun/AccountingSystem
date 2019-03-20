/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/7  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.util;

import com.hundsun.accountingsystem.Global.bean.TGhk;
import com.hundsun.accountingsystem.Global.bean.TQsb;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 * DBF解析工具
 */
public class FileParsing {

  /**
  * @Author yangjf25257
  * @MethodName ReadDbf
   * @Param [path]
   * @Return java.util.List<com.hundsun.accountingsystem.Global.bean.TGhk>
   * @Description 获取上交所交易文件，放进过户库中
   **/
  public static List<TGhk> ReadDbf(String path)throws IOException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    List<TGhk> list = new ArrayList<TGhk>();
    InputStream fis = null;
    int n = 0;

    try {
      fis = new FileInputStream(path);
      DBFReader reader = new DBFReader(fis);
      reader.setCharactersetName("GBK");
      DBFHeader dbfHeader = reader.header;
      for (DBFField dbfField:dbfHeader.fieldArray) {
        byte[] fieldName = dbfField.fieldName;
        for (int i =0;i < fieldName.length;i++){
          if (fieldName[i]==0){
            n=i;
            break;
          }
        }
        byte[] dest = new byte[n];
        System.arraycopy(fieldName, 0, dest, 0, n);
        String s = new String(dest);
      }
      Object[] rowValues;
      TGhk tGhk = null;
      while ((rowValues = reader.nextRecord()) != null) {
        tGhk =  new TGhk();
        //去掉空格
        String temp = rowValues[7].toString().substring(0,rowValues[7].toString().length()-1);
        //去掉小数点1
        String temp1 = rowValues[5].toString().substring(0,rowValues[5].toString().length()-2);
        //去掉小数点2
        String temp2 = rowValues[2].toString().substring(0,rowValues[2].toString().length()-1);
        tGhk.setGdcode(rowValues[0].toString()).setGdname(null).setXwcode(rowValues[4].toString()).setZtcode(null)
       .setCjsl(Integer.valueOf(temp1)).setCjje(Double.valueOf(rowValues[11].toString())).setCjjg(Double.valueOf(rowValues[10].toString()))
       .setZqcode(rowValues[7].toString()).setBs(rowValues[13].toString().trim()).setBctime(sdf.parse(temp2)).setJstime(sdf.parse(temp2))
       .setCjtime(sdf.parse(temp2)).setJszh(null).setBfjzh(null).setSclb(0);
        list.add(tGhk);
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
  * @Author yangjf25257
  * @MethodName ReadSJSDbf
   * @Param [path]
   * @Return java.util.List<com.hundsun.accountingsystem.Global.bean.TGhk>
   * @Description 获取深交所交易文件，放进过户库中
   **/
  public static List<TGhk> ReadSJSDbf(String path)throws IOException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    List<TGhk> list = new ArrayList<TGhk>();
    InputStream fis = null;
    int n = 0;

    try {
      fis = new FileInputStream(path);
      DBFReader reader = new DBFReader(fis);
      reader.setCharactersetName("GBK");
      DBFHeader dbfHeader = reader.header;
      for (DBFField dbfField:dbfHeader.fieldArray) {
        byte[] fieldName = dbfField.fieldName;
        for (int i =0;i < fieldName.length;i++){
          if (fieldName[i]==0){
            n=i;
            break;
          }
        }
        byte[] dest = new byte[n];
        System.arraycopy(fieldName, 0, dest, 0, n);
        String s = new String(dest);
      }
      Object[] rowValues;
      TGhk tGhk = null;
      while ((rowValues = reader.nextRecord()) != null) {
        tGhk =  new TGhk();
//        //去掉空格
//        String temp = rowValues[7].toString().substring(0,rowValues[7].toString().length()-1);
        //去掉小数点1
        String temp1 = rowValues[12].toString().substring(0,rowValues[12].toString().length()-2);
//        //去掉小数点2
//        String temp2 = rowValues[2].toString().substring(0,rowValues[2].toString().length()-1);

        tGhk.setJszh(rowValues[0].toString()).setBfjzh(rowValues[1].toString()).setZqcode(rowValues[4].toString()).setSclb(1)
        .setCjsl(Integer.valueOf(temp1)).setCjjg(Double.valueOf(rowValues[14].toString())).setCjtime(sdf.parse(rowValues[34].toString()))
        .setJstime(sdf.parse(rowValues[34].toString())).setBctime(sdf.parse(rowValues[34].toString())).setGdname("null").setGdcode(rowValues[1].toString())
        .setBs("B").setCjje(Double.valueOf(rowValues[15].toString())).setXwcode(rowValues[5].toString()).setZtcode(null);
        list.add(tGhk);
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
  * @Author yangjf25257
  * @MethodName ReadXGDBf
   * @Param [path]
   * @Return java.util.List<com.hundsun.accountingsystem.Global.bean.TGhk>
   * @Description 获取新股网上中签文件，放入清算库中
   **/
  public static List<TQsb> ReadXGDBf(String path)throws IOException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    List<TQsb> list = new ArrayList<TQsb>();
    InputStream fis = null;
    int n = 0;

    try {
      fis = new FileInputStream(path);
      DBFReader reader = new DBFReader(fis);
      reader.setCharactersetName("GBK");
      DBFHeader dbfHeader = reader.header;
      for (DBFField dbfField:dbfHeader.fieldArray) {
        byte[] fieldName = dbfField.fieldName;
        for (int i =0;i < fieldName.length;i++){
          if (fieldName[i]==0){
            n=i;
            break;
          }
        }
        byte[] dest = new byte[n];
        System.arraycopy(fieldName, 0, dest, 0, n);
        String s = new String(dest);
      }
      Object[] rowValues;
      TQsb tQsb = null;
      while ((rowValues = reader.nextRecord()) != null) {
        tQsb =  new TQsb();
//        //去掉空格
//        String temp = rowValues[7].toString().substring(0,rowValues[7].toString().length()-1);
        //去掉小数点1
        //String temp1 = rowValues[12].toString().substring(0,rowValues[12].toString().length()-2);
//        //去掉小数点2
//        String temp2 = rowValues[2].toString().substring(0,rowValues[2].toString().length()-1);
        tQsb.setZtbh(10000).setRq(sdf.parse(rowValues[12].toString())).setZqcode(rowValues[24].toString()).setYwlb(Integer.valueOf(rowValues[4].toString()))
        .setBs(rowValues[29].toString()).setQuantity(Integer.valueOf(rowValues[32].toString())).setAmount(Double.valueOf(rowValues[36].toString()))
        .setYhs(Double.valueOf(rowValues[37].toString())).setJsf(Double.valueOf(rowValues[38].toString())).setGhf(Double.valueOf(rowValues[39].toString()))
        .setZgf(Double.valueOf(rowValues[40].toString())).setYj(Double.valueOf(rowValues[41].toString())).setCost(Double.valueOf(rowValues[45].toString()))
        .setExtenda(rowValues[13].toString()).setExtendb(rowValues[11].toString());
        list.add(tQsb);
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
}
