package com.hundsun.accountingsystem.Global.VO;

import java.util.List;

import com.hundsun.accountingsystem.Global.bean.SysUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TUserVO {
	private int code;
	 private String msg;
	 private int count;
	 private List<SysUser> data;
}
