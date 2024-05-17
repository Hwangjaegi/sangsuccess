package com.green.sang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.sang.dto.ReplyReview;


@Mapper
public interface ReplyReviewMapper {
	List<ReplyReview> r_list(int r_bno);
	void r_insert(ReplyReview rr);
	void r_update(ReplyReview rr);
	void r_delete(ReplyReview rr);
}