package com.hundsun.accountingsystem.Global.bean;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ClassName GHK
 * @Aurhor yangjf25257
 * @Date 2019/3/8 15:34
 * @Version 1.0
 * @Description
 *
 **/
@ToString
@Data
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

    private Date bctime;

    private Date jstime;

    private Date cjtime;

    private String jszh;

    private String bfjzh;

    private Integer sclb;

}