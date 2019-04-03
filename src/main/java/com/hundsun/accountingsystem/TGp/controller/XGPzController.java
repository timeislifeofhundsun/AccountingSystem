package com.hundsun.accountingsystem.TGp.controller;

import com.hundsun.accountingsystem.Global.bean.TPzb;
import com.hundsun.accountingsystem.TGp.service.GPPZService;
import com.hundsun.accountingsystem.TGp.service.XgPzbService;
import com.hundsun.accountingsystem.TJj.service.TjjScpzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Aurhor yangjf25257
 * @ClassName XGPzController
 * @Date 2019/4/2 9:59
 * @Version 1.0
 * @Description
 **/
@RestController
public class XGPzController {

    @Autowired
    XgPzbService xgPzbService;

    @Autowired
    GPPZService gppzService;

    @Autowired
    TjjScpzService tjjScpzService;

    /**
    * @Author yangjf25257
    * @MethodName get_pz
     * @Param [tQsb]
     * @Return java.lang.String
     * @Description 获取凭证controller，根据账套编号和时间
     **/
    @PostMapping("/inerst_pz")
    public String insert_pz(int ztbh, Date rq) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        tjjScpzService.scpz(ztbh,sdf.format(rq));
        gppzService.insertGPPZ(ztbh, rq);
        xgPzbService.insert_pz(ztbh, rq);
        return "";
    }

    /**
    * @Author yangjf25257
    * @MethodName get_pz
     * @Param [ztbh, rq]
     * @Return java.lang.String
     * @Description 获取凭证
     **/
    @PostMapping("/get_pz")
    public String get_pz(int ztbh, Date rq){
        List<TPzb> list = xgPzbService.get_pz(ztbh, rq);
        return "";
    }

    /**
    * @Author yangjf25257
    * @MethodName get_bb
     * @Param [ztbh]
     * @Return java.lang.String
     * @Description 获取报表
     **/
    @PostMapping("/get_bb")
    public String get_bb(int ztbh){
      List<TPzb> bb_list = xgPzbService.get_bb(ztbh);
        return "";
    }

}
