package com.hundsun.accountingsystem.Global.bean;
public class TCcyeb implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;//主键
    private Integer cysl;//持有数量
    private Double ljgz;//累计估增
    private String extendc;//扩展字段3
    private String extendb;//扩展字段2
    private String extenda;//扩展字段1(股票存1)
    private Integer zqnm;//证券内码
    private Double zqcb;//证券成本
    private String kjkmdm;//会计科目代码
    private String zqdm;//证券代码
    private Integer ztbh;//账套编号
    private java.util.Date fsrq;//发生日期
    private Double ljjx;//累计计息
    public TCcyeb() {
        super();
    }
    public TCcyeb(Integer id,Integer cysl,Double ljgz,String extendc,String extendb,String extenda,Integer zqnm,Double zqcb,String kjkmdm,String zqdm,Integer ztbh,java.util.Date fsrq,Double ljjx) {
        super();
        this.id = id;
        this.cysl = cysl;
        this.ljgz = ljgz;
        this.extendc = extendc;
        this.extendb = extendb;
        this.extenda = extenda;
        this.zqnm = zqnm;
        this.zqcb = zqcb;
        this.kjkmdm = kjkmdm;
        this.zqdm = zqdm;
        this.ztbh = ztbh;
        this.fsrq = fsrq;
        this.ljjx = ljjx;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCysl() {
        return this.cysl;
    }

    public void setCysl(Integer cysl) {
        this.cysl = cysl;
    }

    public Double getLjgz() {
        return this.ljgz;
    }

    public void setLjgz(Double ljgz) {
        this.ljgz = ljgz;
    }

    public String getExtendc() {
        return this.extendc;
    }

    public void setExtendc(String extendc) {
        this.extendc = extendc;
    }

    public String getExtendb() {
        return this.extendb;
    }

    public void setExtendb(String extendb) {
        this.extendb = extendb;
    }

    public String getExtenda() {
        return this.extenda;
    }

    public void setExtenda(String extenda) {
        this.extenda = extenda;
    }

    public Integer getZqnm() {
        return this.zqnm;
    }

    public void setZqnm(Integer zqnm) {
        this.zqnm = zqnm;
    }

    public Double getZqcb() {
        return this.zqcb;
    }

    public void setZqcb(Double zqcb) {
        this.zqcb = zqcb;
    }

    public String getKjkmdm() {
        return this.kjkmdm;
    }

    public void setKjkmdm(String kjkmdm) {
        this.kjkmdm = kjkmdm;
    }

    public String getZqdm() {
        return this.zqdm;
    }

    public void setZqdm(String zqdm) {
        this.zqdm = zqdm;
    }

    public Integer getZtbh() {
        return this.ztbh;
    }

    public void setZtbh(Integer ztbh) {
        this.ztbh = ztbh;
    }

    public java.util.Date getFsrq() {
        return this.fsrq;
    }

    public void setFsrq(java.util.Date fsrq) {
        this.fsrq = fsrq;
    }

    public Double getLjjx() {
        return this.ljjx;
    }

    public void setLjjx(Double ljjx) {
        this.ljjx = ljjx;
    }
	@Override
	public String toString() {
		return "TCcyeb [id=" + id + ", cysl=" + cysl + ", ljgz=" + ljgz + ", extendc=" + extendc + ", extendb="
				+ extendb + ", extenda=" + extenda + ", zqnm=" + zqnm + ", zqcb=" + zqcb + ", kjkmdm=" + kjkmdm
				+ ", zqdm=" + zqdm + ", ztbh=" + ztbh + ", fsrq=" + fsrq + ", ljjx=" + ljjx + "]";
	}
    
}
