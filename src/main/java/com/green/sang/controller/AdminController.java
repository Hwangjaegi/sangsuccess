package com.green.sang.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.green.sang.dto.Academy;
import com.green.sang.dto.Buy;
import com.green.sang.dto.Member;
import com.green.sang.dto.PagingBean;
import com.green.sang.dto.QnA;
import com.green.sang.dto.Staff;
import com.green.sang.service.AcademyService;
import com.green.sang.service.MemberService;
import com.green.sang.service.NoticeService;
import com.green.sang.service.ReviewService;
import com.green.sang.service.StaffService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	@Autowired
	private MemberService ms;
	@Autowired
	private StaffService ss;
	@Autowired
	private ReviewService rs;
	@Autowired
	private NoticeService ns;
	@Autowired
	private AcademyService as;

	@GetMapping("/adminMemberList") // 관리자 회원목록
	public String adminMemberList(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum, Model model) {
		int rowPerPage = 10; // 한 화면에 보여주는 갯수
		if (pageNum == null || pageNum.equals(""))
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = ms.getTotal();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		
		System.out.println("커런트페이지 : " +currentPage);
		System.out.println("로우퍼페이지 : " +rowPerPage);
		System.out.println("토탈 : " +total);
		
		List<Member> list2 = ms.paginglist(startRow, endRow);
		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		System.out.println("스타트페이지" +pb.getStartPage());
		System.out.println("엔드페이지" +pb.getEndPage());
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("list2", list2);
		model.addAttribute("pb", pb);
		model.addAttribute("total", total);

		return "/admin/adminMemberList";
	}

	@GetMapping("/admin/deleteMember")
	public String MemberDelete(Model model, Member member) {
		int result = ms.delete(member.getId());

		Member member2 = ms.select(member.getId());

		model.addAttribute("result", result);
		model.addAttribute("name", member2.getName());
		return "/admin/deleteMember";
	}

	@GetMapping("/admin/admin")
	public String admin(Model model, HttpSession session) {
		Object idObject = session.getAttribute("id");
		String id = "";

		if (idObject instanceof Long) { // 카카오로그인으로 구매시
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);

		} else { // 일반회원으로 구매시
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);
		}
		System.out.println("아이디체크 : " + id);
		if (!id.equals("admin"))
			return "/admin/notAdmin";

		model.addAttribute("id", id);
		return "/admin/admin";
	}

	@GetMapping("/listAcademy")
	public String productPage(Model model, HttpSession session,
			@RequestParam(value = "pageNum", defaultValue = "1") String pageNum) {
		Object idObject = session.getAttribute("id");
		String id = "";

		if (idObject instanceof Long) { // 카카오로그인으로 구매시
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);

		} else { // 일반회원으로 구매시
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);
		}

		// 전체 아카데미 list , 페이징
		// 페이징
		System.out.println("들어온 pageNum :" + pageNum);
		int rowPerPage = 10;
		if (pageNum == null || pageNum.equals(""))
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = as.getTotal();
		System.out.println("아카데미개수 : " + total);
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		System.out.println("스타트 : " + startRow);
		System.out.println("엔드 : " + endRow);

		List<Academy> academy = as.AcademyList(startRow, endRow);
		System.out.println("페이지넘 : " +pageNum);

		model.addAttribute("id", id);
		model.addAttribute("academyList", academy);
		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		return "/admin/listAcademy";
	}

	@GetMapping("/admin/academyInsert")
	public String academyInsert() {

		return "/admin/academyInsert";
	}

	@PostMapping("/admin/academyInsertAction")
	public String academyInsert(Model model, Academy academy, MultipartHttpServletRequest mhr ,
			HttpSession session) throws IOException {
		
		Object idObject = session.getAttribute("id");
		String id = "";

		if (idObject instanceof Long) { // 카카오로그인으로 구매시
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);

		} else { // 일반회원으로 구매시
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);
		}
		
		String originalFileName = academy.getFile().getOriginalFilename(); // 업로드용 file에담긴 file명을 가져온다.
		System.out.println("originalFileName : " + originalFileName);
		UUID uuid = UUID.randomUUID(); // 랜덤한 문자열을 가져온다
		String fileName = uuid + originalFileName.substring(originalFileName.lastIndexOf(".")); // 랜덤문자 + 파일명 ,
																									// 마지막.기준 잘라내기
		System.out.println("fileName : " + fileName);
		academy.setImage(fileName); // 파일명을 image 컬럼으로 저장
		String imgSaveUrl = "src/main/resources/static/images/academy"; // 이미지 파일이 저장될 경로
		FileOutputStream fos = new FileOutputStream(new File(imgSaveUrl + "/" + fileName));
		System.out.println("fos : " + fos);
		fos.write(academy.getFile().getBytes()); // 저장용 file dto에 저장된 이미지의 크기
		fos.close();
		
		
		int max_A_no = ms.select_max_a_no();
		System.out.println("맥스 a_no : " + max_A_no);
		
		academy.setA_no(max_A_no);
		academy.setId(id);
		
		System.out.println("이미지파일 : " + academy.getImage());
		int result = ms.insertAcademy(academy);
		System.out.println("성공여부 : " +result);
		
		model.addAttribute("result" , result);
		model.addAttribute("academy",academy);
		return "/admin/academyInsertAction";
	}


	@GetMapping("/admin/deleteAcademy")
	public String deleteAcademy(Model model, Academy academy, HttpSession session
			,@RequestParam(value="pageNum" , defaultValue = "1")String pageNum) {
		Object idObject = session.getAttribute("id");
		String id = "";

		if (idObject instanceof Long) { // 카카오로그인으로 구매시
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);

		} else { // 일반회원으로 구매시
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);
		}

		System.out.println("들어온 a_no : " + academy.getA_no());
		ms.deleteDetail(academy.getA_no());
		System.out.println("디테일삭제");
		int result = ms.deleteAcademy(academy.getA_no());
		System.out.println("성공 : " + result);

		model.addAttribute("id", id);
		model.addAttribute("result", result);
		model.addAttribute("pageNum",pageNum);
		return "/admin/deleteAcademy";
	}

	@GetMapping("/admin/updateAcademy")
	public String updateAcademy(Model model, Academy academy ,@RequestParam(value="pageNum",defaultValue = "1")String pageNum) {
		System.out.println("들어온 a_no :" + academy.getA_no());
		/* int result = ms.updateAcademy(academy.getA_no()); */
		Academy academy2 = ms.selectAcademy(academy.getA_no());
		System.out.println("들어온 아카데미 :" + academy2.getA_name());
		System.out.println("제목 :" + academy2.getTitle());

		model.addAttribute("pageNum",pageNum);
		model.addAttribute("academy", academy2);
		return "/admin/updateAcademy";
	}

	@PostMapping("/admin/academyUpdateAction")
	public String UpdateAcademyAction(Model model, Academy academy , HttpSession session
			,@RequestParam(value="pageNum",defaultValue = "1")String pageNum) throws IOException {
		Object idObject = session.getAttribute("id");
		String id = "";

		if (idObject instanceof Long) { // 카카오로그인으로 구매시
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);

		} else { // 일반회원으로 구매시
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);
		}
		System.out.println("들어온파일 : " + academy.getFile().getOriginalFilename());
		System.out.println("들어온이미지 : " + academy.getImage());
		
		if(academy.getFile().getOriginalFilename() == null || academy.getFile().getOriginalFilename().isEmpty()) {
			System.out.println("들어왔니");
			Academy academy2 = ms.selectAcademy(academy.getA_no());
			System.out.println("academy2 image : "+academy2.getImage());
			academy.setImage(academy2.getImage());
		}else {
			String originalFileName = academy.getFile().getOriginalFilename(); // 업로드용 file에담긴 file명을 가져온다.
			System.out.println("originalFileName : " + originalFileName);
			UUID uuid = UUID.randomUUID(); // 랜덤한 문자열을 가져온다
			String fileName = uuid + originalFileName.substring(originalFileName.lastIndexOf(".")); // 랜덤문자 + 파일명 ,
																										// 마지막.기준 잘라내기
			System.out.println("fileName : " + fileName);
			academy.setImage(fileName); // 파일명을 image 컬럼으로 저장
			String imgSaveUrl = "src/main/resources/static/images/academy"; // 이미지 파일이 저장될 경로
			FileOutputStream fos = new FileOutputStream(new File(imgSaveUrl + "/" + fileName));
			System.out.println("fos : " + fos);
			fos.write(academy.getFile().getBytes()); // 저장용 file dto에 저장된 이미지의 크기
			fos.close();
		}
		System.out.println("a_no : " + academy.getA_no());
		System.out.println("title : " + academy.getTitle());
		System.out.println("price : " + academy.getPrice());
		System.out.println("intro : " + academy.getIntro());
		System.out.println("tag : " + academy.getTag());
		System.out.println("schedule : " + academy.getSchedule());
		System.out.println("a_name : " + academy.getA_name());
		System.out.println("place : " + academy.getPlace());
		System.out.println("inwon : " + academy.getInwon());
		System.out.println("turn : " + academy.getTurn());
		System.out.println("request : " + academy.getRequest());
		System.out.println("onoff : " + academy.getOnoff());
		System.out.println("count : " + academy.getCount());
		System.out.println("status : " + academy.getStatus());
		System.out.println("content " + academy.getContent());
		System.out.println("ct_no : " + academy.getCt_no());
		System.out.println("들어온 image : " +academy.getImage());
		academy.setId(id);
		
		int result = ms.updateAcademy(academy);
		System.out.println("성공 : " + result);

		model.addAttribute("result", result);
		model.addAttribute("pageNum",pageNum);
		return "/admin/updateAcademyAction";
	}

	@GetMapping("/adminOrder")
	public String adminOrder(Model model, HttpSession session,
			@RequestParam(value = "pageNum", defaultValue = "1") String pageNum) {
		Object idObject = session.getAttribute("id");
		String id = "";

		if (idObject instanceof Long) { // 카카오로그인으로 구매시
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);

		} else { // 일반회원으로 구매시
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);
		}

		// 페이징
		System.out.println("들어온 pageNum :" + pageNum);
		int rowPerPage = 10;
		if (pageNum == null || pageNum.equals(""))
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = ms.getTotalOrder();
		System.out.println("주문 총 개수 : " + total);
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		System.out.println("스타트 : " + startRow);
		System.out.println("엔드 : " + endRow);

		List<Buy> buylist = ms.selectAdminBuyList(startRow, endRow);
		List<Academy> academylist = new ArrayList<>();

		for (Buy buy : buylist) {
			System.out.println("조회된 구매내역번호 : " + buy.getB_no());
			Academy academy = ms.selectBuyAcademy(buy.getA_no());
			System.out.println("가지고온 아카데미 : " + academy.getA_no());
			academylist.add(academy);
		}
		model.addAttribute("id", id);
		model.addAttribute("academylist", academylist);
		model.addAttribute("buylist", buylist);
		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		return "/admin/adminOrder";
	}

	@GetMapping("/admin/cancleOk")
	public String cancleOk(Model model, Buy buy) {
		System.out.println("들어온 구매번호 : " + buy.getB_no());
		int result = ms.updateCancleOk(buy.getB_no());
		System.out.println("결과 : " + result);

		model.addAttribute("result", result);

		return "/admin/updateCancleOk";
	}

	@GetMapping("deleteMember_cancle")
	public String deleteMember_cancle(Model model, Member member) {
		System.out.println("들어온 아이디 : " + member.getId());
		int result = ms.updateMember2(member.getId());
		System.out.println("결과 : " + result);

		model.addAttribute("result", result);
		return "/admin/deleteMember_cancle";
	}

	@GetMapping("/adminQnA")
	public String adminQnA(Model model, HttpSession session,
			@RequestParam(value = "pageNum", defaultValue = "1") String pageNum) {
		Object idObject = session.getAttribute("id");
		String id = "";

		if (idObject instanceof Long) { // 카카오로그인으로 구매시
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);

		} else { // 일반회원으로 구매시
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);
		}

		// 페이징
		System.out.println("들어온 pageNum :" + pageNum);
		int rowPerPage = 10;
		if (pageNum == null || pageNum.equals(""))
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = ms.getTotalQnA();
		System.out.println("주문 총 개수 : " + total);
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		System.out.println("스타트 : " + startRow);
		System.out.println("엔드 : " + endRow);

		List<QnA> qnalist = ms.adminQnAList(startRow, endRow);
		System.out.println(qnalist);
		for (QnA qna : qnalist) {
			System.out.println("들어온 qna : " + qna.getQ_no());
		}

		model.addAttribute("qnalist", qnalist);
		model.addAttribute("id", id);
		model.addAttribute("total", total);
		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);

		return "/admin/adminQnAList";
	}

	@GetMapping("/admin/adminQnADetail")
	public String adminQnADetail(Model model, QnA qna, HttpSession session) {
		Object idObject = session.getAttribute("id");
		String id = "";

		if (idObject instanceof Long) { // 카카오로그인으로 구매시
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);

		} else { // 일반회원으로 구매시
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);
		}

		System.out.println("들어온 문의번호 : " + qna);
		QnA qnaItem = ms.selectQnA(qna.getQ_no());
		System.out.println("가져온 qna제목 : " + qna.getQ_title());

		model.addAttribute("id", id);
		model.addAttribute("qnaItem", qnaItem);
		return "/admin/adminQnADetail";
	}

	@GetMapping("/admin/adminQnADelete")
	public String adminQnADelete(Model model, QnA qna) {
		System.out.println("삭제대기중 q_no : " + qna.getQ_no());
		int result = ms.deleteQnA(qna.getQ_no());
		System.out.println("삭제여부 : " + result);

		model.addAttribute("result", result);
		return "/admin/adminQnADelete";
	}

	// 서머노트 이미지 업로드시 ajax로 이미지 업로드처리
	@PostMapping("/static/images/academy/")
	public ResponseEntity<String> summerimage(@RequestParam("file") MultipartFile img) throws IOException {
		System.out.println("이미지왔어용 : " + img.getOriginalFilename());

		try {
			/*
			 * String originalFileName = img.getOriginalFilename();
			 * System.out.println("originalFileName : " + originalFileName); UUID uuid =
			 * UUID.randomUUID(); // 랜덤한 문자열을 가져온다 String fileName = uuid +
			 * originalFileName.substring(originalFileName.lastIndexOf(".")); // 랜덤문자 + 파일명
			 * , // // 마지막.기준 잘라내기 System.out.println("fileName : " + fileName);
			 */
			String fileName = img.getOriginalFilename();
			String imgSaveUrl = "src/main/resources/static/images/academy"; // 이미지 파일이 저장될 경로
			FileOutputStream fos = new FileOutputStream(new File(imgSaveUrl + "/" + fileName));
			System.out.println("fos : " + fos);
			fos.write(img.getBytes()); // 저장용 file dto에 저장된 이미지의 크기
			fos.close();
			System.out.println("/images/academy/" + fileName);
			return ResponseEntity.ok().body("/images/academy/" + fileName);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 중 오류가 발생했습니다");
		}
	}
	
	@GetMapping("/admin/adminQnAAnswer")
	public String adminQnAAnswer(Model model , QnA qna) {
		System.out.println("들어온 q_no : " + qna.getQ_no());
		
		model.addAttribute("q_no" , qna.getQ_no());
		return "/admin/adminQnAAnswer";
	}
	@GetMapping("/admin/test")
	public String test(Model model , Academy academy) {
		System.out.println("들어온 a_no : " + academy.getA_no());
		Academy academy2 = ms.selectAcademy(academy.getA_no());
		
		model.addAttribute("academy",academy2);
		return "/admin/test";
	}

}
