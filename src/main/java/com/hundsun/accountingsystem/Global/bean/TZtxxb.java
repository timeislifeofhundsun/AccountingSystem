package com.hundsun.accountingsystem.Global.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TZtxxb {
    private Integer ztbh;

    private String name;
    
    private Date createdate;

    private Date enddate;

    private Integer creater;

    private Integer jjdm;

    private Double money;

    private Integer number;

    public Integer getZtbh() {
        return ztbh;
    }

    @Override
	public String toString() {
		return "TZtxxb [ztbh=" + ztbh + ", name=" + name + ", createdate=" + createdate + ", enddate=" + enddate
				+ ", creater=" + creater + ", jjdm=" + jjdm + ", money=" + money + ", number=" + number + "]";
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
    @JSONField(format = "yyyy-MM-dd")
    public Date getCreatedate() {
        return createdate;
    }
    
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    @JSONField(format = "yyyy-MM-dd")
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Integer getJjdm() {
        return jjdm;
    }

    public void setJjdm(Integer jjdm) {
        this.jjdm = jjdm;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}