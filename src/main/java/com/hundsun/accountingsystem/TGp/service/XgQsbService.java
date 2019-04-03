package com.hundsun.accountingsystem.TGp.service;

import com.hundsun.accountingsystem.Global.bean.TQsb;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @Aurhor yangjf25257
 * @InterfaceName XgQsbService
 * @Date 2019/3/22 10:14
 * @Version 1.0
 * @Description 新股清算库Servie层
 **/
public interface XgQsbService {
    String  xgqs(String path_xg_qsk, String path_sclt_qsk, int ztbh, Date date) throws ParseException;
    String xg_wx(TQsb tQsb);
    String sclt_wx(TQsb tQsb);
    List<TQsb> get_yels(TQsb tQsb);

}
