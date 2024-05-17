package com.green.sang.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("Buy")
public class Buy {
	private int b_no; 		// 구매 번호
	private Date b_date;	// 주문 날짜
	private String cancle; 	// 취소
	private int c_count;	// 장바구니 수량
	private int a_no;		// 강의 번호 (fk)
	private String id;

//구매이미지받아서 처리위해 추가 + 재기추가
	private String image;
}
