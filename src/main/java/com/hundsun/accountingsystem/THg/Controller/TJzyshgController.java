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
import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.THg.Service.TJzyshgService;
import com.hundsun.accountingsystem.THg.Service.TQsbSearchService;
import com.hundsun.accountingsystem.THg.Service.Union_HGService;
import com.hundsun.accountingsystem.THg.VO.TJzyshgVO;
import com.hundsun.accountingsystem.THg.VO.Union_HG;
import com.hundsun.accountingsystem.THg.VO.Union_HGVO;
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
  @Autowired
  public TCcyebMapper tCcyebMapper;
  @Autowired
  public TQsbSearchService tQsbSearchService;
  @Autowired
  public Union_HGService union_hgService;

  @GetMapping(value = "/TJzyshg")
  public String getAllTYzyshg(@RequestParam(value = "ckrq",required = true)String data,
                              @RequestParam(value ="indexpage" ) int indexpage,
                              @RequestParam(value = "sizepage") int sizepage,
                              @RequestParam(value = "keyword",required = false) String keyword) {
    int[] ywlb = {3101,3102};
    List<TQsb> alltQsbs;
    List<TQsb> tQsbs;
    Union_HGVO layuiJson = new Union_HGVO();
    if (keyword==null){
      alltQsbs= tJzyshgService.findAllTQsb(ywlb,data,"303");
      tQsbs= tJzyshgService.selectTQsbbyPage(ywlb,data,"303", indexpage, sizepage);
    }else{
      alltQsbs =tQsbSearchService.search(ywlb,data,"303",keyword);
      tQsbs= tQsbSearchService.searchPage(ywlb,data,"303",keyword, indexpage, sizepage);
    }
    List<Union_HG> union_hgs = union_hgService.unionHg(tQsbs);
    layuiJson.setCode(0);
    layuiJson.setCount(alltQsbs.size());
    layuiJson.setMsg("");
    layuiJson.setData(union_hgs);
    String jsonString = JSON.toJSONString(layuiJson);
    System.out.println(jsonString);
    return jsonString;
  }
  //废弃
  @DeleteMapping("/TJzyshg")
  public String deleteTYzyshg(@RequestParam(value = "id",required = true)String id){
    int i = tJzyshgService.deleteTQsbById(Integer.valueOf(id));
    Assist assist = new Assist();
    assist.setRequires(Assist.andEq("extendc",id));
    int i1 = tCcyebMapper.deleteTCcyeb(assist);
    //判断并返回处理标志
    if (i1!=0&&i!=0){
      return String.valueOf(i);
    }else{
      return "0";
    }
  }
}
