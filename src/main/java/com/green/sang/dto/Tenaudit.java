package com.green.sang.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("Tenaudit")
public class Tenaudit {
	private int ta_no; 			//심사평 번호
	private String ta_title;	//심사평 제목
	private String ta_image;	//심사평 이미지
	private String ta_content1;	//본문내용
	private String ta_content2;	//본문내용
	private String ta_content3;	//본문내용
	private String ta_content4;	//본문내용
	private String ta_content5;	//본문내용
	private String ta_content6;	//본문내용
	private String ta_content7;	//본문내용
	private String ta_content8;	//본문내용
}
