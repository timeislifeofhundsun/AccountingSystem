package com.hundsun.accountingsystem.Global.bean;
public class THqb implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;//主键
    private String zqmc;//证券名称
    private Double jrsp;//今日收盘
    private java.util.Date hqrq;//行情日期
    private String zqdm;//证券代码
    private Double zrspj;//昨日收盘价
    private Double jrkp;//今日开盘
    private Double cjje;//成交金额
    private Integer cjsl;//成交数量
    private Integer zqnm;//证券内码
    public THqb() {
        super();
    }
    public THqb(Integer id,String zqmc,Double jrsp,java.util.Date hqrq,String zqdm,Double zrspj,Double jrkp,Double cjje,Integer cjsl,Integer zqnm) {
        super();
        this.id = id;
        this.zqmc = zqmc;
        this.jrsp = jrsp;
        this.hqrq = hqrq;
        this.zqdm = zqdm;
        this.zrspj = zrspj;
        this.jrkp = jrkp;
        this.cjje = cjje;
        this.cjsl = cjsl;
        this.zqnm = zqnm;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZqmc() {
        return this.zqmc;
    }

    public void setZqmc(String zqmc) {
        this.zqmc = zqmc;
    }

    public Double getJrsp() {
        return this.jrsp;
    }

    public void setJrsp(Double jrsp) {
        this.jrsp = jrsp;
    }

    public java.util.Date getHqrq() {
        return this.hqrq;
    }

    public void setHqrq(java.util.Date hqrq) {
        this.hqrq = hqrq;
    }

    public String getZqdm() {
        return this.zqdm;
    }

    public void setZqdm(String zqdm) {
        this.zqdm = zqdm;
    }

    public Double getZrspj() {
        return this.zrspj;
    }

    public void setZrspj(Double zrspj) {
        this.zrspj = zrspj;
    }

    public Double getJrkp() {
        return this.jrkp;
    }

    public void setJrkp(Double jrkp) {
        this.jrkp = jrkp;
    }

    public Double getCjje() {
        return this.cjje;
    }

    public void setCjje(Double cjje) {
        this.cjje = cjje;
    }

    public Integer getCjsl() {
        return this.cjsl;
    }

    public void setCjsl(Integer cjsl) {
        this.cjsl = cjsl;
    }

    public Integer getZqnm() {
        return this.zqnm;
    }

    public void setZqnm(Integer zqnm) {
        this.zqnm = zqnm;
    }

}
