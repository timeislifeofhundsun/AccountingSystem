package com.hundsun.accountingsystem.Global.bean;

public class TGdxxb {
    private String gddm;

    private Integer ztbh;

    private String name;

    private String xwbh;

    public String getGddm() {
        return gddm;
    }

    public void setGddm(String gddm) {
        this.gddm = gddm == null ? null : gddm.trim();
    }

    public Integer getZtbh() {
        return ztbh;
    }

    public void setZtbh(Integer ztbh) {
        this.ztbh = ztbh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getXwbh() {
        return xwbh;
    }

    public void setXwbh(String xwbh) {
        this.xwbh = xwbh == null ? null : xwbh.trim();
    }

	@Override
	public String toString() {
		return "TGdxxb [gddm=" + gddm + ", ztbh=" + ztbh + ", name=" + name + ", xwbh=" + xwbh + "]";
	}
    
}