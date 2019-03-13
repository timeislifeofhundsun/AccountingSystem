package com.hundsun.accountingsystem.Global.bean;

public class TXwxxb {
    private String xwbh;

    private String xwName;

    private String qsbh;

    private String qsName;

    public String getXwbh() {
        return xwbh;
    }

    public void setXwbh(String xwbh) {
        this.xwbh = xwbh == null ? null : xwbh.trim();
    }

    public String getXwName() {
        return xwName;
    }

    public void setXwName(String xwName) {
        this.xwName = xwName == null ? null : xwName.trim();
    }

    public String getQsbh() {
        return qsbh;
    }

    public void setQsbh(String qsbh) {
        this.qsbh = qsbh == null ? null : qsbh.trim();
    }

    public String getQsName() {
        return qsName;
    }

    public void setQsName(String qsName) {
        this.qsName = qsName == null ? null : qsName.trim();
    }

	@Override
	public String toString() {
		return "TXwxxb [xwbh=" + xwbh + ", xwName=" + xwName + ", qsbh=" + qsbh + ", qsName=" + qsName + "]";
	}
    
}