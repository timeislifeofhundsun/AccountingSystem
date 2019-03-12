package com.hundsun.accountingsystem.Global.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
//证券内码 证券代码 行情日期 证券名称 昨日收盘价 今日开盘 今日收盘 成交数量 成交金额
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class THqb {
    private Integer zqnm;

    private String zqdm;

    private Date hqrq;

    private String zqmc;

    private Double zrspj;

    private Double jrkp;

    private Double jrsp;

    private Integer cjsl;

    private Double cjje;
}