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
public class TCcyeb implements java.io.Serializable {
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
    private java.util.Date fsrq;//发生日期
    private Double ljjx;//累计计息

}
