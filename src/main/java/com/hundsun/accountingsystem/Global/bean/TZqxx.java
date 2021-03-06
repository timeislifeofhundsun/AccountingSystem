package com.hundsun.accountingsystem.Global.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
// 证券内码 证券代码 证券类别 市场类别 证券简称 总股本 流通股数 每股面值 发行日期 到期日期 回购天数 年计息天数 年利率 起息日 发行方式 发行价格
public class TZqxx {
    private Integer zqnm;

    private String zqdm;

    private Integer zqlb;

    private Integer sclb;

    private String zqjg;

    private Double zgb;

    private Double ltgs;

    private Double mgmz;

    @JSONField(format = "yyyy-MM-dd")
    private Date fxrq;
    @JSONField(format = "yyyy-MM-dd")
    private Date dqrq;

    private Integer hgts;

    private Double njxts;

    private Double nll;

    @JSONField(format = "yyyy-MM-dd")
    private Date qxr;

    private Integer fxfs;

    private Double fxjg;

    public Integer getZqnm() {
        return zqnm;
    }

    public void setZqnm(Integer zqnm) {
        this.zqnm = zqnm;
    }

    public String getZqdm() {
        return zqdm;
    }

    public void setZqdm(String zqdm) {
        this.zqdm = zqdm == null ? null : zqdm.trim();
    }

    public Integer getZqlb() {
        return zqlb;
    }

    public void setZqlb(Integer zqlb) {
        this.zqlb = zqlb;
    }

    public Integer getSclb() {
        return sclb;
    }

    public void setSclb(Integer sclb) {
        this.sclb = sclb;
    }

    public String getZqjg() {
        return zqjg;
    }

    public void setZqjg(String zqjg) {
        this.zqjg = zqjg == null ? null : zqjg.trim();
    }

    public Double getZgb() {
        return zgb;
    }

    public void setZgb(Double zgb) {
        this.zgb = zgb;
    }

    public Double getLtgs() {
        return ltgs;
    }

    public void setLtgs(Double ltgs) {
        this.ltgs = ltgs;
    }

    public Double getMgmz() {
        return mgmz;
    }

    public void setMgmz(Double mgmz) {
        this.mgmz = mgmz;
    }

    public Date getFxrq() {
        return fxrq;
    }

    public void setFxrq(Date fxrq) {
        this.fxrq = fxrq;
    }

    public Date getDqrq() {
        return dqrq;
    }

    public void setDqrq(Date dqrq) {
        this.dqrq = dqrq;
    }

    public Integer getHgts() {
        return hgts;
    }

    public void setHgts(Integer hgts) {
        this.hgts = hgts;
    }

    public Double getNjxts() {
        return njxts;
    }

    public void setNjxts(Double njxts) {
        this.njxts = njxts;
    }

    public Double getNll() {
        return nll;
    }

    public void setNll(Double nll) {
        this.nll = nll;
    }

    public Date getQxr() {
        return qxr;
    }

    public void setQxr(Date qxr) {
        this.qxr = qxr;
    }

    public Integer getFxfs() {
        return fxfs;
    }

    public void setFxfs(Integer fxfs) {
        this.fxfs = fxfs;
    }

    public Double getFxjg() {
        return fxjg;
    }

    public void setFxjg(Double fxjg) {
        this.fxjg = fxjg;
    }

    @Override
    public String toString() {
        return "TZqxx{" +
            "zqnm=" + zqnm +
            ", zqdm='" + zqdm + '\'' +
            ", zqlb=" + zqlb +
            ", sclb=" + sclb +
            ", zqjg='" + zqjg + '\'' +
            ", zgb=" + zgb +
            ", ltgs=" + ltgs +
            ", mgmz=" + mgmz +
            ", fxrq=" + fxrq +
            ", dqrq=" + dqrq +
            ", hgts=" + hgts +
            ", njxts=" + njxts +
            ", nll=" + nll +
            ", qxr=" + qxr +
            ", fxfs=" + fxfs +
            ", fxjg=" + fxjg +
            '}';
    }
}