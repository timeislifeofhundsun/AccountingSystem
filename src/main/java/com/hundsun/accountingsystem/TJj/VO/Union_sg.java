package com.hundsun.accountingsystem.TJj.VO;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/*
 * 该类暂时没有用
 * */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Union_sg {
	private Integer id;

    private Integer ztbh;
    
    private String ztbhname;

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
