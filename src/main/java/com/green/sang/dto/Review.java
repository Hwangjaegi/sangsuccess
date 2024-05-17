package com.green.sang.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("Review")
public class Review {
	private int r_no;			// 리뷰 번호
	private String r_title; 	// 리뷰 제목
	private String r_image;		// 리뷰 사진
	private String r_content;	// 리뷰 내용
	private Date r_date;		// 등록 일자
	private String id;
	private int ct_no;
	
	private MultipartFile file;
}
