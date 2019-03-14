package com.hundsun.accountingsystem.Global.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @ClassName TGhk
 * @Date 2019/3/8 15:34
 * @Version 1.0
 * @Description 属性：业务流水号 股东代码 股东姓名 席位代码 账套编号 成交数量 成交价格 成交金额 证券代码 买卖 保存时间 交收时间 成交时间 结算账号
 * 备付金账户 市场类别
 **/

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class TGhk implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Integer ywlsid;
    private String bfjzh;
    private String jszh;
    private Integer ztcode;//账套编号
    private Double cjjg;
    private java.util.Date bctime;
    private Double cjje;
    private String xwcode;
    private Integer cjsl;
    private java.util.Date cjtime;
    private String bs;
    private String zqcode;
    private Integer sclb;
    private String gdcode;
    private java.util.Date jstime;
    private String gdname;

}
