package com.hundsun.accountingsystem.Global.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
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
    
}
