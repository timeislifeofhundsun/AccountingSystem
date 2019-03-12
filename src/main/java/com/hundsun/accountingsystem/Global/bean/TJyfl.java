package com.hundsun.accountingsystem.Global.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @ClassName TJyfl
 * @Aurhor yangjf25257
 * @Date 2019/3/8 15:34
 * @Version 1.0
 * @Description 交易费率VO 其中的属性包括 1、业务代码（ywcode） 2、业务名字（ywname） 3、经手费（jfs） 4、印花（yh） 5、过户（gh） 6、证管（zg） 7、佣金（yj）
 **/
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class TJyfl {
    private Integer ywcode;

    private String ywname;

    private Double jsfl;

    private Double yh;

    private Double gh;

    private Double zg;

    private Double yj;

}