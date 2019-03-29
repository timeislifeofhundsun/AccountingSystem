package com.hundsun.accountingsystem.Global.VO;

import java.util.List;

import com.hundsun.accountingsystem.Global.bean.TCcyeb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TCcyebVO {
	private int code;
	 private String msg;
	 private int count;
	 private List<TCcyeb> data;
}
