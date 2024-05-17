package com.green.sang.service;

import java.util.List;

import com.green.sang.dto.ReplyReview;

public interface ReplyReviewService {
	List<ReplyReview> r_list(int r_bno);
	void r_insert(ReplyReview rr);
	void r_update(ReplyReview rr);
	void r_delete(ReplyReview rr);
}
