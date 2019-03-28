package com.hundsun.accountingsystem.Global.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
//会计科目编号 会计科目父科目编号 会计科目名称 借贷方向 所属级别 是否为父科目
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class TKjkmb {
    private String id;

    private String parentId;

    private String name;

    private Integer lendingDirection;

    private Integer level;

    private Integer isParent;
}