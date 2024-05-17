package com.green.sang.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("ReplyAttend")
public class ReplyAttend {
	//참여 댓글
		private int at_rno;
		private int at_bno;
		private String at_replytext;
		private String at_replier;
		private Date at_regdate;
		private Date at_updatedate;
		private String id;

	// 글쓰기 댓글
		private int w_rno;
		private int w_bno;
		private String w_replytext;
		private String w_replier;
		private Date w_regdate;
		private Date w_updatedate;


	// 열줄 댓글
		private int t_rno;
		private int t_bno;
		private String t_replytext;
		private String t_replier;
		private Date t_regdate;
		private Date t_updatedate;
		
	// 열줄-심사평 댓글
		private int ta_rno;
		private int ta_bno;
		private String ta_replytext;
		private String ta_replier;
		private Date ta_regdate;
		private Date ta_updatedate;

}
