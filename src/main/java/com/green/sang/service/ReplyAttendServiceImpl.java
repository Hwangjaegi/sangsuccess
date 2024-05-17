package com.green.sang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.sang.dto.ReplyAttend;
import com.green.sang.dto.ReplyNotice;
import com.green.sang.mapper.ReplyAttendMapper;
import com.green.sang.mapper.ReplyNoticeMapper;

@Service
public class ReplyAttendServiceImpl implements ReplyAttendService {
	@Autowired
	private ReplyAttendMapper ratm;
// 열줄공모전
//	댓글 목록
	@Override
	public List<ReplyAttend> list_t(int t_bno) {
		return ratm.list_t(t_bno);
	}
//	댓글 입력
	public void insert_t(ReplyAttend rat) {
		ratm.insert_t(rat);
	}
//	댓글 수정
	public void update_t(ReplyAttend rat) {
		ratm.update_t(rat);
	}
//	댓글 삭제
	public void delete_t(ReplyAttend rat) {
		ratm.delete_t(rat);
	}
	
// 취미진심
//	댓글 목록
	@Override
	public List<ReplyAttend> list_at(int at_bno) {
		return ratm.list_at(at_bno);
	}
//	댓글 입력
	@Override
	public void insert_at(ReplyAttend rat) {
		System.out.println("rs id : "+rat.getId());
		ratm.insert_at(rat);		
	}
//	댓글 수정
	@Override
	public void update_at(ReplyAttend rat) {
		ratm.update_at(rat);		
	}
//	댓글 삭제
	@Override
	public void delete_at(ReplyAttend rat) {
		ratm.delete_at(rat);		
	}
	
// 참여-글쓰기
//	댓글 목록
	@Override
	public List<ReplyAttend> list_w(int w_bno) {
		return ratm.list_w(w_bno);
	}
//	댓글 입력
	@Override
	public void insert_w(ReplyAttend rat) {
		ratm.insert_w(rat);		
	}
//	댓글 수정
	@Override
	public void update_w(ReplyAttend rat) {
		ratm.update_w(rat);		
	}
//	댓글 삭제
	@Override
	public void delete_w(ReplyAttend rat) {
		ratm.delete_w(rat);		
	}

// 열줄공모전-심사평
//		댓글 목록
		@Override
		public List<ReplyAttend> list_ta(int ta_bno) {
			return ratm.list_ta(ta_bno);
		}
//		댓글 입력
		public void insert_ta(ReplyAttend rat) {
			ratm.insert_ta(rat);
		}
//		댓글 수정
		public void update_ta(ReplyAttend rat) {
			ratm.update_ta(rat);
		}
//		댓글 삭제
		public void delete_ta(ReplyAttend rat) {
			ratm.delete_ta(rat);
		}
}
