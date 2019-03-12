package com.hundsun.accountingsystem.Global.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
/**
 * @ClassName TGhk
 * @Aurhor yangjf25257
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
public class TGhk {
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

    private Date bctime;

    private Date jstime;

    private Date cjtime;

    private String jszh;

    private String bfjzh;

    private Integer sclb;

}