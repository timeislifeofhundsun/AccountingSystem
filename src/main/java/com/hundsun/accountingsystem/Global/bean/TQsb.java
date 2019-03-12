package com.hundsun.accountingsystem.Global.bean;

import java.util.Date;

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

    public TQsb(Integer id, Integer ztbh, Date rq, String zqcode, Integer ywlb, String bs, Integer quantity, Double amount, Double yhs, Double jsf, Double ghf, Double zgf, Double yj, Double lumpsum, String extenda, String extendb, String extendc, String extendd, String extende, String extendf, Double gyjzbd, Double zqqsk, Double cjsr, Integer sclb, Double cost) {
        this.id = id;
        this.ztbh = ztbh;
        this.rq = rq;
        this.zqcode = zqcode;
        this.ywlb = ywlb;
        this.bs = bs;
        this.quantity = quantity;
        this.amount = amount;
        this.yhs = yhs;
        this.jsf = jsf;
        this.ghf = ghf;
        this.zgf = zgf;
        this.yj = yj;
        this.lumpsum = lumpsum;
        this.extenda = extenda;
        this.extendb = extendb;
        this.extendc = extendc;
        this.extendd = extendd;
        this.extende = extende;
        this.extendf = extendf;
        this.gyjzbd = gyjzbd;
        this.zqqsk = zqqsk;
        this.cjsr = cjsr;
        this.sclb = sclb;
        this.cost = cost;
    }

    public TQsb() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getZtbh() {
        return ztbh;
    }

    public void setZtbh(Integer ztbh) {
        this.ztbh = ztbh;
    }

    public Date getRq() {
        return rq;
    }

    public void setRq(Date rq) {
        this.rq = rq;
    }

    public String getZqcode() {
        return zqcode;
    }

    public void setZqcode(String zqcode) {
        this.zqcode = zqcode == null ? null : zqcode.trim();
    }

    public Integer getYwlb() {
        return ywlb;
    }

    public void setYwlb(Integer ywlb) {
        this.ywlb = ywlb;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs == null ? null : bs.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getYhs() {
        return yhs;
    }

    public void setYhs(Double yhs) {
        this.yhs = yhs;
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

    public Double getYj() {
        return yj;
    }

    public void setYj(Double yj) {
        this.yj = yj;
    }

    public Double getLumpsum() {
        return lumpsum;
    }

    public void setLumpsum(Double lumpsum) {
        this.lumpsum = lumpsum;
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

    public String getExtendc() {
        return extendc;
    }

    public void setExtendc(String extendc) {
        this.extendc = extendc == null ? null : extendc.trim();
    }

    public String getExtendd() {
        return extendd;
    }

    public void setExtendd(String extendd) {
        this.extendd = extendd == null ? null : extendd.trim();
    }

    public String getExtende() {
        return extende;
    }

    public void setExtende(String extende) {
        this.extende = extende == null ? null : extende.trim();
    }

    public String getExtendf() {
        return extendf;
    }

    public void setExtendf(String extendf) {
        this.extendf = extendf == null ? null : extendf.trim();
    }

    public Double getGyjzbd() {
        return gyjzbd;
    }

    public void setGyjzbd(Double gyjzbd) {
        this.gyjzbd = gyjzbd;
    }

    public Double getZqqsk() {
        return zqqsk;
    }

    public void setZqqsk(Double zqqsk) {
        this.zqqsk = zqqsk;
    }

    public Double getCjsr() {
        return cjsr;
    }

    public void setCjsr(Double cjsr) {
        this.cjsr = cjsr;
    }

    public Integer getSclb() {
        return sclb;
    }

    public void setSclb(Integer sclb) {
        this.sclb = sclb;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}