/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/13  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.bean.TJyflVO;
import com.hundsun.accountingsystem.Global.service.TJyflService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 * 交易费率设置控制器
 */
@RestController
public class TJyflController {
  @Autowired
  public TJyflService tJyflService;
  @GetMapping("/TJyfl")
  public String getAllTJyfl(){
    List<TJyfl> allTJyfl = tJyflService.findAllTJyfl();
    TJyflVO layuiJson = new TJyflVO();
    layuiJson.setCode(0);
    layuiJson.setCount(allTJyfl.size());
    layuiJson.setMsg("");
    layuiJson.setData(allTJyfl);
    String jsonString = JSON.toJSONString(layuiJson);
    System.out.println(jsonString);
    return jsonString;
  }
  @PutMapping("/TJyfl")
  public String updateTJyfl(@RequestParam(value = "TJyfl",required = true) String data){
    System.out.println(data);
    TJyfl tJyfl = JSON.parseObject(data,TJyfl.class);
    int i = tJyflService.updateByPrimaryKey(tJyfl);
    return String.valueOf(i);
  }
}
