package com.hundsun.accountingsystem.Global.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
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

    private Date rq;

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

    private Double lumpsum;

    private String extenda;

    private String extendb;

    private String extendc;

    private String extendd;

    private String extende;

    private String extendf;

    private Double gyjzbd;

    private Double zqqsk;

    private Double cjsr;

    private Integer sclb;

    private Double cost;

}