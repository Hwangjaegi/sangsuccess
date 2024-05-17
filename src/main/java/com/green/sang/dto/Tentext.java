package com.green.sang.dto;

import java.sql.Date;
import org.apache.ibatis.type.Alias;
import lombok.Data;

@Data
@Alias("Tentext")
public class Tentext {
	private int t_no; 			// 열줄번호
	private String t_title;		// 열줄제목
	private String t_image;		// 열줄썸네일
	private String t_content;	// 열줄내용
	private Date t_date;		// 열줄등록일자
	private String id;
}
