package com.green.sang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.green.sang.dto.Attend;
import com.green.sang.dto.Heartsave;
import com.green.sang.dto.Member;
import com.green.sang.dto.PagingBean;
import com.green.sang.dto.Tenaudit;
import com.green.sang.dto.Tentext;
import com.green.sang.dto.Write;
import com.green.sang.service.AttendService;
import com.green.sang.service.HeartsaveService;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;

@Controller
public class AttendController {
	@Autowired
	private AttendService ats;
	@Autowired
	private JavaMailSender jms;
	@Autowired
	private HeartsaveService hss;
	
	@GetMapping("/attend/gather")
	public void gather() {
	}
	@PostMapping("/gather_form")
    @ResponseBody
    public String gather_form(@RequestParam("name") String name, @RequestParam("email") String email,
            @RequestParam("phonenumber1") String phoneNumber1, @RequestParam("phonenumber2") String phoneNumber2,
            @RequestParam("phonenumber3") String phoneNumber3, @RequestParam("textarea") String textArea,
            @RequestParam("form_file") MultipartFile file) {
			
			MimeMessage mm = jms.createMimeMessage();
			try {
				MimeMessageHelper mmh = new MimeMessageHelper(mm, true, "utf-8");
				mmh.setSubject("강사모집");
				String formDataText = "이름: " + name + "\n" +
	                      "이메일 주소: " + email + "\n" +
	                      "연락처: " + phoneNumber1 + "-" + phoneNumber2 + "-" + phoneNumber3 + "\n" +
	                      "콘텐츠 내용: " + textArea;
				if (!file.isEmpty()) {
				    String fileName = file.getOriginalFilename();
				    formDataText += "\n첨부 파일: " + fileName;
				    mmh.addAttachment(fileName, file);
				}
				mmh.setText(formDataText);
				mmh.setTo("hanmeansuh@naver.com");
				mmh.setFrom("hanmeansuh@naver.com");
				jms.send(mm);
				return "redirect:/attend/gather";
			}catch (Exception e) {
				System.out.println(e.getMessage());
				return "forward:/attend/gather";
			}
		
    }
	@GetMapping("/attend/hobbypage")
	public void hobbypage(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
			@RequestParam(value = "keyword", defaultValue = "") String keyword,Attend attend, Model model,HttpSession session) {
		int rowPerPage = 5;
		if (pageNum == null || pageNum.equals(""))
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = ats.getTotal(keyword);
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Attend> list_attend = ats.list_attend();
		List<Write> list_write = ats.list_write(startRow, endRow, keyword);
		Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
		String id = null;
		
		if(idobj instanceof Long) { // id 데이터타입이 Long이라면
			Long idLong = (Long)idobj;
			id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
		}else if(idobj instanceof String) {
			id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
		}
		
		List<Heartsave> list_save = hss.list_save();
		
		model.addAttribute("id",id); // main에서 login,logout 버튼활성화에 사용
		model.addAttribute("pb", pb);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("list_attend", list_attend);
		model.addAttribute("list_write", list_write);
		model.addAttribute("list_save", list_save);
	}	
	@GetMapping("/attend/hobbypage_detail")
	public String hobbypage_detail(Attend attend, Model model, HttpSession session) {
		System.out.println("atno : " + attend.getAt_no());
		
		ats.increaseViewCount(attend.getAt_no());
		Attend attend2 = ats.select(attend.getAt_no());
	    Member member = ats.select_mb(attend.getAt_no());
	    
	    if(attend2 != null) {
			String nextAtTitle = "";
			String beforeAtTitle = "";
			Attend nextattend = ats.select(attend.getAt_no()+1);
			if(nextattend != null) { nextAtTitle = nextattend.getAt_title();}
			else nextAtTitle = "false";
			
			Attend beforeattend = ats.select(attend.getAt_no()-1);
			if(beforeattend != null) { beforeAtTitle = beforeattend.getAt_title();}
			else beforeAtTitle = "false";
			
		
			model.addAttribute("beforeTitle",beforeAtTitle);
			model.addAttribute("nextTitle",nextAtTitle);
			model.addAttribute("attend" , attend2);
		} else { }
	    
		Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
		String id = null;
		
		if(idobj instanceof Long) { // id 데이터타입이 Long이라면
			Long idLong = (Long)idobj;
			id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
		}else if(idobj instanceof String) {
			id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
		}
		System.out.println("id값 : " + id);
		
		Heartsave save = hss.findAt_noAndId(attend.getAt_no(), id);
		
		model.addAttribute("id",id); // main에서 login,logout 버튼활성화에 사용  
	    model.addAttribute("member", member);
	    model.addAttribute("save", save);
	    
		return "attend/hobbypage_detail";
	}
	// 좋아요
	@PostMapping("/attend/plus_heart")
	@ResponseBody
	public ResponseEntity<String> plus_heart(@RequestParam("at_no") int at_no, @RequestParam("id") String id) {
	   	Attend attend = ats.select(at_no);

	    if (attend != null) {  
	        ats.increaseGoodCount(at_no);
	        Heartsave at_save = new Heartsave();
	        at_save.setAt_no(at_no);
	        at_save.setId(id);
	        hss.save_atinsert(at_save);
	        return ResponseEntity.ok("Success");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@PostMapping("/attend/minus_heart")
	@ResponseBody
	public ResponseEntity<String> minus_heart(@RequestParam("at_no") int at_no, @RequestParam("id") String id) {
		Attend attend = ats.select(at_no);

	    if (attend != null) {  
	        ats.decreaseGoodCount(at_no);
	        Heartsave check_save = hss.findAt_noAndId(at_no,id);
	        hss.save_atdelete(check_save);
	        return ResponseEntity.ok("Success");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	//글쓰기 좋아요
	@PostMapping("/attend/w_plus_heart")
	@ResponseBody
	public ResponseEntity<String> w_plus_heart(@RequestParam("w_no") int w_no, @RequestParam("id") String id) {
	   	Write write = ats.select_w(w_no);

	    if (write != null) {  
	        ats.w_increaseGoodCount(w_no);
	        Heartsave w_save = new Heartsave();
	        w_save.setW_no(w_no);
	        w_save.setId(id);
	        hss.save_winsert(w_save);
	        return ResponseEntity.ok("Success");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@PostMapping("/attend/w_minus_heart")
	@ResponseBody
	public ResponseEntity<String> w_minus_heart(@RequestParam("w_no") int w_no, @RequestParam("id") String id) {
		Write write = ats.select_w(w_no);

	    if (write != null) {  
	        ats.w_decreaseGoodCount(w_no);
	        Heartsave check_wsave = hss.findW_noAndId(w_no,id);
	        hss.save_wdelete(check_wsave);
	        return ResponseEntity.ok("Success");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@GetMapping("/attend/tentext")
	public void tentext(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum, HttpSession session,
			Model model) {
		int rowPerPage = 20;
		if (pageNum == null || pageNum.equals(""))
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = ats.getTotal_tt();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Tentext> list_tentext = ats.list_tentext(startRow, endRow);

		// 아이디세션
		Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
		String id = null;

		if (idobj instanceof Long) { // id 데이터타입이 Long이라면
			Long idLong = (Long) idobj;
			id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
		} else if (idobj instanceof String) {
			id = (String) idobj; // id데이터타입이 String이라면 id에 그대로저장
		}
		System.out.println("id값 : " + id);

		model.addAttribute("id", id);

		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("list_tentext", list_tentext);
	}

	@GetMapping("/attend/tentext_detail")
	public String tentext_detail(Tentext tentext, Model model, HttpSession session) {		
		Tentext tentext2 = ats.select_t(tentext.getT_no());
		
		if(tentext2 != null) {
			String nextTtTitle = "";
			String beforeTtTitle = "";
			Tentext nexttext = ats.select_t(tentext.getT_no()+1);
			if(nexttext != null) {	nextTtTitle = nexttext.getT_title();}
			else nextTtTitle = "false";
			
			Tentext beforetext = ats.select_t(tentext.getT_no()-1);
			if(beforetext != null) { beforeTtTitle = beforetext.getT_title();}
			else beforeTtTitle = "false";
			
			Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
			String id = null;
			
			if(idobj instanceof Long) { // id 데이터타입이 Long이라면
				Long idLong = (Long)idobj;
				id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
			}else if(idobj instanceof String) {
				id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
			}
			System.out.println("id값 : " + id);		

			model.addAttribute("id",id); // main에서 login,logout 버튼활성화에 사용
		
			model.addAttribute("beforeTitle",beforeTtTitle);
			model.addAttribute("nextTitle",nextTtTitle);
			model.addAttribute("tentext" , tentext2);
		} else { }
		
		return "attend/tentext_detail";
	}
	// 열줄-심사평
	@GetMapping("/attend/tentext_audit")
	public void tentext_audit(HttpSession session, Model model) {
		List<Tenaudit> list_tenaudit = ats.list_tenaudit();
		
		Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
		String id = null;
		
		if(idobj instanceof Long) { // id 데이터타입이 Long이라면
			Long idLong = (Long)idobj;
			id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
		}else if(idobj instanceof String) {
			id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
		}
		System.out.println("id값 : " + id);		

		model.addAttribute("id",id); // main에서 login,logout 버튼활성화에 사용
		model.addAttribute("list_tenaudit",list_tenaudit);
	}
	
	@GetMapping("/attend/tentext_audit_detail")
	public String tentext_audit_detail(Tenaudit tenaudit, Model model, HttpSession session) {		
		Tenaudit tenaudit2 = ats.select_ta(tenaudit.getTa_no());
		
		if(tenaudit2 != null) {
			String nextTaTitle = "";
			String beforeTaTitle = "";
			Tenaudit nexttenaudit = ats.select_ta(tenaudit.getTa_no()+1);
			if(nexttenaudit != null) {	nextTaTitle = nexttenaudit.getTa_title();}
			else nextTaTitle = "false";
			
			Tenaudit beforetenaudit = ats.select_ta(tenaudit.getTa_no()-1);
			if(beforetenaudit != null) { beforeTaTitle = beforetenaudit.getTa_title();}
			else beforeTaTitle = "false";
			
		
			model.addAttribute("beforeTitle",beforeTaTitle);
			model.addAttribute("nextTitle",nextTaTitle);
			model.addAttribute("tenaudit" , tenaudit2);
		} else { }
		
		Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
		String id = null;
		
		if(idobj instanceof Long) { // id 데이터타입이 Long이라면
			Long idLong = (Long)idobj;
			id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
		}else if(idobj instanceof String) {
			id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
		}
		System.out.println("id값 : " + id);		

		model.addAttribute("id",id); // main에서 login,logout 버튼활성화에 사용
		return "attend/tentext_audit_detail";
	}
	
	// 참여-글쓰기
	@GetMapping("/attend/writing_detail")
	public String writing_detail(@RequestParam("w_no") int w_no ,Write write, Model model, HttpSession session) {		
		Write write2 = ats.select_w(write.getW_no());
		Member member = ats.select_mbw(write.getW_no());
		List<Attend> list_attend = ats.list_attend();
		
		if(write2 != null) {
			String nextWTitle = "";
			String beforeWTitle = "";
			Write nextwrite = ats.select_w(write.getW_no()+1);
			if(nextwrite != null) {	nextWTitle = nextwrite.getW_title();}
			else nextWTitle = "false";
			
			Write beforewrite = ats.select_w(write.getW_no()-1);
			if(beforewrite != null) { beforeWTitle = beforewrite.getW_title();}
			else beforeWTitle = "false";
			
		
			model.addAttribute("beforeTitle",beforeWTitle);
			model.addAttribute("nextTitle",nextWTitle);
			model.addAttribute("write" , write2);
		} else { }
		
		Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
		String id = null;
		
		if(idobj instanceof Long) { // id 데이터타입이 Long이라면
			Long idLong = (Long)idobj;
			id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
		}else if(idobj instanceof String) {
			id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
		}
		System.out.println("id값 : " + id);	
		
		Heartsave w_save = hss.findW_noAndId(w_no, id);

		model.addAttribute("id",id); // main에서 login,logout 버튼활성화에 사용
		model.addAttribute("list_attend", list_attend);
		model.addAttribute("member", member);
		model.addAttribute("w_save", w_save);
		
		return "attend/writing_detail";
	}
	//글쓰기 입력
	@GetMapping("/attend/writing")
	public void writing(HttpSession session, Model model) {		
		
		Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
		String id = null;
		
		if(idobj instanceof Long) { // id 데이터타입이 Long이라면
			Long idLong = (Long)idobj;
			id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
		}else if(idobj instanceof String) {
			id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
		}
		System.out.println("id값 : " + id);		
		
		Member member = ats.select_wm(id);
		
		model.addAttribute("id",id); // main에서 login,logout 버튼활성화에 사용
		model.addAttribute("member",member);
	}
	@PostMapping("/write")
    public String saveWrite(@RequestParam("w_title") String title, @RequestParam("w_content") String content,
    		HttpSession session) {
		
		Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
		String id = null;
		
		if(idobj instanceof Long) { // id 데이터타입이 Long이라면
			Long idLong = (Long)idobj;
			id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
		}else if(idobj instanceof String) {
			id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
		}
		System.out.println("id값 : " + id);		
		
        ats.saveWrite(title, content, id);
        return "redirect:/attend/hobbypage"; 
    }
	// 글쓰기 수정
	@GetMapping("/attend/writing_update")
	public void writing_update(@RequestParam("w_no") int w_no ,HttpSession session, Model model) {		
		
		Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
		String id = null;
		
		if(idobj instanceof Long) { // id 데이터타입이 Long이라면
			Long idLong = (Long)idobj;
			id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
		}else if(idobj instanceof String) {
			id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
		}
		System.out.println("id값 : " + id);		
		
		Write write = ats.select_w(w_no);
		Member member = ats.select_wm(id);
		
		model.addAttribute("id",id); // main에서 login,logout 버튼활성화에 사용
		model.addAttribute("member",member);
		model.addAttribute("write",write);
	}
	@PostMapping("/write_update")
    public String updateWrite(@RequestParam("w_title") String title, @RequestParam("w_content") String content,
    		@RequestParam("w_no") int w_no, HttpSession session) {
		
		Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
		String id = null;
		
		if(idobj instanceof Long) { // id 데이터타입이 Long이라면
			Long idLong = (Long)idobj;
			id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
		}else if(idobj instanceof String) {
			id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
		}
		System.out.println("id값 : " + id);		
		
        ats.updateWrite(title, content, w_no);
        return "redirect:/attend/hobbypage"; 
    }
	
	@PostMapping("/delete_page")
    public ResponseEntity<String> deletePage(@RequestParam("w_no") int w_no) {
		System.out.println("숫자열"+w_no);
		
        boolean deletionSuccess = ats.deletePage(w_no); 
        if (deletionSuccess) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failure");
        }
    }
}
