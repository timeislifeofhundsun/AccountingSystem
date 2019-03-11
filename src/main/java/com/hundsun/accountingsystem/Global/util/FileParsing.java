/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/7  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.util;

import org.omg.IOP.Encoding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

/**
 * 功能说明:
 *
 * @author wanggk25832
 * DBF解析工具
 */
public class FileParsing {
  public void ReadDbf(String path)throws IOException {
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
      while ((rowValues = reader.nextRecord()) != null) {
        for (int i = 0; i < rowValues.length; i++) {
          System.out.print(rowValues[i] + "|");
        }
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
  }
}
