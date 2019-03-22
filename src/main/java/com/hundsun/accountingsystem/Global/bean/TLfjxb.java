package com.hundsun.accountingsystem.Global.bean;

public class TLfjxb {
    private Integer id;

    private String yhlx;

    private String hglx;

    private String xxplf;

    private String sjf;

    private Integer xxplcs;

    private Integer sjcs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYhlx() {
        return yhlx;
    }

    public void setYhlx(String yhlx) {
        this.yhlx = yhlx == null ? null : yhlx.trim();
    }

    public String getHglx() {
        return hglx;
    }

    public void setHglx(String hglx) {
        this.hglx = hglx == null ? null : hglx.trim();
    }

    public String getXxplf() {
        return xxplf;
    }

    public void setXxplf(String xxplf) {
        this.xxplf = xxplf == null ? null : xxplf.trim();
    }

    public String getSjf() {
        return sjf;
    }

    public void setSjf(String sjf) {
        this.sjf = sjf == null ? null : sjf.trim();
    }

    public Integer getXxplcs() {
        return xxplcs;
    }

    public void setXxplcs(Integer xxplcs) {
        this.xxplcs = xxplcs;
    }

    public Integer getSjcs() {
        return sjcs;
    }

    public void setSjcs(Integer sjcs) {
        this.sjcs = sjcs;
    }
}