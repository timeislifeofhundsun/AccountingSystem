/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/3  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Quartz;

import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.THg.Service.DQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
@Service
public class QuartzTask {
  @Autowired
  DQService dqService;
  public void DQ(){
    Date date = new Date();
    String today = DateFormatUtil.getStringByDate(date);
    System.out.println(today);
    dqService.HG_dq(today);
  }
}
