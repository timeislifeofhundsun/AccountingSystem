/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/28  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.Global.util.MD5Util;
import com.hundsun.accountingsystem.THg.Quartz.QuartzTask;
import com.hundsun.accountingsystem.THg.Service.DQService;
import com.hundsun.accountingsystem.THg.Service.HGPZBService;
import com.hundsun.accountingsystem.THg.Service.HGQSService;
import com.hundsun.accountingsystem.THg.Service.impl.HGPZServiceImpl;
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
import java.util.List;

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

  @Autowired
  TCcyebMapper tCcyebMapper;

  @Autowired
  TQsbMapper tQsbMapper;

  @Autowired
  HGPZBService hgpzbService;

  @Autowired
  DQService dqService;

  @Autowired
  QuartzTask quartzTask;
  @Test
  public void test() throws ParseException {
    System.out.println(MD5Util.encode("123"));
  }

}
