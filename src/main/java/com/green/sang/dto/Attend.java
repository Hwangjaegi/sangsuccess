package com.green.sang.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("Attend")
public class Attend {
	private int at_no;			// 참여 번호
	private String at_title;	// 참여 제목
	private String at_image;	// 참여 사진
	private String at_editor; 	// 참여 편집자의 말
	private String at_content1; // 참여 내용1
	private String at_content2; // 참여 내용2
	private String at_content3; // 참여 내용3
	private String at_content4; // 참여 내용4
	private String at_content5; // 참여 내용5
	private String at_content6; // 참여 내용6
	private String at_content7; // 참여 내용7
	private String at_content8; // 참여 내용8
	private Date at_date;		// 등록 일자
	private int at_good_count; 	// 좋아요 수
	private String at_goodChk;	// 좋아요 구분
	private int at_view_count; 	// 조회수
	private int at_repl_count; 	// 댓글 수
	private String id;

}
