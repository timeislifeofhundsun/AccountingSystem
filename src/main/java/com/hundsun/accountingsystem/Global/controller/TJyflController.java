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
import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.VO.TJyflVO;
import com.hundsun.accountingsystem.Global.service.TJyflService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public String getAllTJyfl(@RequestParam(value ="page" ) int page,@RequestParam(value = "limit") int limit,@RequestParam(value = "keyword",required = false)String keyword){
    List<TJyfl> list ;
    List<TJyfl> alllist;
    TJyflVO layuiJson = new TJyflVO();
    if (keyword==null){
      list = tJyflService.getTJyflPage(page,limit);
      alllist = tJyflService.findAllTJyfl();
    }else{
      list = tJyflService.searchTJyflPage(page,limit,keyword);
      alllist = tJyflService.searchTJyfl(keyword);
    }
    layuiJson.setCode(0);
    layuiJson.setCount(alllist.size());
    layuiJson.setMsg("");
    layuiJson.setData(list);
    String jsonString = JSON.toJSONString(layuiJson);
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
