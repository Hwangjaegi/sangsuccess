package com.green.sang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.sang.dto.ReplyAttend;
import com.green.sang.dto.ReplyNotice;

@Mapper
public interface ReplyAttendMapper {
	
//	열줄공모전
//	댓글 목록
	List<ReplyAttend> list_t(int t_bno);
//	댓글 입력
	void insert_t(ReplyAttend rat);
//	댓글 수정
	void update_t(ReplyAttend rat);
//	댓글 삭제
	void delete_t(ReplyAttend rat);
	
//	취미진심
//	댓글 목록
	List<ReplyAttend> list_at(int at_bno);
// 	댓글 입력
	void insert_at(ReplyAttend rat);
//	댓글 수정
	void update_at(ReplyAttend rat);
//	댓글 삭제
	void delete_at(ReplyAttend rat);
	
// 참여-글쓰기
//	댓글 목록
	List<ReplyAttend> list_w(int w_bno);
// 	댓글 입력
	void insert_w(ReplyAttend rat);
//	댓글 수정
	void update_w(ReplyAttend rat);
//	댓글 삭제
	void delete_w(ReplyAttend rat);

//	열줄공모전-심사평
//	댓글 목록
	List<ReplyAttend> list_ta(int ta_bno);
//	댓글 입력
	void insert_ta(ReplyAttend rat);
//	댓글 수정
	void update_ta(ReplyAttend rat);
//	댓글 삭제
	void delete_ta(ReplyAttend rat);
	
}