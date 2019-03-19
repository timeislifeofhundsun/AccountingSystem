/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/19  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.controller;

import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.VO.TJyflVO;
import com.hundsun.accountingsystem.Global.VO.TZqxxVO;
import com.hundsun.accountingsystem.Global.bean.TZqxx;
import com.hundsun.accountingsystem.Global.service.TZqxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
@RestController
public class TZqxxController {
  @Autowired
  public TZqxxService tZqxxService;

  @GetMapping("/TZqxx")
  public String getAllZqxx(@RequestParam(value ="indexpage" ) int indexpage,@RequestParam(value = "sizepage") int sizepage ){
    List<TZqxx> list = tZqxxService.getTZqxxPage(indexpage,sizepage);
    List<TZqxx> alllist = tZqxxService.findAllTZqxx();
    TZqxxVO layuiJson = new TZqxxVO();
    layuiJson.setCode(0);
    layuiJson.setCount(alllist.size());
    layuiJson.setMsg("");
    layuiJson.setData(list);
    String jsonString = JSON.toJSONString(layuiJson);
    return jsonString;
  }
  @PostMapping("/TZqxx")
  public String addTZqxx(@RequestParam(value = "TZqxx",required = true) String data){
    System.out.println(data);
    TZqxx tZqxx = JSON.parseObject(data,TZqxx.class);
    int i = tZqxxService.insertSelective(tZqxx);
    return String.valueOf(i);

  }
}
