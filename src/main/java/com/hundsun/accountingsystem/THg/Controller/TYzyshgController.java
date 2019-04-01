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
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.Global.util.FileParsing;
import com.hundsun.accountingsystem.THg.Service.TYzyshgService;
import com.hundsun.accountingsystem.THg.VO.TYzyshgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
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
  @Autowired
  public TCcyebMapper tCcyebMapper;
  @Autowired
  public TQsbMapper tQsbMapper;

  @GetMapping(value = "/TYzyshg")
  public String getAllTYzyshg(@RequestParam(value = "ckrq",required = true)String data,
                                  @RequestParam(value ="indexpage" ) int indexpage,
                                  @RequestParam(value = "sizepage") int sizepage) {
    System.out.println(data);
    int[] ywlb = {3103,3104};
    List<TQsb> alltQsbs = tYzyshgService.findAllTQsb(ywlb,data,"301");
    List<TQsb> tQsbs = tYzyshgService.selectTQsbbyPage(ywlb,data,"301", indexpage, sizepage);
    TYzyshgVO layuiJson = new TYzyshgVO();
    layuiJson.setCode(0);
    layuiJson.setCount(alltQsbs.size());
    layuiJson.setMsg("");
    layuiJson.setData(tQsbs);
    String jsonString = JSON.toJSONString(layuiJson);
    System.out.println(jsonString);
    return jsonString;
  }
  @Transactional
  @PostMapping("/TYzyshg")
  public String addTYzyshg(@RequestParam(value = "TYzyshg",required = true)String data) throws ParseException {
    TQsb tQsb = JSON.parseObject(data, TQsb.class);
    tQsb.setExtendc("301");
    if (tQsb.getBs().equals("B")){
      tQsb.setYwlb(3103);
    }else{
      tQsb.setYwlb(3104);
    }
    int i = tYzyshgService.insertTQsb(tQsb);
    //持仓余额表相应变动
    TCcyeb tCcyeb = new TCcyeb();
    tCcyeb.setZqdm(tQsb.getZqcode());
    tCcyeb.setZtbh(tQsb.getZtbh());
    tCcyeb.setZqcb(tQsb.getAmount());
    tCcyeb.setExtendc(String.valueOf(tQsb.getId()));
    tCcyeb.setLjjx((tQsb.getYhs()-tQsb.getAmount()));
    tCcyeb.setFsrq(DateFormatUtil.getDateByString(tQsb.getExtenda()));
    tCcyeb.setExtenda("31");
    tCcyeb.setExtendb(tQsb.getExtendb());
    int i1 = tCcyebMapper.insertTCcyeb(tCcyeb);
    //判断并返回处理标志
    if (i1!=0&&i!=0){
      return String.valueOf(i);
    }else{
      return "0";
    }
  }
  @Transactional
  @PutMapping("/TYzyshg")
  public String updateTYzyshg(@RequestParam(value = "TYzyshg",required = true)String data){
    TQsb tQsb = JSON.parseObject(data, TQsb.class);
    tQsb.setExtendc("301");
    if (tQsb.getBs().equals("B")){
      tQsb.setYwlb(3103);
    }else{
      tQsb.setYwlb(3104);
    }
    int i = tYzyshgService.updateTQsbById(tQsb);
    //持仓余额表相应变动
    Assist assist = new Assist();
    assist.setRequires(Assist.andEq("extendc",tQsb.getId()));
    List<TCcyeb> tCcyebs = tCcyebMapper.selectTCcyeb(assist);
    TCcyeb tCcyeb = tCcyebs.get(0);
    tCcyeb.setZqcb(tQsb.getAmount());
    tCcyeb.setLjgz((tQsb.getYhs()-tQsb.getAmount()));
    int i1 = tCcyebMapper.updateTCcyebById(tCcyeb);
    //判断并返回处理标志
    if (i1!=0&&i!=0){
      return String.valueOf(i);
    }else{
      return "0";
    }
  }
  @Transactional
  @DeleteMapping("/TYzyshg")
  public String deleteTYzyshg(@RequestParam(value = "id",required = true)String id){
    int i = tYzyshgService.deleteTQsbById(Integer.valueOf(id));
    //持仓余额表相应变动
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
