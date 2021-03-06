package com.hundsun.accountingsystem.Global.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * @ClassName TQsb
 * @Aurhor yangjf25257
 * @Date 2019/3/8 15:34
 * @Version 1.0
 * @Description 属性： 编号 账套编号 时间 证券代码 业务类别 买卖方向 数量 金额 印花税 经手费 过户费 证管费 佣金 费用合计 六个扩展 成本  公允价值变动
 *                     证券清算款 差价收入 市场类别
 **/
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class TQsb {
    private Integer id;

    private Integer ztbh;

    @JSONField(format = "yyyy-MM-dd")
    private Date rq;//新股情况代表清算日期ok

    private String zqcode;

    private Integer ywlb;

    private String bs;

    private Integer quantity;

    private Double amount;

    private Double yhs;

    private Double jsf;

    private Double ghf;

    private Double zgf;

    private Double yj;

    private Double lumpsum;//新股中代表累计估值增值

    private String extenda;//新股情况下代表交收时期,红股情况代表到账日期

    private String extendb;//新股情况代表交易日期

    private String extendc;//新股代表价格

    private String extendd;

    private String extende;

    private String extendf;

    private Double gyjzbd;

    private Double zqqsk;

    private Double cjsr;

    private Integer sclb;

    private Double cost;

}