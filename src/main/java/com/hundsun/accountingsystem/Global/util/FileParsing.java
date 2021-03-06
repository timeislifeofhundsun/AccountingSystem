/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/7  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.util;

import com.hundsun.accountingsystem.Global.bean.TGdxxb;
import com.hundsun.accountingsystem.Global.bean.TGhk;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TGdxxbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component
public class FileParsing {

  @Autowired
  TGdxxbMapper tGdxxbMapper;



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
        String BS = null;
        if (temp1.contains("-")){
          BS = "S";
        } else {
          BS = "B";
        }
//        //去掉小数点2
//        String temp2 = rowValues[2].toString().substring(0,rowValues[2].toString().length()-1);

        tGhk.setJszh(rowValues[0].toString()).setBfjzh(rowValues[1].toString()).setZqcode(rowValues[4].toString()).setSclb(1)
        .setCjsl(Integer.valueOf(temp1)).setCjjg(Double.valueOf(rowValues[14].toString())).setCjtime(sdf.parse(rowValues[34].toString()))
        .setJstime(sdf.parse(rowValues[36].toString())).setBctime(sdf.parse(rowValues[34].toString())).setGdname("null").setGdcode(rowValues[1].toString())
        .setBs(BS).setCjje(Math.abs(Double.valueOf(rowValues[33].toString()))).setXwcode(rowValues[5].toString()).setZtcode(null);
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
  public List<TQsb> ReadXGDBf(String path)throws IOException {
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

        if (rowValues[47].toString().contains("新股中签认购")){//判断是否包含新股中检
          tQsb =  new TQsb();
          //根据股东代码和席位编号获取账套编号
          TGdxxb tGdxxb_s = new TGdxxb();
          tGdxxb_s.setXwbh(rowValues[17].toString().trim());
          tGdxxb_s.setGddm(rowValues[22].toString().trim());
          System.out.println(tGdxxbMapper);
          TGdxxb tGdxxb = tGdxxbMapper.selectByGddmAndXwbh(tGdxxb_s);

          if (tGdxxb == null){
            return null;
          }
          //去掉负号
          String temp = rowValues[36].toString().trim().substring(1);
          String temp1 = rowValues[45].toString().trim().substring(1);
//        //去掉小数点2
          tQsb.setZtbh(tGdxxb.getZtbh()).setRq(sdf.parse(rowValues[12].toString())).setZqcode(rowValues[24].toString()).setYwlb(1302)
                  .setBs(rowValues[29].toString().trim()).setQuantity(Integer.valueOf(rowValues[31].toString().trim())).setAmount(Double.valueOf(temp))
                  .setYhs(Double.valueOf(rowValues[37].toString())).setJsf(Double.valueOf(rowValues[38].toString())).setGhf(Double.valueOf(rowValues[39].toString()))
                  .setZgf(Double.valueOf(rowValues[40].toString())).setYj(Double.valueOf(rowValues[41].toString())).setCost(Double.valueOf(temp1))
                  .setExtenda(rowValues[13].toString().trim()).setExtendb(rowValues[11].toString().trim());
          list.add(tQsb);
        } else {
          return null;
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
  * @Author yangjf25257
  * @MethodName ReadZQBDDbf
   * @Param [path]
   * @Return java.util.List<com.hundsun.accountingsystem.Global.bean.TGhk>
   * @Description 读取ZQBD文件,新股流通
   *
   * 1、判断是否为新股
   * 2、根据席位编号和股东代码去股东信息表中查账套编号
   * 3、根据证券代码和账套编号去持仓余额表中更新类型
   * 4、把相应的数据加上业务类别添加到清算库中
   **/
  public static List<String> ReadZQBDDbf(String path)throws IOException {
    List<String> list = new ArrayList<String>();
    String time = null;
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
      while ((rowValues = reader.nextRecord()) != null) {
        if (rowValues[10].toString().contains("00G") 
        		&& rowValues[5].toString().trim().equals("PT")
        		&& rowValues[5].toString().trim().equals("N")){
          list.add(rowValues[2].toString().trim());
          list.add(rowValues[3].toString().trim());
          list.add(rowValues[4].toString().trim());
          list.add(rowValues[11].toString().trim());
        }else {
          return null;
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
  * @Author yangjf25257
  * @MethodName ReadSJSFX
   * @Param [path]
   * @Return java.util.List<com.hundsun.accountingsystem.Global.bean.TQsb>
   * @Description 读取SJSF文件，深交所新股中签
   **/
  public List<TQsb> ReadSJSFX(String path)throws IOException {
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

        if (rowValues[4].toString().contains("FXA3")){//判断是否是中签认购
                tQsb =  new TQsb();
                //根据股东代码和席位编号获取账套编号
                TGdxxb tGdxxb_s = new TGdxxb();
                tGdxxb_s.setXwbh(rowValues[1].toString().trim());
                tGdxxb_s.setGddm("B001999711");
                TGdxxb tGdxxb = tGdxxbMapper.selectByGddmAndXwbh(tGdxxb_s);

                if (tGdxxb == null){
                  return null;
                }
                //去掉负号
                int temp = Integer.valueOf(rowValues[7].toString().trim().substring(0,rowValues[7].toString().length() - 2));
                Double temp1 = Double.valueOf(rowValues[8].toString().trim());
                Double amount = temp1 * temp;
                String extenda = sdf.format(DateFormatUtil.getNextWorkDay(DateFormatUtil.getNextWorkDay(sdf.parse(rowValues[10].toString()))));
      //        //去掉小数点2
                tQsb.setZtbh(tGdxxb.getZtbh()).setRq(sdf.parse(rowValues[10].toString())).setZqcode(rowValues[2].toString()).setYwlb(1302)
                        .setBs("B").setQuantity(temp).setAmount(amount)
                        .setExtenda(extenda);
                list.add(tQsb);
        } else if (rowValues[4].toString().trim().contains("FXA5")){
                tQsb =  new TQsb();
                //根据股东代码和席位编号获取账套编号
                TGdxxb tGdxxb_s = new TGdxxb();
                tGdxxb_s.setXwbh(rowValues[1].toString().trim());
                tGdxxb_s.setGddm("B001999711");
                TGdxxb tGdxxb = tGdxxbMapper.selectByGddmAndXwbh(tGdxxb_s);

                if (tGdxxb == null){
                  return null;
                }
                //去掉负号
              int temp = Integer.valueOf(rowValues[7].toString().trim().substring(0,rowValues[7].toString().length() - 2));
                Double temp1 = Double.valueOf(rowValues[8].toString().trim());
                Double amount = temp1 * temp;
      //        //去掉小数点2
                tQsb.setZtbh(tGdxxb.getZtbh()).setRq(DateFormatUtil.getLastWorkDay(DateFormatUtil.getLastWorkDay(sdf.parse(rowValues[10].toString())))).setZqcode(rowValues[2].toString()).setYwlb(1302)
                        .setBs("B").setQuantity(temp).setAmount(amount)
                        .setExtenda(rowValues[10].toString().trim());
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
  * @Author yangjf25257
  * @MethodName ReadSJSJG
   * @Param [path]
   * @Return java.util.List<java.lang.String>
   * @Description 深交所新股流通
   **/
  public static List<String> ReadSJSJG(String path)throws IOException {
    List<String> list = new ArrayList<String>();
    String time = null;
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
      while ((rowValues = reader.nextRecord()) != null) {
        if (rowValues[1].toString().contains("B001999711") && rowValues[4].toString().contains("300515")){
          list.add(rowValues[1].toString().trim());//股东代码
          list.add(rowValues[6].toString().trim());//席位编号
          list.add(rowValues[4].toString().trim());//证券代码
          list.add(rowValues[37].toString().trim());//时间
        }else {
          return null;
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

}


