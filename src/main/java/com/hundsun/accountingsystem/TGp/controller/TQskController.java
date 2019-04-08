package com.hundsun.accountingsystem.TGp.controller;

import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.TGp.service.XgQsbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Aurhor yangjf25257
 * @ClassName TQskController
 * @Date 2019/4/1 14:01
 * @Version 1.0
 * @Description 新股网下申购Controller类
 **/
@RestController
public class TQskController {

    @Autowired
    XgQsbService xgQsbService;

    /**
    * @Author yangjf25257
    * @MethodName wx_insest_qsk
     * @Param [tQsb]
     * @Return java.lang.String
     * @Description 网下申购Controller
     **/
    @PostMapping("/xg_wx")
    public String xg_wx(TQsb tQsb){
        if (tQsb != null){
            return xgQsbService.xg_wx(tQsb);
        } else {
            return "未传入参数";
        }
    }

    /**
    * @Author yangjf25257
    * @MethodName sclt_wx
     * @Param [tQsb]
     * @Return java.lang.String
     * @Description 网下市场流通Controller
     **/
    @PostMapping("/sclt_wx")
    public String sclt_wx(TQsb tQsb){
        if (tQsb != null){
            return xgQsbService.sclt_wx(tQsb);
        } else {
            return "未传入参数";
        }
    }

    /**
    * @Author yangjf25257
    * @MethodName get_ywls
     * @Param [tQsb]
     * @Return java.lang.String
     * @Description 获取业务流水Controller，根据业务类别，账套编号，时间。
     **/
    @PostMapping("/get_ywls")
    public String get_ywls(TQsb tQsb){
        List<TQsb> list = xgQsbService.get_yels(tQsb);
        return "获取成功";
    }



}
