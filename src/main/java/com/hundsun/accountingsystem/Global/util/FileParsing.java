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
  public List<TGhk> ReadDbf(String path)throws IOException {
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
        System.out.print(s+"|");
      }
      System.out.println();
      Object[] rowValues;
      TGhk tGhk = new TGhk();
      while ((rowValues = reader.nextRecord()) != null) {
        //去掉空格
        String temp = rowValues[7].toString().substring(0,rowValues[7].toString().length()-1);
        //去掉小数点1
        String temp1 = rowValues[5].toString().substring(0,rowValues[5].toString().length()-2);
        //去掉小数点2
        String temp2 = rowValues[2].toString().substring(0,rowValues[2].toString().length()-1);

        tGhk.setGdcode(rowValues[0].toString());
        tGhk.setGdcode(rowValues[0].toString()).setGdname(null).setXwcode(rowValues[4].toString()).setZtcode(Integer.valueOf(temp))
       .setCjsl(Integer.valueOf(temp1)).setCjje(Double.valueOf(rowValues[11].toString())).setCjjg(Double.valueOf(rowValues[10].toString()))
       .setZqcode(rowValues[7].toString()).setBs(rowValues[13].toString()).setBctime(sdf.parse(temp2)).setJstime(sdf.parse(temp2))
       .setCjtime(sdf.parse(temp2)).setJszh("111").setBfjzh("222").setSclb(0);
        list.add(tGhk);
        System.out.println();
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
