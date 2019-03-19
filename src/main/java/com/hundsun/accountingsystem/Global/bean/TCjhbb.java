package com.hundsun.accountingsystem.Global.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class TCjhbb {
    private Integer id;

    private String gddm;

    private String xwbh;

    private String zqdm;

    private Integer jysc;

    private Integer cjsl;

    private Double cjjg;

    private Double cjje;

    private String mmfx;

    private Integer ztbh;

    private Date ywrq;

    private Integer ywlb;

    private Double jsf;

    private Double ghf;

    private Double zgf;

    private Double yhs;

    private Double jyfy;

    private String extenda;

    private String extendb;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGddm() {
        return gddm;
    }

    public void setGddm(String gddm) {
        this.gddm = gddm == null ? null : gddm.trim();
    }

    public String getXwbh() {
        return xwbh;
    }

    public void setXwbh(String xwbh) {
        this.xwbh = xwbh == null ? null : xwbh.trim();
    }

    public String getZqdm() {
        return zqdm;
    }

    public void setZqdm(String zqdm) {
        this.zqdm = zqdm == null ? null : zqdm.trim();
    }

    public Integer getJysc() {
        return jysc;
    }

    public void setJysc(Integer jysc) {
        this.jysc = jysc;
    }

    public Integer getCjsl() {
        return cjsl;
    }

    public void setCjsl(Integer cjsl) {
        this.cjsl = cjsl;
    }

    public Double getCjjg() {
        return cjjg;
    }

    public void setCjjg(Double cjjg) {
        this.cjjg = cjjg;
    }

    public Double getCjje() {
        return cjje;
    }

    public void setCjje(Double cjje) {
        this.cjje = cjje;
    }

    public String getMmfx() {
        return mmfx;
    }

    public void setMmfx(String mmfx) {
        this.mmfx = mmfx == null ? null : mmfx.trim();
    }

    public Integer getZtbh() {
        return ztbh;
    }

    public void setZtbh(Integer ztbh) {
        this.ztbh = ztbh;
    }
    
    @JSONField(format = "yyyy-MM-dd")
    public Date getYwrq() {
        return ywrq;
    }

    public void setYwrq(Date ywrq) {
        this.ywrq = ywrq;
    }

    public Integer getYwlb() {
        return ywlb;
    }

    public void setYwlb(Integer ywlb) {
        this.ywlb = ywlb;
    }

    public Double getJsf() {
        return jsf;
    }

    public void setJsf(Double jsf) {
        this.jsf = jsf;
    }

    public Double getGhf() {
        return ghf;
    }

    public void setGhf(Double ghf) {
        this.ghf = ghf;
    }

    public Double getZgf() {
        return zgf;
    }

    public void setZgf(Double zgf) {
        this.zgf = zgf;
    }

    public Double getYhs() {
        return yhs;
    }

    public void setYhs(Double yhs) {
        this.yhs = yhs;
    }

    public Double getJyfy() {
        return jyfy;
    }

    public void setJyfy(Double jyfy) {
        this.jyfy = jyfy;
    }

    public String getExtenda() {
        return extenda;
    }

    public void setExtenda(String extenda) {
        this.extenda = extenda == null ? null : extenda.trim();
    }

    public String getExtendb() {
        return extendb;
    }

    public void setExtendb(String extendb) {
        this.extendb = extendb == null ? null : extendb.trim();
    }
}