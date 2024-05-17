package com.green.sang.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("Staff")
public class Staff {
	private int st_no; 			// 강사소개 번호
	private String st_title;	// 강사소개 제목
	private String st_image;		// 강사소개 사진
	private String st_content; 	// 강사소개 내용
	private String st_name;		// 강사소개 이름
	private String st_type; 	// 강사소개 타입 강의
	private Date st_date;
	private String id;
}
