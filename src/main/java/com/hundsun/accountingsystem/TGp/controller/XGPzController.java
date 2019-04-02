package com.hundsun.accountingsystem.TGp.controller;

import com.hundsun.accountingsystem.Global.bean.TPzb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.TGp.service.XgPzbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
    * @Author yangjf25257
    * @MethodName get_pz
     * @Param [tQsb]
     * @Return java.lang.String
     * @Description 获取凭证controller，根据账套编号和时间
     **/
    @PostMapping("/get_pz")
    public String get_pz(TQsb tQsb){
        List<TPzb> tPzbs_list = xgPzbService.get_pz(tQsb);
        return "";
    }

}
