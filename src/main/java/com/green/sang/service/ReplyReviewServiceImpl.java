package com.green.sang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.sang.dto.ReplyReview;
import com.green.sang.mapper.ReplyReviewMapper;

@Service
public class ReplyReviewServiceImpl implements ReplyReviewService {
	@Autowired
	private ReplyReviewMapper rrm;
	public List<ReplyReview> r_list(int r_bno) {
		return rrm.r_list(r_bno);
	}
	public void r_insert(ReplyReview rr) {
		rrm.r_insert(rr);
	}

	public void r_update(ReplyReview rr) {
		rrm.r_update(rr);
	}

	public void r_delete(ReplyReview rr) {
		rrm.r_delete(rr);
	}


}
