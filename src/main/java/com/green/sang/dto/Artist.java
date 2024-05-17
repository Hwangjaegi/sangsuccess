package com.green.sang.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("Artist")
public class Artist {
	private int ar_no;			// 소식(아티스트) 번호
	private String ar_title;	//소식(아티스트) 제목
	private String ar_image;	// 소식(아티스트) 사진
	private String ar_content; 	// 소식(아티스트) 내용
	private Date ar_date;		// 등록 일자
	private String id;
	private int ct_no;
	private String ar_detail;
	private String ar_detail2;
}
