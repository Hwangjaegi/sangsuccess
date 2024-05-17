package com.green.sang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.sang.dto.ReplyNotice;

@Mapper
public interface ReplyNoticeMapper {
//	댓글 목록
	List<ReplyNotice> list(int nt_bno);
//	댓글 입력
	void insert(ReplyNotice rn);
//	댓글 수정
	void update(ReplyNotice rn);
//	댓글 삭제
	void delete(ReplyNotice rn);
}