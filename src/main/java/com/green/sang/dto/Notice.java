package com.green.sang.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("Notice")
public class Notice {
	private int nt_no;			// 공지 번호
	private String nt_title; 	// 공지 제목
	private String nt_image;	// 공지 사진
	private String nt_content;	// 공지 내용
	private Date nt_date;		// 등록 일자
	private String id;
	private int ct_no;
	
	private MultipartFile file;
}
