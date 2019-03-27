/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/25  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Controller;

import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.VO.TZqxxVO;
import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.THg.Service.TYzyshgService;
import com.hundsun.accountingsystem.THg.VO.TYzyshgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
@RestController
public class TYzyshgController {
  @Autowired
  public TYzyshgService tYzyshgService;

  @GetMapping(value = "/TYzyshg")
  public String getAllTYzyshg(@RequestParam(value = "ckrq",required = true)String data,
                                  @RequestParam(value ="indexpage" ) int indexpage,
                                  @RequestParam(value = "sizepage") int sizepage) {
    System.out.println(data);
    int[] ywlb = {3103,3104};
    List<TQsb> alltQsbs = tYzyshgService.findAllTQsb(ywlb,data,"1");
    List<TQsb> tQsbs = tYzyshgService.selectTQsbbyPage(ywlb,data,"1", indexpage, sizepage);
    TYzyshgVO layuiJson = new TYzyshgVO();
    layuiJson.setCode(0);
    layuiJson.setCount(alltQsbs.size());
    layuiJson.setMsg("");
    layuiJson.setData(tQsbs);
    String jsonString = JSON.toJSONString(layuiJson);
    System.out.println(jsonString);
    return jsonString;
  }

  @PostMapping("/TYzyshg")
  public String addTYzyshg(@RequestParam(value = "TYzyshg",required = true)String data){
    TQsb tQsb = JSON.parseObject(data, TQsb.class);
    tQsb.setExtendc("1");
    if (tQsb.getBs().equals("B")){
      tQsb.setYwlb(3103);
    }else{
      tQsb.setYwlb(3104);
    }
    int i = tYzyshgService.insertTQsb(tQsb);
    return String.valueOf(i);
  }
  @PutMapping("/TYzyshg")
  public String updateTYzyshg(@RequestParam(value = "TYzyshg",required = true)String data){
    TQsb tQsb = JSON.parseObject(data, TQsb.class);
    tQsb.setExtendc("1");
    if (tQsb.getBs().equals("B")){
      tQsb.setYwlb(3103);
    }else{
      tQsb.setYwlb(3104);
    }
    int i = tYzyshgService.updateTQsbById(tQsb);
    return String.valueOf(i);
  }
  @DeleteMapping("/TYzyshg")
  public String deleteTYzyshg(@RequestParam(value = "id",required = true)String id){
    int i = tYzyshgService.deleteTQsbById(Integer.valueOf(id));
    return String.valueOf(i);
  }
}
