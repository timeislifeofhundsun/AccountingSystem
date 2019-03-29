/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/28  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem;

import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.THg.Service.HGQSService;
import com.hundsun.accountingsystem.THg.Service.impl.HGQSServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class THgTest {
  @Autowired
  HGQSService hgqsService;
  @Test
  public void test() throws ParseException {
    DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    Date ywrq = dateFormat1.parse("2018-5-31");
    boolean hgqs = hgqsService.hgqs(10004, ywrq);
    System.out.println(hgqs);
    /*String day = "204051".substring(3,6);
    System.out.println(day);
    String[] days = day.split("");
    if (days[0].equals("0")){
      if (days[1].equals("0")){
        day = days[2];
      }else{
        day=days[1]+days[2];
      }
    }else{
      day=days[0]+days[1]+days[2];
    }
    System.out.println(day);*/
    /*String s = hgqsService.CalcDate("2019-4-1", 7);
    System.out.println("s"+s);*/

  }


}
