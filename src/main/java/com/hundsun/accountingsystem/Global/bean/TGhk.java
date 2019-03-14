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
    private Integer ywlsid;//业务流水id
    private String bfjzh;//备付金账号
    private String jszh;//结算账号
    private Integer ztcode;//账套编号
    private Double cjjg;//成交价格
    private java.util.Date bctime;//保存时间
    private Double cjje;//成交金额
    private String xwcode;//席位代码
    private Integer cjsl;//成交数量
    private java.util.Date cjtime;//成交时间
    private String bs;//买卖方向
    private String zqcode;//证券代码
    private Integer sclb;//市场类别
    private String gdcode;//股东代码
    private java.util.Date jstime;//交收时间
    private String gdname;//股东姓名

}
