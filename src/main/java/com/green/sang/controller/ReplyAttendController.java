package com.green.sang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.sang.dto.Attend;
import com.green.sang.dto.Notice;
import com.green.sang.dto.ReplyAttend;
import com.green.sang.dto.ReplyNotice;
import com.green.sang.dto.Tenaudit;
import com.green.sang.dto.Tentext;
import com.green.sang.dto.Write;
import com.green.sang.service.AttendService;
import com.green.sang.service.NoticeService;
import com.green.sang.service.ReplyAttendService;
import com.green.sang.service.ReplyNoticeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReplyAttendController {
	@Autowired
	private AttendService ats;
	@Autowired
	private ReplyAttendService rats;
	
// 취미진심 댓글
//	댓글
	@GetMapping("/attend/at_replyList/at_bno/{at_bno}")
	public String at_replyList (@PathVariable("at_bno") int at_bno, Model model, HttpSession session) {
		Attend attend = ats.select(at_bno);
		List<ReplyAttend> at_rnList = rats.list_at(at_bno);
		ats.at_countup(at_bno);
		
		Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
		String id = null;
		
		if(idobj instanceof Long) { // id 데이터타입이 Long이라면
			Long idLong = (Long)idobj;
			id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
		}else if(idobj instanceof String) {
			id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
		}
		System.out.println("id값 : " + id);
		
		model.addAttribute("id", id);
		model.addAttribute("attend", attend);		
		model.addAttribute("at_rnList", at_rnList);
		return "/attend/at_replyList";
	}
	
//	댓글 입력
	@PostMapping("/attend/at_rInsert")
	public String at_rInsert(ReplyAttend rat, HttpSession session) {
		Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
		String id = null;
		
		if(idobj instanceof Long) { // id 데이터타입이 Long이라면
			Long idLong = (Long)idobj;
			id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
		}else if(idobj instanceof String) {
			id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
		}
		System.out.println("id값 : " + id);
		
		rat.setId(id);
		rats.insert_at(rat);
		return "redirect:/attend/at_replyList/at_bno/"+rat.getAt_bno();
	}
	
//	댓글 수정
	@PostMapping("/at_rUpdate")
	public String at_rUpdate(ReplyAttend rat) {
		rats.update_at(rat);
		return "redirect:/attend/at_replyList/at_bno/"+rat.getAt_bno();
	}
	
//	댓글 삭제
	@PostMapping("/at_rDelete")
	public String at_rDelete(ReplyAttend rat) {
		rats.delete_at(rat);
		return "redirect:/attend/at_replyList/at_bno/"+rat.getAt_bno();
	}

// 참여-글쓰기 댓글
//		댓글
		@GetMapping("/attend/w_replyList/w_bno/{w_bno}")
		public String w_replyList (@PathVariable("w_bno") int w_bno, Model model, HttpSession session) {
			Write write = ats.select_w(w_bno);
			List<ReplyAttend> w_rnList = rats.list_w(w_bno);
			ats.w_countup(w_bno);
			
			Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
			String id = null;
			
			if(idobj instanceof Long) { // id 데이터타입이 Long이라면
				Long idLong = (Long)idobj;
				id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
			}else if(idobj instanceof String) {
				id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
			}
			System.out.println("id값 : " + id);
			
			model.addAttribute("id", id);
			
			model.addAttribute("write", write);
			model.addAttribute("w_rnList", w_rnList);
			return "/attend/w_replyList";
		}
		
//		댓글 입력
		@PostMapping("/attend/w_rInsert")
		public String w_rInsert(ReplyAttend rat, HttpSession session) {
			Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
			String id = null;
			
			if(idobj instanceof Long) { // id 데이터타입이 Long이라면
				Long idLong = (Long)idobj;
				id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
			}else if(idobj instanceof String) {
				id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
			}
			System.out.println("id값 : " + id);
			
			rat.setId(id);
			rats.insert_w(rat);
			return "redirect:/attend/w_replyList/w_bno/"+rat.getW_bno();
		}
		
//		댓글 수정
		@PostMapping("/w_rUpdate")
		public String w_rUpdate(ReplyAttend rat) {
			rats.update_w(rat);
			return "redirect:/attend/w_replyList/w_bno/"+rat.getW_bno();
		}
		
//		댓글 삭제
		@PostMapping("/w_rDelete")
		public String w_rDelete(ReplyAttend rat) {
			rats.delete_w(rat);
			return "redirect:/attend/w_replyList/w_bno/"+rat.getW_bno();
		}

		// 참여-열줄 댓글
//		댓글
		@GetMapping("/attend/t_replyList/t_bno/{t_bno}")
		public String t_replyList (@PathVariable("t_bno") int t_bno, Model model, HttpSession session) {
			Tentext tentext = ats.select_t(t_bno);
			List<ReplyAttend> t_rnList = rats.list_t(t_bno);
			
			Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
			String id = null;
			
			if(idobj instanceof Long) { // id 데이터타입이 Long이라면
				Long idLong = (Long)idobj;
				id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
			}else if(idobj instanceof String) {
				id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
			}
			System.out.println("id값 : " + id);
			
			model.addAttribute("id", id);
			model.addAttribute("tentext", tentext);
			model.addAttribute("t_rnList", t_rnList);
			return "/attend/t_replyList";
		}
		
//		댓글 입력
		@PostMapping("/attend/t_rInsert")
		public String t_rInsert(ReplyAttend rat, HttpSession session) {
			Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
			String id = null;
			
			if(idobj instanceof Long) { // id 데이터타입이 Long이라면
				Long idLong = (Long)idobj;
				id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
			}else if(idobj instanceof String) {
				id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
			}
			System.out.println("id값 : " + id);
			
			rat.setId(id);
			rats.insert_t(rat);
			return "redirect:/attend/t_replyList/t_bno/"+rat.getT_bno();
		}
		
//		댓글 수정
		@PostMapping("/t_rUpdate")
		public String t_rUpdate(ReplyAttend rat) {
			rats.update_t(rat);
			return "redirect:/attend/t_replyList/t_bno/"+rat.getT_bno();
		}
		
//		댓글 삭제
		@PostMapping("/t_rDelete")
		public String t_rDelete(ReplyAttend rat) {
			rats.delete_t(rat);
			return "redirect:/attend/t_replyList/t_bno/"+rat.getT_bno();
		}
		
		// 참여-열줄심사평 댓글
//		댓글
		@GetMapping("/attend/ta_replyList/ta_bno/{ta_bno}")
		public String ta_replyList (@PathVariable("ta_bno") int ta_bno, Model model, HttpSession session) {
			Tenaudit tenaudit = ats.select_ta(ta_bno);
			List<ReplyAttend> ta_rnList = rats.list_ta(ta_bno);
			
			Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
			String id = null;
			
			if(idobj instanceof Long) { // id 데이터타입이 Long이라면
				Long idLong = (Long)idobj;
				id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
			}else if(idobj instanceof String) {
				id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
			}
			System.out.println("id값 : " + id);
			
			model.addAttribute("id", id);
			model.addAttribute("tenaudit", tenaudit);
			model.addAttribute("ta_rnList", ta_rnList);
			return "/attend/ta_replyList";
		}
		
//		댓글 입력
		@PostMapping("/attend/ta_rInsert")
		public String ta_rInsert(ReplyAttend rat, HttpSession session) {
			Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
			String id = null;
			
			if(idobj instanceof Long) { // id 데이터타입이 Long이라면
				Long idLong = (Long)idobj;
				id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
			}else if(idobj instanceof String) {
				id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
			}
			System.out.println("id값 : " + id);
			
			rat.setId(id);
			rats.insert_ta(rat);
			return "redirect:/attend/ta_replyList/ta_bno/"+rat.getTa_bno();
		}
		
//		댓글 수정
		@PostMapping("/ta_rUpdate")
		public String ta_rUpdate(ReplyAttend rat) {
			rats.update_ta(rat);
			return "redirect:/attend/ta_replyList/ta_bno/"+rat.getTa_bno();
		}
		
//		댓글 삭제
		@PostMapping("/ta_rDelete")
		public String ta_rDelete(ReplyAttend rat) {
			rats.delete_ta(rat);
			return "redirect:/attend/ta_replyList/ta_bno/"+rat.getTa_bno();
		}
}