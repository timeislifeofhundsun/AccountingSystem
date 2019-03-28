package com.hundsun.accountingsystem.Global.bean;
// 银行利息 回购利息 信息披露费 审计费 信息披露次数 审计次数
public class TLfjxb {
    private Integer id;

    private String yhlv;

    private String hglv;

    private String xxplf;

    private String sjf;

    private String xxplcs;

    private String sjcs;

    private String jylv;

    private String jslv;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYhlv() {
        return yhlv;
    }

    public void setYhlv(String yhlv) {
        this.yhlv = yhlv == null ? null : yhlv.trim();
    }

    public String getHglv() {
        return hglv;
    }

    public void setHglv(String hglv) {
        this.hglv = hglv == null ? null : hglv.trim();
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

    public String getXxplcs() {
        return xxplcs;
    }

    public void setXxplcs(String xxplcs) {
        this.xxplcs = xxplcs == null ? null : xxplcs.trim();
    }

    public String getSjcs() {
        return sjcs;
    }

    public void setSjcs(String sjcs) {
        this.sjcs = sjcs == null ? null : sjcs.trim();
    }

    public String getJylv() {
        return jylv;
    }

    public void setJylv(String jylv) {
        this.jylv = jylv == null ? null : jylv.trim();
    }

    public String getJslv() {
        return jslv;
    }

    public void setJslv(String jslv) {
        this.jslv = jslv == null ? null : jslv.trim();
    }
}