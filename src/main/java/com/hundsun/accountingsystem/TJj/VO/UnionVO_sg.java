package com.hundsun.accountingsystem.TJj.VO;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UnionVO_sg {
	private int code;
	 private String msg;
	 private int count;
	 private List<Union_sg> data;
}
