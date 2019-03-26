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
public class TYzyshgController {
  @Autowired
  public TYzyshgService tYzyshgService;

  @GetMapping(value = "/TYzyshg")
  public String getAllTYzyshg(@RequestParam(value = "ckrq",required = true)String data,
                                  @RequestParam(value ="indexpage" ) int indexpage,
                                  @RequestParam(value = "sizepage") int sizepage) {
    String[] ymd = data.split("-");
    if (ymd[1].substring(0,1).equals("0")){
      ymd[1] = ymd[1].substring(1,2);
    }
    if (ymd[2].substring(0,1).equals("0")){
      ymd[2] = ymd[2].substring(1,2);
    }
    data = ymd[0]+"-"+ymd[1]+"-"+ymd[2];
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
}
