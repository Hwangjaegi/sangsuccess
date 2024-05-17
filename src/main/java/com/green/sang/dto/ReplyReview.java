package com.green.sang.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("replyr")
public class ReplyReview {
	private int r_rno;
	private int r_bno;
	private String r_replytext;
	private String r_replier;
	private Date r_regdate;
	private Date r_updatedate;
	private String r_del;
}