/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/25  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.VO;

import com.hundsun.accountingsystem.Global.bean.TQsb;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 * 银行质押式回购传输对象
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TYzyshgVO {
  private int code;
  private String msg;
  private int count;
  private List<TQsb> data;
}