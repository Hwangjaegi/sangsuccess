package com.green.sang.service;

import java.util.List;

import com.green.sang.dto.ReplyNotice;

public interface ReplyNoticeService {
//	댓글 목록
	List<ReplyNotice> list(int nt_bno);
//	댓글 입력
	void insert(ReplyNotice rn);
//	댓글 수정
	void update(ReplyNotice rn);
//	댓글 삭제
	void delete(ReplyNotice rn);
}
