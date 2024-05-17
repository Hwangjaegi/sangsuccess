package com.green.sang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.sang.dto.ReplyNotice;
import com.green.sang.mapper.ReplyNoticeMapper;

@Service
public class ReplyNoticeServiceImpl implements ReplyNoticeService {
	@Autowired
	private ReplyNoticeMapper rnm;

//	댓글 목록
	public List<ReplyNotice> list(int nt_bno) {
		return rnm.list(nt_bno);
	}
//	댓글 입력
	public void insert(ReplyNotice rn) {
		rnm.insert(rn);
	}
//	댓글 수정
	public void update(ReplyNotice rn) {
		rnm.update(rn);
	}
//	댓글 삭제
	public void delete(ReplyNotice rn) {
		rnm.delete(rn);
	}
}
