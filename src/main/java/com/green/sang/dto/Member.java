package com.green.sang.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("member")
public class Member {
	private String id;  
	private String password; 
	private String name;
	private String tel;
	private String email;
	private String image;	// 1.[재기] image 추가
	private Date reg_date;
	private String del;
	private String snt;		// 수강생/강사 구분
	private String admin;	// 관리자	
	
	private MultipartFile file;
}
