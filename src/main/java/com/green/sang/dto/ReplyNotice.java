package com.green.sang.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("replyn")
public class ReplyNotice {
	private int nt_rno;
	private int nt_bno;
	private String nt_replytext;
	private String nt_replier;
	private Date nt_regdate;
	private Date nt_updatedate;
	private String nt_del;

}