package com.hundsun.accountingsystem.Global.bean;
public class TPzb implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;//编号
    private String extendf;//备用
    private String extendc;//备用
    private String extendb;//备用
    private String extende;//备用
    private String extendd;//备用
    private String extenda;//备用
    private String bs;//借贷方向
    private Integer pzid;//凭证内id
    private String kjkm;//会计科目
    private Integer ztbh;//账套编号
    private String kmms;//科目描述
    private Double je;//金额
    private String zy;//摘要
    private java.util.Date rq;//日期
    public TPzb() {
        super();
    }
    public TPzb(Integer id,String extendf,String extendc,String extendb,String extende,String extendd,String extenda,String bs,Integer pzid,String kjkm,Integer ztbh,String kmms,Double je,String zy,java.util.Date rq) {
        super();
        this.id = id;
        this.extendf = extendf;
        this.extendc = extendc;
        this.extendb = extendb;
        this.extende = extende;
        this.extendd = extendd;
        this.extenda = extenda;
        this.bs = bs;
        this.pzid = pzid;
        this.kjkm = kjkm;
        this.ztbh = ztbh;
        this.kmms = kmms;
        this.je = je;
        this.zy = zy;
        this.rq = rq;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExtendf() {
        return this.extendf;
    }

    public void setExtendf(String extendf) {
        this.extendf = extendf;
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

    public String getExtende() {
        return this.extende;
    }

    public void setExtende(String extende) {
        this.extende = extende;
    }

    public String getExtendd() {
        return this.extendd;
    }

    public void setExtendd(String extendd) {
        this.extendd = extendd;
    }

    public String getExtenda() {
        return this.extenda;
    }

    public void setExtenda(String extenda) {
        this.extenda = extenda;
    }

    public String getBs() {
        return this.bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public Integer getPzid() {
        return this.pzid;
    }

    public void setPzid(Integer pzid) {
        this.pzid = pzid;
    }

    public String getKjkm() {
        return this.kjkm;
    }

    public void setKjkm(String kjkm) {
        this.kjkm = kjkm;
    }

    public Integer getZtbh() {
        return this.ztbh;
    }

    public void setZtbh(Integer ztbh) {
        this.ztbh = ztbh;
    }

    public String getKmms() {
        return this.kmms;
    }

    public void setKmms(String kmms) {
        this.kmms = kmms;
    }

    public Double getJe() {
        return this.je;
    }

    public void setJe(Double je) {
        this.je = je;
    }

    public String getZy() {
        return this.zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public java.util.Date getRq() {
        return this.rq;
    }

    public void setRq(java.util.Date rq) {
        this.rq = rq;
    }
	@Override
	public String toString() {
		return "TPzb [id=" + id + ", extenda=" + extenda + ", bs=" + bs + ", pzid=" + pzid + ", ztbh=" + ztbh
				+ ", kmms=" + kmms + ", je=" + je + ", zy=" + zy + ", rq=" + rq + "]";
	}

    
    
}
