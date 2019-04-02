package com.hundsun.accountingsystem.TGp.service;

import com.hundsun.accountingsystem.Global.bean.TPzb;
import com.hundsun.accountingsystem.Global.bean.TQsb;

import java.util.List;

/**
 * @Aurhor yangjf25257
 * @ClassName XgPzbService
 * @Date 2019/3/22 10:15
 * @Version 1.0
 * @Description
 **/
public interface XgPzbService {
    List<TPzb> get_pz(TQsb tQsb);

}
