package com.green.sang.controller;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.sang.dto.Academy;
import com.green.sang.dto.Artist;
import com.green.sang.dto.Attend;
import com.green.sang.dto.Cart;
import com.green.sang.dto.Email;
import com.green.sang.dto.Love;
import com.green.sang.dto.Notice;
import com.green.sang.service.AcademyService;
import com.green.sang.service.ArtistService;
import com.green.sang.service.AttendService;
import com.green.sang.service.LoveService;
import com.green.sang.service.NoticeService;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;

@Controller
public class AcademyController {
	@Autowired
	private AcademyService as;
	@Autowired
	private ArtistService ars;
	@Autowired
	private AttendService ats;
	@Autowired
	private NoticeService ns;
	@Autowired
	private LoveService ls;
//	이메일
	@Autowired
	private JavaMailSender jms;
	
	@GetMapping("/")
	public String main(HttpSession session, Model model) {
//		강의 new 조회
		List<Academy> new_list = as.new_list();
//		강의 best 조회
		List<Academy> best_list = as.best_list();
//		강의 online 조회
		List<Academy> online_list = as.online_list();
//		강의 offline 조회
		List<Academy> offline_list = as.offline_list();
//		아티스트 조회
		List<Artist> art_list = ars.art_list();
//		참여 조회
		List<Attend> att_list = ats.att_list();
//		공지 조회
		List<Notice> nt_list = ns.nt_list();

//		최근 게시물 btn 출력
		long kk = new GregorianCalendar().getTimeInMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = "";
		for(Academy ad : new_list) {
			long cha = kk - ad.getA_date().getTime();
			date = sdf.format(cha);
			int year = Integer.parseInt(date.substring(0,4));
			int month = Integer.parseInt(date.substring(5,7));
			int day = Integer.parseInt(date.substring(8,10));
			if (year == 1970 && month == 1 && day <= 2)
				ad.setShow("y");
		}
		for(Academy ad : best_list) {
			long cha = kk - ad.getA_date().getTime();
			date = sdf.format(cha);
			int year = Integer.parseInt(date.substring(0,4));
			int month = Integer.parseInt(date.substring(5,7));
			int day = Integer.parseInt(date.substring(8,10));
			if (year == 1970 && month == 1 && day <= 2)
				ad.setShow("y");
		}
		for(Academy ad : online_list) {
			long cha = kk - ad.getA_date().getTime();
			date = sdf.format(cha);
			int year = Integer.parseInt(date.substring(0,4));
			int month = Integer.parseInt(date.substring(5,7));
			int day = Integer.parseInt(date.substring(8,10));
			if (year == 1970 && month == 1 && day <= 2)
				ad.setShow("y");
		}
		for(Academy ad : offline_list) {
			long cha = kk - ad.getA_date().getTime();
			date = sdf.format(cha);
			int year = Integer.parseInt(date.substring(0,4));
			int month = Integer.parseInt(date.substring(5,7));
			int day = Integer.parseInt(date.substring(8,10));
			if (year == 1970 && month == 1 && day <= 2)
				ad.setShow("y");
		}
		
		model.addAttribute("new_list", new_list);
		model.addAttribute("best_list", best_list);
		model.addAttribute("online_list", online_list);
		model.addAttribute("offline_list", offline_list);
		model.addAttribute("art_list", art_list);
		model.addAttribute("att_list", att_list);
		model.addAttribute("nt_list", nt_list);
		
//+ 재기추가
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

	return "sang_main";
}
	
//	알림 이메일
	@PostMapping("/email")
	@ResponseBody
	public String sendEmail(@RequestBody Email email) {
	    MimeMessage mm = jms.createMimeMessage();
	    try {
	        MimeMessageHelper mmh = new MimeMessageHelper(mm, true, "utf-8");
	        mmh.setSubject("[상상마당] 알림 신청 확인 메일");
	        mmh.setText("\n\n"+email.getA_name()+" 님 안녕하세요!\n상상마당 아카데미 프로그램 알림 신청해 주셔서 감사합니다 :)\n관심 가져주신 "+email.getE_keyword()+" 관련해 더 좋은 프로그램으로 찾아뵙겠습니다.\n\n감사합니다\n상상마당아카데미\n\n");
	        mmh.setTo(email.getA_email());
	        mmh.setFrom(new InternetAddress("hanmeansuh@naver.com", "상상마당 아카데미"));
	        jms.send(mm);
	        return "알림 신청이 정상적으로 처리되었습니다";
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return "알림 신청 실패";
	    }
	}

// 예지님 추가
@GetMapping("/academy/academyList_Free")
	public String academyList_Free(HttpSession session, Model model) {
		//게시판이동시 header의 logout => login으로 변하는문제
		//why? id를통해 login , logout표시하기에 id추가
		//왜 Object사용? 카카오로그인 시 데이터형이 Long이기때문
		Object idObject = session.getAttribute("id");
		String id = "";
		
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);
			
		}else {
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);	
		}
	
		List<Academy> list_Free = as.list_Free();
		long kk = new GregorianCalendar().getTimeInMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = "";
		for(Academy ad : list_Free) {
			long cha = kk - ad.getA_date().getTime();
			date = sdf.format(cha);
			int year = Integer.parseInt(date.substring(0,4));
			int month = Integer.parseInt(date.substring(5,7));
			int day = Integer.parseInt(date.substring(8,10));
			if (year == 1970 && month == 1 && day <= 2)
				ad.setShow("y");
		}
		
		model.addAttribute("id",id);
		model.addAttribute("list_Free",list_Free);
		return "academy/academyList_Free";
	}
	@GetMapping("/academy/academyList_Hobby")
	public String academyList_Hobby(HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);
			
		}else {
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);	
		}
		
		List<Academy> list_Hobby = as.list_Hobby();
		long kk = new GregorianCalendar().getTimeInMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = "";
		for(Academy ad : list_Hobby) {
			long cha = kk - ad.getA_date().getTime();
			date = sdf.format(cha);
			int year = Integer.parseInt(date.substring(0,4));
			int month = Integer.parseInt(date.substring(5,7));
			int day = Integer.parseInt(date.substring(8,10));
			if (year == 1970 && month == 1 && day <= 2)
				ad.setShow("y");
		}
		
		model.addAttribute("id",id);
		model.addAttribute("list_Hobby",list_Hobby);
		return "academy/academyList_Hobby";
	}
	@GetMapping("/academy/academyList_Write")
	public String academyList_Write(HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);
			
		}else {
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);	
		}
		
		List<Academy> list_Write = as.list_Write();
		long kk = new GregorianCalendar().getTimeInMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = "";
		for(Academy ad : list_Write) {
			long cha = kk - ad.getA_date().getTime();
			date = sdf.format(cha);
			int year = Integer.parseInt(date.substring(0,4));
			int month = Integer.parseInt(date.substring(5,7));
			int day = Integer.parseInt(date.substring(8,10));
			if (year == 1970 && month == 1 && day <= 2)
				ad.setShow("y");
		}
		
		model.addAttribute("id",id);
		model.addAttribute("list_Write",list_Write);
		return "academy/academyList_Write";
	}
	@GetMapping("/academy/academyList_Book")
	public String academyList_Book(HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);
			
		}else {
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);	
		}
		
		List<Academy> list_Book = as.list_Book();
		long kk = new GregorianCalendar().getTimeInMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = "";
		for(Academy ad : list_Book) {
			long cha = kk - ad.getA_date().getTime();
			date = sdf.format(cha);
			int year = Integer.parseInt(date.substring(0,4));
			int month = Integer.parseInt(date.substring(5,7));
			int day = Integer.parseInt(date.substring(8,10));
			if (year == 1970 && month == 1 && day <= 2)
				ad.setShow("y");
		}
		
		model.addAttribute("id",id);
		model.addAttribute("list_Book",list_Book);
		return "academy/academyList_Book";
	}
	@GetMapping("/academy/academyList_Design")
	public String academyList_Design(HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);
			
		}else {
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);	
		}
		
		List<Academy> list_Design = as.list_Design();
		long kk = new GregorianCalendar().getTimeInMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = "";
		for(Academy ad : list_Design) {
			long cha = kk - ad.getA_date().getTime();
			date = sdf.format(cha);
			int year = Integer.parseInt(date.substring(0,4));
			int month = Integer.parseInt(date.substring(5,7));
			int day = Integer.parseInt(date.substring(8,10));
			if (year == 1970 && month == 1 && day <= 2)
				ad.setShow("y");
		}
		
		model.addAttribute("id",id);
		model.addAttribute("list_Design",list_Design);
		return "academy/academyList_Design";
	}
	@GetMapping("/academy/academyList_Media")
	public String academyList_Media(HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);
			
		}else {
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);	
		}
		
		List<Academy> list_Media = as.list_Media();
		long kk = new GregorianCalendar().getTimeInMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = "";
		for(Academy ad : list_Media) {
			long cha = kk - ad.getA_date().getTime();
			date = sdf.format(cha);
			int year = Integer.parseInt(date.substring(0,4));
			int month = Integer.parseInt(date.substring(5,7));
			int day = Integer.parseInt(date.substring(8,10));
			if (year == 1970 && month == 1 && day <= 2)
				ad.setShow("y");
		}
		
		model.addAttribute("id",id);
		model.addAttribute("list_Media",list_Media);
		return "academy/academyList_Media";
	}
	@GetMapping("/academy/academyList_Photo")
	public String academyList_Photo(HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);
			
		}else {
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);	
		}
		
		List<Academy> list_Photo = as.list_Photo();
		long kk = new GregorianCalendar().getTimeInMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = "";
		for(Academy ad : list_Photo) {
			long cha = kk - ad.getA_date().getTime();
			date = sdf.format(cha);
			int year = Integer.parseInt(date.substring(0,4));
			int month = Integer.parseInt(date.substring(5,7));
			int day = Integer.parseInt(date.substring(8,10));
			if (year == 1970 && month == 1 && day <= 2)
				ad.setShow("y");
		}
		
		model.addAttribute("id",id);
		model.addAttribute("list_Photo",list_Photo);
		return "academy/academyList_Photo";
	}

	/* 강의상세 */
	@GetMapping("/academy/academyDetail")
	public void academyDetail(@RequestParam("a_no") int a_no, Model model
			, HttpSession session) {
		Object idObject = session.getAttribute("id");
		String id2 = "";
		
		if(idObject instanceof Long) {
			id2 = String.valueOf(idObject);
			System.out.println("변환된 id = " + id2);
			
		}else {
			id2 = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id2);	
		}
		
		as.increaseViewCount(a_no);
		System.out.println("ano : " + a_no);
		Academy ac = as.select(a_no);
		String id = as.findIdByANo(a_no);
		List<Academy> ac_list = as.ac_list(id);
		Love love = ls.findLoveByA_NoAndId(a_no,id2);

		
		model.addAttribute("love",love);
		model.addAttribute("id",id2);
		model.addAttribute("ac",ac);
		model.addAttribute("ac_list",ac_list);
	}
	// 좋아요
	@PostMapping("/academy/plus_heart")
	@ResponseBody
	public ResponseEntity<String> plus_heart(@RequestParam("a_no") int a_no, @RequestParam("id") String id) {
	    Academy academy = as.select(a_no);

	    if (academy != null) {  
	        as.increaseGoodCount(a_no);
	        Love love = new Love();
	        love.setA_no(a_no);
	        love.setId(id);
	        ls.love_insert(love);
	        return ResponseEntity.ok("Success");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@PostMapping("/academy/minus_heart")
	@ResponseBody
	public ResponseEntity<String> minus_heart(@RequestParam("a_no") int a_no, @RequestParam("id") String id) {
	    Academy academy = as.select(a_no);

	    if (academy != null) {  
	        as.decreaseGoodCount(a_no);
	        Love checklove = ls.findLoveByA_NoAndId(a_no, id);
	        ls.love_delete(checklove);
	        return ResponseEntity.ok("Success");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	//장바구니
	@PostMapping("/cartInsert")
	public String cartList(@RequestParam("a_no") int a_no, @RequestParam("c_count") int c_count, Model model
			, HttpSession session) {
		System.out.println("a_no : " + a_no);
		System.out.println("c_count : " + c_count);
		Object idObject = session.getAttribute("id");
		String id2 = "";
		
		if(idObject instanceof Long) {
			id2 = String.valueOf(idObject);
			System.out.println("변환된 id = " + id2);
			
		}else {
			id2 = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id2);	
		}
		Cart cart = new Cart();
		cart.setA_no(a_no);
		System.out.println("1 : " + a_no);
		cart.setC_count(c_count);
		System.out.println("2 : " + c_count);
		cart.setId(id2);
		System.out.println("3 : " + id2);
		as.insert_cart(cart);
		
		System.out.println("ano : " + a_no);
		System.out.println("c_count : " + c_count);
		model.addAttribute("id",id2);
		model.addAttribute("cart",cart);
		
		return "redirect:/cartList";
	}
		
}