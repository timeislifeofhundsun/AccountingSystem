package com.hundsun.accountingsystem.TGp.service;

import java.text.ParseException;
import java.util.Date;

/**
 * @Aurhor yangjf25257
 * @InterfaceName XgQsbService
 * @Date 2019/3/22 10:14
 * @Version 1.0
 * @Description 新股清算库Servie层
 **/
public interface XgQsbService {
    String  xgqs(String path_xg_qsk, String path_sclt_qsk, int ztbh, Date date) throws ParseException;
}
