package com.hundsun.accountingsystem.TGp.controller;

import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.TGp.service.XgQsbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Aurhor yangjf25257
 * @ClassName TQskController
 * @Date 2019/4/1 14:01
 * @Version 1.0
 * @Description 新股网下申购Controller类
 **/
@Controller
public class TQskController {

    @Autowired
    XgQsbService xgQsbService;

    /**
    * @Author yangjf25257
    * @MethodName wx_insest_qsk
     * @Param [tQsb]
     * @Return java.lang.String
     * @Description 网下申购方法
     **/
    @PostMapping("/xg_wx")
    public String xg_wx(TQsb tQsb){
        if (tQsb != null){
            return xgQsbService.xg_wx(tQsb);
        } else {
            return "未传入参数";
        }
    }

    public String sclt_wx(TQsb tQsb){
        if (tQsb != null){
            return xgQsbService.sclt_wx(tQsb);
        } else {
            return "未传入参数";
        }
    }

}
