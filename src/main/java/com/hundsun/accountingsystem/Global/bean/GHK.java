package com.hundsun.accountingsystem.Global.bean;

import lombok.ToString;
import lombok.experimental.Accessors;

import javax.xml.crypto.Data;

/**
 * @ClassName GHK
 * @Aurhor yangjf25257
 * @Date 2019/3/8 17:07
 * @Version 1.0
 * @Description 过户库VO
 *              属性有：业务流水号 股东代码 股东姓名 席位代码 账套代码 成交数量 成交价格 成交金额 证券代码 买/卖 保存时间 交收时间 成交时间
 *              结算账号 备付金账户
 **/
@ToString
@lombok.Data
@Accessors(chain = true)
public class GHK {
    private Integer ywlsid;
    private String gdcode;
    private String gdname;
    private String xwcode;
    private Integer ztcode;
    private Integer cjsl;
    private Double cjje;
    private Double cjjg;
    private String zqcode;
    private String bs;
    private Data bctime;
    private Data jstime;
    private Data cjtime;
    private String jszh;
    private String bfjzh;
}
