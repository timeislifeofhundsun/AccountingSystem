/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/22  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.controller;

import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.bean.TLfjxb;
import com.hundsun.accountingsystem.Global.service.TLfjxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
@RestController
public class TLfjxbController {
  @Autowired
  public TLfjxbService tLfjxbService;

  @GetMapping("/TLfjxb")
  public TLfjxb getTLfjxb(){
    TLfjxb tLfjxb = tLfjxbService.selectByPrimaryKey(1);
    return tLfjxb;
  }

  @PutMapping("/TLfjxb")
  public String updateTLfjxb(@RequestParam(value ="TLfjxb",required = true)String data){
    System.out.println("TLJ信息"+data);
    TLfjxb tLfjxb = JSON.parseObject(data,TLfjxb.class);
    tLfjxb.setId(1);
    System.out.println(tLfjxb);
    int i = tLfjxbService.updateByPrimaryKey(tLfjxb);
    return String.valueOf(i);
  }


}
