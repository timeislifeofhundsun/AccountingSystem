/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/8  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.bean;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

/**
 * 功能说明:
 *
 * @author wanggk25832
 * 会计科目表Bean
 * 属性名：会计科目编号  科目父节点编号 会计科目名称 借贷方向 所属等级 是否是父节点
 */
@Data
@Mapper
public class TKjkm {
  private String id;
  private String parent_id;
  private String name;
  private Integer lending_direction;
  private Integer level;
  private Integer is_parent;
}
