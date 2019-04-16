package com.hundsun.accountingsystem.TJj.VO;

import com.alibaba.fastjson.annotation.JSONField;

/*
 * 该类暂时没有用
 * */
public class Union_sh {
	private static final long serialVersionUID = 1L;
    private Integer id;//主键
    private Integer cysl;//持有数量
    private Double ljgz;//累计估增
    private String extendc;//
    private String extendb;//扩展字段2
    private String extenda;//扩展字段1(股票存1)
    private Integer zqnm;//证券内码
    private Double zqcb;//证券成本
    private String kjkmdm;//会计科目代码
    private String zqdm;//证券代码
    private Integer ztbh;//账套编号
    private String ztbhname;
    @JSONField(format = "yyyy-MM-dd")
    private java.util.Date fsrq;//发生日期
    private Double ljjx;//累计计息
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCysl() {
		return cysl;
	}
	public void setCysl(Integer cysl) {
		this.cysl = cysl;
	}
	public Double getLjgz() {
		return ljgz;
	}
	public void setLjgz(Double ljgz) {
		this.ljgz = ljgz;
	}
	public String getExtendc() {
		return extendc;
	}
	public void setExtendc(String extendc) {
		this.extendc = extendc;
	}
	public String getExtendb() {
		return extendb;
	}
	public void setExtendb(String extendb) {
		this.extendb = extendb;
	}
	public String getExtenda() {
		return extenda;
	}
	public void setExtenda(String extenda) {
		this.extenda = extenda;
	}
	public Integer getZqnm() {
		return zqnm;
	}
	public void setZqnm(Integer zqnm) {
		this.zqnm = zqnm;
	}
	public Double getZqcb() {
		return zqcb;
	}
	public void setZqcb(Double zqcb) {
		this.zqcb = zqcb;
	}
	public String getKjkmdm() {
		return kjkmdm;
	}
	public void setKjkmdm(String kjkmdm) {
		this.kjkmdm = kjkmdm;
	}
	public String getZqdm() {
		return zqdm;
	}
	public void setZqdm(String zqdm) {
		this.zqdm = zqdm;
	}
	public Integer getZtbh() {
		return ztbh;
	}
	public void setZtbh(Integer ztbh) {
		this.ztbh = ztbh;
	}
	public java.util.Date getFsrq() {
		return fsrq;
	}
	public void setFsrq(java.util.Date fsrq) {
		this.fsrq = fsrq;
	}
	public Double getLjjx() {
		return ljjx;
	}
	public void setLjjx(Double ljjx) {
		this.ljjx = ljjx;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getZtbhname() {
		return ztbhname;
	}
	public void setZtbhname(String ztbhname) {
		this.ztbhname = ztbhname;
	}
	@Override
	public String toString() {
		return "Union_sh [id=" + id + ", cysl=" + cysl + ", ljgz=" + ljgz + ", extendc=" + extendc + ", extendb="
				+ extendb + ", extenda=" + extenda + ", zqnm=" + zqnm + ", zqcb=" + zqcb + ", kjkmdm=" + kjkmdm
				+ ", zqdm=" + zqdm + ", ztbh=" + ztbh + ", ztbhname=" + ztbhname + ", fsrq=" + fsrq + ", ljjx=" + ljjx
				+ ", getId()=" + getId() + ", getCysl()=" + getCysl() + ", getLjgz()=" + getLjgz() + ", getExtendc()="
				+ getExtendc() + ", getExtendb()=" + getExtendb() + ", getExtenda()=" + getExtenda() + ", getZqnm()="
				+ getZqnm() + ", getZqcb()=" + getZqcb() + ", getKjkmdm()=" + getKjkmdm() + ", getZqdm()=" + getZqdm()
				+ ", getZtbh()=" + getZtbh() + ", getFsrq()=" + getFsrq() + ", getLjjx()=" + getLjjx()
				+ ", getZtbhname()=" + getZtbhname() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
