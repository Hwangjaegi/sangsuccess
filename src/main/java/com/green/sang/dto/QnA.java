package com.green.sang.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("QnA")
public class QnA {
	private int q_no;
	private String q_title;
	private String q_content;
	private Date q_date;
	private char q_del;
	private String id;

}
