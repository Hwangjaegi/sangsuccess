package com.green.sang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.sang.dto.Notice;
import com.green.sang.dto.ReplyNotice;
import com.green.sang.service.NoticeService;
import com.green.sang.service.ReplyNoticeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReplyNoticeController {
	@Autowired
	private NoticeService ns;
	@Autowired
	private ReplyNoticeService rns;
	
//	댓글
	@GetMapping("/notice/nt_replyList/nt_bno/{nt_bno}")
	public String nt_replyList (@PathVariable("nt_bno") int nt_bno,
			HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		Notice notice = ns.select_d(nt_bno);
		List<ReplyNotice> rnList = rns.list(nt_bno);
		
		model.addAttribute("id", id);
		model.addAttribute("notice", notice);
		model.addAttribute("rnList", rnList);
		
		return "/notice/nt_replyList";
	}
	
//	댓글 입력
	@PostMapping("/notice/nt_rInsert")
	public String nt_rInsert(@RequestParam(value = "id", required = false) String id,
			ReplyNotice rn, HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		if (id != null) {
			rn.setNt_replier(id);
		}
		rns.insert(rn);

		return "redirect:/notice/nt_replyList/nt_bno/"+rn.getNt_bno();
	}
	
//	댓글 수정
	@PostMapping("/rUpdate")
	public String rUpdate(@RequestParam(value = "id", required = false) String id, 
			ReplyNotice rn, HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		if (id != null) {
			rn.setNt_replier(id);
		}
		rns.update(rn);

		return "redirect:/notice/nt_replyList/nt_bno/"+rn.getNt_bno();
	}
	
//	댓글 삭제
	@PostMapping("/rDelete")
	public String rDelete(ReplyNotice rn) {
		rns.delete(rn);
		return "redirect:/notice/nt_replyList/nt_bno/"+rn.getNt_bno();
	}
}