package com.green.sang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.sang.dto.ReplyReview;
import com.green.sang.dto.Review;
import com.green.sang.service.ReplyReviewService;
import com.green.sang.service.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReplyReviewController {
	@Autowired
	private ReviewService rs;
	@Autowired
	private ReplyReviewService rrs;
	
//	댓글
	@GetMapping("/review/r_replyList/r_bno/{r_bno}")
	public String r_replyList (@PathVariable("r_bno") int r_bno,
			HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		Review review = rs.select_d(r_bno);
		List<ReplyReview> r_list = rrs.r_list(r_bno);
		
		model.addAttribute("id", id);
		model.addAttribute("review", review);
		model.addAttribute("r_list", r_list);
		return "/review/r_replyList";
	}
	
//	댓글 입력
	@PostMapping("/review/r_rInsert")
	public String r_rInsert(@RequestParam(value = "id", required = false) String id,
			ReplyReview rr, HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		if (id != null) {
			rr.setR_replier(id);
		}
		rrs.r_insert(rr);
		
		return "redirect:/review/r_replyList/r_bno/"+rr.getR_bno();
	}
	
//	댓글 수정
	@PostMapping("/r_rUpdate")
	public String rUpdate(@RequestParam(value = "id", required = false) String id,
			ReplyReview rr, HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		if (id != null) {
			rr.setR_replier(id);
		}
		rrs.r_update(rr);
		
		return "redirect:/review/r_replyList/r_bno/"+rr.getR_bno();
	}
	
//	댓글 삭제
	@PostMapping("/r_rDelete")
	public String rDelete(ReplyReview rr) {
		rrs.r_delete(rr);
		return "redirect:/review/r_replyList/r_bno/"+rr.getR_bno();
	}
}