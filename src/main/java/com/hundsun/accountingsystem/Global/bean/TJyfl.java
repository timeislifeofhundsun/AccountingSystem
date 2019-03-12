package com.hundsun.accountingsystem.Global.bean;

public class TJyfl {
    private Integer ywcode;

    private String ywname;

    private Double jsfl;

    private Double yh;

    private Double gh;

    private Double zg;

    private Double yj;

    public TJyfl(Integer ywcode, String ywname, Double jsfl, Double yh, Double gh, Double zg, Double yj) {
        this.ywcode = ywcode;
        this.ywname = ywname;
        this.jsfl = jsfl;
        this.yh = yh;
        this.gh = gh;
        this.zg = zg;
        this.yj = yj;
    }

    public TJyfl() {
        super();
    }

    public Integer getYwcode() {
        return ywcode;
    }

    public void setYwcode(Integer ywcode) {
        this.ywcode = ywcode;
    }

    public String getYwname() {
        return ywname;
    }

    public void setYwname(String ywname) {
        this.ywname = ywname == null ? null : ywname.trim();
    }

    public Double getJsfl() {
        return jsfl;
    }

    public void setJsfl(Double jsfl) {
        this.jsfl = jsfl;
    }

    public Double getYh() {
        return yh;
    }

    public void setYh(Double yh) {
        this.yh = yh;
    }

    public Double getGh() {
        return gh;
    }

    public void setGh(Double gh) {
        this.gh = gh;
    }

    public Double getZg() {
        return zg;
    }

    public void setZg(Double zg) {
        this.zg = zg;
    }

    public Double getYj() {
        return yj;
    }

    public void setYj(Double yj) {
        this.yj = yj;
    }
}