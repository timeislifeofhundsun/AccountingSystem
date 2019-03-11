/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/10  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.util;

import com.hundsun.accountingsystem.Global.bean.KJKM;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * 功能说明:
 *
 * @author wanggk25832
 * Excel导入数据工具
 */
public class ExcelImportUtil {


  public List<KJKM> getKJKM () throws IOException {
    List<KJKM> kjkmList = new ArrayList<>();
    File excelFile = new File("C:/Users/wanggk23608/Desktop/KJKM.xlsx");
    if (excelFile.exists() && excelFile.isFile()) {
      String[] split = excelFile.getName().split("\\.");
      Workbook wb;
      if ("xls".equals(split[1])) {
        FileInputStream fis = new FileInputStream(excelFile);   //文件流对象
        wb = new HSSFWorkbook(fis);
      } else if ("xlsx".equals(split[1])) {
        FileInputStream fis = new FileInputStream(excelFile);
        wb = new XSSFWorkbook(fis);
      } else {
        System.out.println("文件有误");
        return kjkmList;
      }
      Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

      int firstRowIndex = sheet.getFirstRowNum() + 1;   //第一行是列名，所以不读
      int lastRowIndex = sheet.getLastRowNum();
      for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
        System.out.println("rIndex: " + rIndex);
        Row row = sheet.getRow(rIndex);
        if (row != null) {
          int firstCellIndex = row.getFirstCellNum();
          int lastCellIndex = row.getLastCellNum();
          KJKM kjkm = new KJKM();
          row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
          row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);

          kjkm.setId(row.getCell(0).toString());
          kjkm.setParent_id(row.getCell(1).toString());
          kjkm.setName(row.getCell(2).toString());
          if (!isEmpty(row.getCell(3).toString())){
            kjkm.setLending_direction((int)row.getCell(3).getNumericCellValue());
          }
          if (!isEmpty(row.getCell(4).toString())){
            kjkm.setLevel(Integer.valueOf((int)row.getCell(4).getNumericCellValue()));
          }
          if (!isEmpty(row.getCell(5).toString())){
            kjkm.setIs_parent(Integer.valueOf((int)row.getCell(5).getNumericCellValue()));
          }
          kjkmList.add(kjkm);
        }
      }
    }
    return kjkmList;
  }
}
