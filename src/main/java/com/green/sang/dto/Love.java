package com.green.sang.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("Love")
public class Love {
	private int l_no;	// 찜 번호
	private String id;
	private int a_no;
}
