package com.hundsun.accountingsystem.Global.bean;

import java.util.Date;

public class TZqxx {
    private Integer zqnm;

    private String zqdm;

    private Integer zqlb;

    private Integer sclb;

    private String zqjg;

    private Double zgb;

    private Double ltgs;

    private Double mgmz;

    private Date fxrq;

    private Date dqrq;

    private Integer hgts;

    private Double njxts;

    private Double nll;

    private Date qxr;

    private Integer fxfs;

    private Double fxjg;

    public TZqxx(Integer zqnm, String zqdm, Integer zqlb, Integer sclb, String zqjg, Double zgb, Double ltgs, Double mgmz, Date fxrq, Date dqrq, Integer hgts, Double njxts, Double nll, Date qxr, Integer fxfs, Double fxjg) {
        this.zqnm = zqnm;
        this.zqdm = zqdm;
        this.zqlb = zqlb;
        this.sclb = sclb;
        this.zqjg = zqjg;
        this.zgb = zgb;
        this.ltgs = ltgs;
        this.mgmz = mgmz;
        this.fxrq = fxrq;
        this.dqrq = dqrq;
        this.hgts = hgts;
        this.njxts = njxts;
        this.nll = nll;
        this.qxr = qxr;
        this.fxfs = fxfs;
        this.fxjg = fxjg;
    }

    public TZqxx() {
        super();
    }

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
}