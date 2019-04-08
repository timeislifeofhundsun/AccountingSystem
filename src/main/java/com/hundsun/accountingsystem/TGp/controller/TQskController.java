package com.hundsun.accountingsystem.TGp.controller;

import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.TGp.service.XgQsbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Aurhor yangjf25257
 * @ClassName TQskController
 * @Date 2019/4/1 14:01
 * @Version 1.0
 * @Description 新股网下申购Controller类
 **/
@Slf4j
@RestController
public class TQskController {

    @Autowired
    XgQsbService xgQsbService;

    /**
    * @Author yangjf25257
    * @MethodName wx_insest_qsk
     * @Param [tQsb]
     * @Return java.lang.String
     * @Description 网下新股中签Controller
     **/
    @PostMapping("/xg_wx")
    public String xg_wx(@RequestParam(value = "xgzq",required = true) String data){
        TQsb tQsb = JSON.parseObject(data,TQsb.class);
        log.info("网下的数据： " + tQsb.toString());
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
    public String sclt_wx(@RequestParam(value = "xgzq",required = true) String data){
        TQsb tQsb = JSON.parseObject(data,TQsb.class);
        log.info("网下流通的数据： " + tQsb.toString());
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
