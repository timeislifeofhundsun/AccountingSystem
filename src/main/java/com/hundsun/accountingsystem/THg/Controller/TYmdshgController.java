/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/27  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Controller;

import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.THg.Service.TYmdshgService;
import com.hundsun.accountingsystem.THg.VO.TYmdshgVO;
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
public class TYmdshgController {
  @Autowired
  public TYmdshgService tYmdshgService;

  @GetMapping("/TYmdshg")
  public String getAllTYmdshg(@RequestParam(value = "ckrq",required = true)String data,
                              @RequestParam(value ="indexpage" ) int indexpage,
                              @RequestParam(value = "sizepage") int sizepage){
    System.out.println(data);
    int[] ywlb = {3103,3104};
    List<TQsb> alltQsbs = tYmdshgService.findAllTQsb(ywlb,data,"302");
    List<TQsb> tQsbs = tYmdshgService.selectTQsbbyPage(ywlb,data,"302", indexpage, sizepage);
    TYmdshgVO layuiJson = new TYmdshgVO();
    layuiJson.setCode(0);
    layuiJson.setCount(alltQsbs.size());
    layuiJson.setMsg("");
    layuiJson.setData(tQsbs);
    String jsonString = JSON.toJSONString(layuiJson);
    System.out.println(jsonString);
    return jsonString;
  }
  @PostMapping("/TYmdshg")
  public String addTYzyshg(@RequestParam(value = "TYmdshg",required = true)String data){
    TQsb tQsb = JSON.parseObject(data, TQsb.class);
    tQsb.setExtendc("302");
    if (tQsb.getBs().equals("B")){
      tQsb.setYwlb(3103);
    }else{
      tQsb.setYwlb(3104);
    }
    int i = tYmdshgService.insertTQsb(tQsb);
    return String.valueOf(i);
  }
  @PutMapping("/TYmdshg")
  public String updateTYzyshg(@RequestParam(value = "TYmdshg",required = true)String data){
    TQsb tQsb = JSON.parseObject(data, TQsb.class);
    tQsb.setExtendc("302");
    if (tQsb.getBs().equals("B")){
      tQsb.setYwlb(3103);
    }else{
      tQsb.setYwlb(3104);
    }
    int i = tYmdshgService.updateTQsbById(tQsb);
    return String.valueOf(i);
  }
  @DeleteMapping("/TYmdshg")
  public String deleteTYzyshg(@RequestParam(value = "id",required = true)String id){
    int i = tYmdshgService.deleteTQsbById(Integer.valueOf(id));
    return String.valueOf(i);
  }
}
