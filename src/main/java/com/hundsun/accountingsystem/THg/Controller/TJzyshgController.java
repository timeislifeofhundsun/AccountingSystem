/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/29  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Controller;

import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.THg.Service.TJzyshgService;
import com.hundsun.accountingsystem.THg.VO.TJzyshgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
@RestController
public class TJzyshgController {
  @Autowired
  public TJzyshgService tJzyshgService;

  @GetMapping(value = "/TJzyshg")
  public String getAllTYzyshg(@RequestParam(value = "ckrq",required = true)String data,
                              @RequestParam(value ="indexpage" ) int indexpage,
                              @RequestParam(value = "sizepage") int sizepage) {
    System.out.println(data);
    int[] ywlb = {3101,3102};
    List<TQsb> alltQsbs = tJzyshgService.findAllTQsb(ywlb,data,"303");
    List<TQsb> tQsbs = tJzyshgService.selectTQsbbyPage(ywlb,data,"303", indexpage, sizepage);
    TJzyshgVO layuiJson = new TJzyshgVO();
    layuiJson.setCode(0);
    layuiJson.setCount(alltQsbs.size());
    layuiJson.setMsg("");
    layuiJson.setData(tQsbs);
    String jsonString = JSON.toJSONString(layuiJson);
    System.out.println(jsonString);
    return jsonString;
  }

  @DeleteMapping("/TJzyshg")
  public String deleteTYzyshg(@RequestParam(value = "id",required = true)String id){
    int i = tJzyshgService.deleteTQsbById(Integer.valueOf(id));
    return String.valueOf(i);
  }
}
