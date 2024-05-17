package com.green.sang.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("Cart")
public class Cart {
	private int c_no;	// 장바구니 번호
	private int c_count;	// 장바구니 수량
	private String id; // 아이디 (fk)
	private int a_no;
}
