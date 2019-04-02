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
  public String getAllTYzyshg(@RequestParam(value = "ckrq", required = true) String data,
                              @RequestParam(value = "indexpage") int indexpage,
                              @RequestParam(value = "sizepage") int sizepage) {
    System.out.println(data);
    //限定获取数据为银行回购
    int[] ywlb = {3103, 3104};
    //301表示为银行质押式回购 302表示为银行买断式回购 303表示交易所质押式回购
    List<TQsb> alltQsbs = tYzyshgService.findAllTQsb(ywlb, data, "301");
    List<TQsb> tQsbs = tYzyshgService.selectTQsbbyPage(ywlb, data, "301", indexpage, sizepage);
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
  public String addTYzyshg(@RequestParam(value = "TYzyshg", required = true) String data) throws ParseException {
    TQsb tQsb = JSON.parseObject(data, TQsb.class);
    //301表示银行质押式回购
    tQsb.setExtendc("301");
    //根据账套编号和会计科目获取本账套的银行存款
    TCcyeb Obj = new TCcyeb();
    Obj.setKjkmdm("100201");
    Obj.setZtbh(tQsb.getZtbh());
    TCcyeb ccyeb = tCcyebMapper.selectTCcyebByObj(Obj);
    if (ccyeb == null) {
      return "104"; //104表示数据库没有数据
    }
    double money;
    int yebd = 0;
    if (tQsb.getBs().equals("B")) {
      //设置业务类别
      tQsb.setYwlb(3103);
      //融资   银行存款增加金额
      money = tQsb.getAmount();//- tQsb.getJsf() - tQsb.getGhf()
      ccyeb.setZqcb(ccyeb.getZqcb() + money);
      yebd = tCcyebMapper.updateTCcyebById(ccyeb);
    } else {
      //设置业务类别
      tQsb.setYwlb(3104);
      //融券首先判断银行存款 金额足够则减少相应费用支出
      if (ccyeb.getZqcb() < tQsb.getAmount()) {
        return "101";//101表示银行存款不足
      } else {
        money = tQsb.getAmount() ;//+ tQsb.getJsf() + tQsb.getGhf()
        ccyeb.setZqcb(ccyeb.getZqcb() - money);
        yebd = tCcyebMapper.updateTCcyebById(ccyeb);
      }
    }
    //其他科目变动
    //获取应收利息科目值
    TCcyeb Obj1 = new TCcyeb();
    Obj1.setKjkmdm("6142");
    Obj1.setZtbh(tQsb.getZtbh());
    TCcyeb tCcyeb1 = tCcyebMapper.selectTCcyebByObj(Obj1);
    //获取回购清算款科目值
    TCcyeb Obj2 = new TCcyeb();
    Obj2.setKjkmdm("1232");
    Obj2.setZtbh(tQsb.getZtbh());
    TCcyeb tCcyeb2 = tCcyebMapper.selectTCcyebByObj(Obj2);
    //获取应付交易费用科目值
    TCcyeb Obj3 = new TCcyeb();
    Obj3.setKjkmdm("2002");
    Obj3.setZtbh(tQsb.getZtbh());
    TCcyeb tCcyeb3 = tCcyebMapper.selectTCcyebByObj(Obj3);
    //获取利息收入科目值
    TCcyeb Obj4 = new TCcyeb();
    Obj4.setKjkmdm("1142");
    Obj4.setZtbh(tQsb.getZtbh());
    TCcyeb tCcyeb4 = tCcyebMapper.selectTCcyebByObj(Obj4);
    //应收利息   如果没有科目则插入科目      融资：付利息（应收利息减少，如果为负值 则表示应付利息） 融券：收利息（应收利息增加，如果为正值 表示应收利息）
    if (tCcyeb1 == null) {
      if (tQsb.getBs().equals("B")) {
        Obj1.setZqcb(-(tQsb.getYhs() - tQsb.getAmount()));
      } else {
        Obj1.setZqcb(tQsb.getYhs() - tQsb.getAmount());
      }
      Obj1.setExtenda("应收利息-回购");
      tCcyebMapper.insertTCcyeb(Obj1);
    } else {
      if (tQsb.getBs().equals("B")) {
        tCcyeb1.setZqcb(tCcyeb1.getZqcb() - (tQsb.getYhs() - tQsb.getAmount()));
      } else {
        tCcyeb1.setZqcb(tCcyeb1.getZqcb() + (tQsb.getYhs() - tQsb.getAmount()));
      }
      tCcyebMapper.updateTCcyebById(tCcyeb1);
    }
    //回购清算款 如果没有科目则插入科目      融资：证券清算款（减少） 融券：证券清算款（增加）
    if (tCcyeb2 == null) {
      if (tQsb.getBs().equals("B")) {
        Obj2.setZqcb(-(tQsb.getAmount() - tQsb.getGhf() - tQsb.getJsf()));
      } else {
        Obj2.setZqcb(tQsb.getAmount() + tQsb.getJsf() + tQsb.getGhf());
      }
      Obj2.setExtenda("回购清算款");
      tCcyebMapper.insertTCcyeb(Obj2);
    } else {
      if (tQsb.getBs().equals("B")) {
        tCcyeb2.setZqcb(tCcyeb2.getZqcb() - (tQsb.getAmount() - tQsb.getGhf() - tQsb.getJsf()));
      } else {
        tCcyeb2.setZqcb(tCcyeb2.getZqcb() + (tQsb.getAmount() + tQsb.getJsf() + tQsb.getGhf()));
      }
      tCcyebMapper.updateTCcyebById(tCcyeb2);
    }
    //应付交易费用 如果没有科目则插入科目      融资：交易费用（增加） 融券：交易费用（增加）
    if (tCcyeb3 == null) {
      Obj3.setZqcb(tQsb.getGhf() + tQsb.getJsf());
      Obj3.setExtenda("应付交易费用-回购");
      tCcyebMapper.insertTCcyeb(Obj3);
    } else {
      tCcyeb3.setZqcb(tCcyeb3.getZqcb() + tQsb.getGhf() + tQsb.getJsf());
      tCcyebMapper.updateTCcyebById(tCcyeb3);
    }
    //利息收入   如果没有科目则插入科目      融资：付出利息（利息收入减少，如果为负值 则表示利息支出） 融券：收入利息（利息收入增加，如果为正值 表示利息收入）
    if (tCcyeb4 == null) {
      if (tQsb.getBs().equals("B")) {
        Obj4.setZqcb(-(tQsb.getYhs() - tQsb.getAmount()));
      } else {
        Obj4.setZqcb(tQsb.getYhs() - tQsb.getAmount());
      }
      Obj4.setExtenda("利息收入-回购");
      tCcyebMapper.insertTCcyeb(Obj4);
    } else {
      if (tQsb.getBs().equals("B")) {
        tCcyeb4.setZqcb(tCcyeb4.getZqcb() - (tQsb.getYhs() - tQsb.getAmount()));
      } else {
        tCcyeb4.setZqcb(tCcyeb4.getZqcb() + (tQsb.getYhs() - tQsb.getAmount()));
      }
      tCcyebMapper.updateTCcyebById(tCcyeb4);
    }
    int i = 0, i1 = 0;
    //持仓变动
    if (yebd == 0) {
      return "103"; //103表示金额扣款或增额失败
    } else {
      i = tYzyshgService.insertTQsb(tQsb);
      //持仓余额表相应插入一条新数据
      TCcyeb tCcyeb = new TCcyeb();
      tCcyeb.setZqdm(tQsb.getZqcode());
      tCcyeb.setZtbh(tQsb.getZtbh());
      tCcyeb.setZqcb(tQsb.getAmount());
      tCcyeb.setExtendc(String.valueOf(tQsb.getId()));
      tCcyeb.setLjjx((tQsb.getYhs() - tQsb.getAmount()));
      tCcyeb.setFsrq(DateFormatUtil.getDateByString(tQsb.getExtenda()));
      tCcyeb.setExtenda("3101");
      tCcyeb.setExtendb(tQsb.getExtendb());
      i1 = tCcyebMapper.insertTCcyeb(tCcyeb);
    }
    //判断并返回处理标志
    if (i1 != 0 && i != 0) {
      return String.valueOf(i);
    } else {
      return "102"; //102表示插入数据失败
    }
  }

  @Transactional
  @PutMapping("/TYzyshg")
  public String updateTYzyshg(@RequestParam(value = "TYzyshg", required = true) String data) {
    TQsb tQsb = JSON.parseObject(data, TQsb.class);
    tQsb.setExtendc("301");
    if (tQsb.getBs().equals("B")) {
      tQsb.setYwlb(3103);
    } else {
      tQsb.setYwlb(3104);
    }
    //银行存款变动
    TQsb tQsbd = tQsbMapper.selectTQsbById(Integer.valueOf(tQsb.getId()));
    if (tQsbd == null) {
      return "104";//104表示数据库没有数据
    }
    TCcyeb Obj = new TCcyeb();
    Obj.setKjkmdm("100201");
    Obj.setZtbh(tQsb.getZtbh());
    TCcyeb ccyeb = tCcyebMapper.selectTCcyebByObj(Obj);
    if (ccyeb == null) {
      return "104";//104表示数据库没有数据
    }
    double money, money1;
    int yebd;
    if (tQsbd.getBs().equals("B")) {
      //融资修改=修改增加的融资金额
      if (ccyeb.getZqcb() < tQsbd.getAmount()) {
        return "101";//101表示银行存款不足
      } else {
        money = tQsbd.getAmount() ;//- tQsbd.getJsf() - tQsbd.getGhf()
        money1 = tQsb.getAmount() ;//- tQsb.getJsf() - tQsb.getGhf()
        if (money > money1) {
          //修改后的融资金额小  则数据库 银行存款相应地减少差额即可
          ccyeb.setZqcb(ccyeb.getZqcb() - (money - money1));
        } else {
          //修改后的融资金额大  则数据库 银行存款相应地增加差额即可
          ccyeb.setZqcb(ccyeb.getZqcb() + (money1 - money));
        }
        yebd = tCcyebMapper.updateTCcyebById(ccyeb);
      }
    } else {
      //融券修改==修改减少的融券金额
      money = tQsbd.getAmount() ;//+ tQsbd.getJsf() + tQsbd.getGhf()
      money1 = tQsb.getAmount() ;//+ tQsb.getJsf() + tQsb.getGhf()
      if (money > money1) {
        //修改后的融券金额小  则数据库 银行存款相应地增加差额即可
        ccyeb.setZqcb(ccyeb.getZqcb() + (money - money1));
      } else {
        //修改后的融券金额大  则数据库 银行存款相应地增加差额即可
        ccyeb.setZqcb(ccyeb.getZqcb() - (money1 - money));
      }
      yebd = tCcyebMapper.updateTCcyebById(ccyeb);
    }
    if (yebd == 0) {
      return "103";//103表示金额扣款或增额失败
    }
    //余额表变动
    //获取应收利息科目值
    TCcyeb Obj1 = new TCcyeb();
    Obj1.setKjkmdm("6142");
    Obj1.setZtbh(tQsb.getZtbh());
    TCcyeb tCcyeb1 = tCcyebMapper.selectTCcyebByObj(Obj1);
    //获取回购清算款科目值
    TCcyeb Obj2 = new TCcyeb();
    Obj2.setKjkmdm("1232");
    Obj2.setZtbh(tQsb.getZtbh());
    TCcyeb tCcyeb2 = tCcyebMapper.selectTCcyebByObj(Obj2);
    //获取应付交易费用科目值
    TCcyeb Obj3 = new TCcyeb();
    Obj3.setKjkmdm("2002");
    Obj3.setZtbh(tQsb.getZtbh());
    TCcyeb tCcyeb3 = tCcyebMapper.selectTCcyebByObj(Obj3);
    //获取利息收入科目值
    TCcyeb Obj4 = new TCcyeb();
    Obj4.setKjkmdm("1142");
    Obj4.setZtbh(tQsb.getZtbh());
    TCcyeb tCcyeb4 = tCcyebMapper.selectTCcyebByObj(Obj4);
    //应收利息  融资:修改后值变小 利息应增加差值 融券：修改后值变小 利息应减少差值
    Double before;
    Double after;
    if (tQsb.getBs().equals("B")) {
      before = tQsbd.getYhs() - tQsbd.getAmount();
      after = tQsb.getYhs() - tQsb.getAmount();
      if (before > after) {
        tCcyeb1.setZqcb(tCcyeb1.getZqcb() + (before - after));
      } else {
        tCcyeb1.setZqcb(tCcyeb1.getZqcb() - (after - before));
      }
    } else {
      before = tQsbd.getYhs() - tQsbd.getAmount();
      after = tQsb.getYhs() - tQsb.getAmount();
      if (before > after) {
        tCcyeb1.setZqcb(tCcyeb1.getZqcb() - (before - after));
      } else {
        tCcyeb1.setZqcb(tCcyeb1.getZqcb() + (after - before));
      }
    }
    tCcyebMapper.updateTCcyebById(tCcyeb1);
    //回购清算款      融资：修改值变小 应加上差值 融券：修改后值变小 应减少差值
    if (tQsb.getBs().equals("B")) {
      if (tQsbd.getBs().equals("B")) {
        before = tQsbd.getAmount() - tQsbd.getGhf() - tQsbd.getJsf();
      } else {
        before = tQsbd.getAmount() + tQsbd.getGhf() + tQsbd.getJsf();
      }
      after = tQsb.getAmount() - tQsb.getGhf() - tQsb.getJsf();
      if (before > after) {
        tCcyeb2.setZqcb(tCcyeb2.getZqcb() + (before - after));
      } else {
        tCcyeb2.setZqcb(tCcyeb2.getZqcb() - (after - before));
      }
    } else {
      if (tQsbd.getBs().equals("B")) {
        before = tQsbd.getAmount() - tQsbd.getGhf() - tQsbd.getJsf();
      } else {
        before = tQsbd.getAmount() + tQsbd.getGhf() + tQsbd.getJsf();
      }
      after = tQsb.getAmount() + tQsb.getGhf() + tQsb.getJsf();
      if (before > after) {
        tCcyeb2.setZqcb(tCcyeb2.getZqcb() - (before - after));
      } else {
        tCcyeb2.setZqcb(tCcyeb2.getZqcb() + (after - before));
      }
    }
    tCcyebMapper.updateTCcyebById(tCcyeb2);

    //应付交易费用  融资、融券：修改后值变小 交易费用减少
    before = tQsbd.getGhf() + tQsbd.getJsf();
    after = tQsb.getGhf() + tQsb.getJsf();
    if (before > after) {
      tCcyeb3.setZqcb(tCcyeb3.getZqcb() - (before - after));
    } else {
      tCcyeb3.setZqcb(tCcyeb3.getZqcb() + (after - before));
    }
    tCcyebMapper.updateTCcyebById(tCcyeb3);

    //利息收入      融资：付出利息（利息收入减少，如果为负值 则表示利息支出） 融券：收入利息（利息收入增加，如果为正值 表示利息收入）
    if (tQsb.getBs().equals("B")) {
      before = tQsbd.getYhs() - tQsbd.getAmount();
      after = tQsb.getYhs() - tQsb.getAmount();
      if (before > after) {
        tCcyeb4.setZqcb(tCcyeb4.getZqcb() + (before - after));
      } else {
        tCcyeb4.setZqcb(tCcyeb4.getZqcb() - (after - before));
      }
    } else {
      before = tQsbd.getYhs() - tQsbd.getAmount();
      after = tQsb.getYhs() - tQsb.getAmount();
      if (before > after) {
        tCcyeb4.setZqcb(tCcyeb4.getZqcb() - (before - after));
      } else {
        tCcyeb4.setZqcb(tCcyeb4.getZqcb() + (after - before));
      }
    }
    tCcyebMapper.updateTCcyebById(tCcyeb4);
    //清算表变动
    int i = tYzyshgService.updateTQsbById(tQsb);
    //持仓余额表相应变动
    Assist assist = new Assist();
    assist.setRequires(Assist.andEq("extendc", tQsb.getId()));
    List<TCcyeb> tCcyebs = tCcyebMapper.selectTCcyeb(assist);
    TCcyeb tCcyeb = tCcyebs.get(0);
    tCcyeb.setZqcb(tQsb.getAmount());
    tCcyeb.setLjjx((tQsb.getYhs() - tQsb.getAmount()));
    int i1 = tCcyebMapper.updateTCcyebById(tCcyeb);
    //判断并返回处理标志
    if (i1 != 0 && i != 0) {
      return String.valueOf(i);
    } else {
      return "102";//102表示更新数据失败
    }
  }

  @Transactional
  @DeleteMapping("/TYzyshg")
  public String deleteTYzyshg(@RequestParam(value = "id", required = true) String id) {
    //将银行存款恢复
    TQsb tQsb = tQsbMapper.selectTQsbById(Integer.valueOf(id));
    if (tQsb == null) {
      return "104";//104表示数据库没有数据
    }
    TCcyeb Obj = new TCcyeb();
    Obj.setKjkmdm("100201");
    Obj.setZtbh(tQsb.getZtbh());
    TCcyeb ccyeb = tCcyebMapper.selectTCcyebByObj(Obj);
    if (ccyeb == null) {
      return "104";//104表示数据库没有数据
    }
    double money;
    int yebd;
    if (tQsb.getBs().equals("B")) {
      //融资扣掉融资金额
      if (ccyeb.getZqcb() < tQsb.getAmount()) {
        return "101";//101表示银行存款不足
      } else {
        money = tQsb.getAmount(); //- tQsb.getJsf() - tQsb.getGhf()
        ccyeb.setZqcb(ccyeb.getZqcb() - money);
        yebd = tCcyebMapper.updateTCcyebById(ccyeb);
      }
    } else {
      //融券添加金额
      money = tQsb.getAmount(); // + tQsb.getJsf() + tQsb.getGhf()
      ccyeb.setZqcb(ccyeb.getZqcb() + money);
      yebd = tCcyebMapper.updateTCcyebById(ccyeb);
    }
    if (yebd == 0) {
      return "103";//103表示金额扣款或增额失败
    }
    //余额表变动
    //获取应收利息科目值
    TCcyeb Obj1 = new TCcyeb();
    Obj1.setKjkmdm("6142");
    Obj1.setZtbh(tQsb.getZtbh());
    TCcyeb tCcyeb1 = tCcyebMapper.selectTCcyebByObj(Obj1);
    //获取回购清算款科目值
    TCcyeb Obj2 = new TCcyeb();
    Obj2.setKjkmdm("1232");
    Obj2.setZtbh(tQsb.getZtbh());
    TCcyeb tCcyeb2 = tCcyebMapper.selectTCcyebByObj(Obj2);
    //获取应付交易费用科目值
    TCcyeb Obj3 = new TCcyeb();
    Obj3.setKjkmdm("2002");
    Obj3.setZtbh(tQsb.getZtbh());
    TCcyeb tCcyeb3 = tCcyebMapper.selectTCcyebByObj(Obj3);
    //获取利息收入科目值
    TCcyeb Obj4 = new TCcyeb();
    Obj4.setKjkmdm("1142");
    Obj4.setZtbh(tQsb.getZtbh());
    TCcyeb tCcyeb4 = tCcyebMapper.selectTCcyebByObj(Obj4);
    //应收利息     融资：付利息 删除时应加上减少值 融券：收利息 删除时应减去增加值
    if (tQsb.getBs().equals("B")) {
      tCcyeb1.setZqcb(tCcyeb1.getZqcb() + (tQsb.getYhs() - tQsb.getAmount()));
    } else {
      tCcyeb1.setZqcb(tCcyeb1.getZqcb() - (tQsb.getYhs() - tQsb.getAmount()));
    }
    tCcyebMapper.updateTCcyebById(tCcyeb1);
    //回购清算款  融资：证券清算款（删除时应加上减少值） 融券：证券清算款（删除时应减掉增加值）
    if (tQsb.getBs().equals("B")) {
      tCcyeb2.setZqcb(tCcyeb2.getZqcb() + (tQsb.getAmount() - tQsb.getGhf() - tQsb.getJsf()));
    } else {
      tCcyeb2.setZqcb(tCcyeb2.getZqcb() - (tQsb.getAmount() + tQsb.getJsf() + tQsb.getGhf()));
    }
    tCcyebMapper.updateTCcyebById(tCcyeb2);
    //应付交易费用     融资：交易费用（删除时减少） 融券：交易费用（删除时减少）
    tCcyeb3.setZqcb(tCcyeb3.getZqcb() - tQsb.getGhf() - tQsb.getJsf());
    tCcyebMapper.updateTCcyebById(tCcyeb3);
    //利息收入       融资：付出利息（删除时应加上减少值） 融券：收入利息（删除时应减到增加值）
    if (tQsb.getBs().equals("B")) {
      tCcyeb4.setZqcb(tCcyeb4.getZqcb() + (tQsb.getYhs() - tQsb.getAmount()));
    } else {
      tCcyeb4.setZqcb(tCcyeb4.getZqcb() - (tQsb.getYhs() - tQsb.getAmount()));
    }
    tCcyebMapper.updateTCcyebById(tCcyeb4);
    int i = tYzyshgService.deleteTQsbById(Integer.valueOf(id));
    //持仓余额表相应变动
    Assist assist = new Assist();
    assist.setRequires(Assist.andEq("extendc", id));
    int i1 = tCcyebMapper.deleteTCcyeb(assist);
    //判断并返回处理标志
    if (i1 != 0 && i != 0) {
      return String.valueOf(i);
    } else {
      return "102";//102表示删除数据失败
    }
  }
}
