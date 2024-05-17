package com.green.sang.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.sang.dto.Academy;
import com.green.sang.dto.Buy;
import com.green.sang.dto.Cancle_Buy;
import com.green.sang.dto.Cart;
import com.green.sang.dto.Kakao;
import com.green.sang.dto.Member;
import com.green.sang.dto.PagingBean;
import com.green.sang.dto.QnA;
import com.green.sang.service.CartService;
import com.green.sang.service.CartServiceImpl;
import com.green.sang.service.MemberService;

import jakarta.mail.Multipart;
import jakarta.servlet.http.HttpSession;
import oracle.jdbc.proxy.annotation.Post;
import oracle.net.ano.Ano;

@Controller
public class MemberController {

	@Autowired
	private MemberService ms;

	@Autowired
	private BCryptPasswordEncoder bpe;

	@Autowired
	private CartService cs;

	@GetMapping("/loginForm")
	public String loginForm() {
		return "member/loginForm";
	}

	@PostMapping("/login")
	public String login(Model model, Member member, HttpSession session) {
		int result = 0;
		System.out.println("id : " + member.getId());
		System.out.println("pass : " + member.getPassword());
		Member member2 = ms.select(member.getId());

		if (member2 == null || member2.getDel().equals("y"))
			result = -1;
		else if (bpe.matches(member.getPassword(), member2.getPassword())) {
			session.setAttribute("id", member.getId());
			System.out.println("일반세션저장 id : " + session.getId());
			model.addAttribute("name", member2.getName());
			result = 1;
		}

		model.addAttribute("result", result);

		return "member/login"; // 컨트롤러의 main url형태로 전달한다.
	}

	@PostMapping("/join")
	public String join(Model model, Member member, MultipartHttpServletRequest mhr, HttpSession session)
			throws IOException {

		int result = 0;
		Member member2 = ms.select(member.getId());

		if (member2 == null) {
			if (member.getFile() == null || member.getFile().isEmpty()) {
				member.setImage("user_image.jpg"); // 이미지없이 가입할시 기본이미지
				String encPass = bpe.encode(member.getPassword()); // 저장될 암호를 암호화
				member.setPassword(encPass); // dto에 암호화 암호를 저장
			} else {
				String originalFileName = member.getFile().getOriginalFilename(); // 업로드용 file에담긴 file명을 가져온다.
				System.out.println("originalFileName : " + originalFileName);
				UUID uuid = UUID.randomUUID(); // 랜덤한 문자열을 가져온다
				String fileName = uuid + originalFileName.substring(originalFileName.lastIndexOf(".")); // 랜덤문자 + 파일명 ,
																										// 마지막.기준 잘라내기
				System.out.println("fileName : " + fileName);
				member.setImage(fileName); // 파일명을 image 컬럼으로 저장
				String imgSaveUrl = "src/main/resources/static/upload"; // 이미지 파일이 저장될 경로
				FileOutputStream fos = new FileOutputStream(new File(imgSaveUrl + "/" + fileName));
				System.out.println("fos : " + fos);
				fos.write(member.getFile().getBytes()); // 저장용 file dto에 저장된 이미지의 크기
				fos.close();

				String encPass = bpe.encode(member.getPassword()); // 저장될 암호를 암호화
				member.setPassword(encPass); // dto에 암호화 암호를 저장
			}
			result = ms.insert(member);
		} else
			result = -1;

		model.addAttribute("result", result);
		model.addAttribute("name", member.getName());

		return "member/join";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.removeAttribute("id");

		return "member/logout";
	}

	@GetMapping("/mypage")
	public String Mypage(Model model, HttpSession session, @RequestParam(value = "menu", defaultValue = "") String menu,
			@RequestParam(value = "b_no", required = false) Integer b_no,
			@RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
			@RequestParam(value = "pageNum2", defaultValue = "1") String pageNum2,
			@RequestParam(value = "pageNum3", defaultValue = "1") String pageNum3,
			@RequestParam(value = "pageNum4", defaultValue = "1") String pageNum4) {
		
		Object idObject = session.getAttribute("id");
		String id = "";
		String kakaoChk = "";
		if (idObject instanceof Long) {
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);
			kakaoChk = "true";

		} else {
			id = (String) session.getAttribute("id");
			kakaoChk = "false";
		}
		
		//초기화면로드시 order화면
		if (menu == null || menu.equals(""))
			menu = "order";

		//신청-예약조회 page
		// id로 위시리스트 조회 후 있으면 보여주기wish_list
		int rowPerPage2 = 4; // 한 화면에 보여주는 갯수
		if (pageNum2 == null || pageNum2.equals(""))
			pageNum2 = "1";
		int currentPage2 = Integer.parseInt(pageNum2);
		System.out.println("커런트 : " + currentPage2);
		int total2 = ms.select_Max_Buy(id);
		System.out.println("토탈 : " + total2);
		int startRow2 = (currentPage2 - 1) * rowPerPage2 + 1;
		System.out.println("스탓로우 : " +startRow2);
		int endRow2 = startRow2 + rowPerPage2 - 1;
		System.out.println("엔드로우 : " + endRow2);
		System.out.println("메이지넘3 : " + pageNum3);
		PagingBean pb2 = new PagingBean(currentPage2, rowPerPage2, total2);

		Member member = ms.select(id);

		List<Buy> buyList = ms.selectBuyList(id,startRow2,endRow2);
		List<Academy> academyList = new ArrayList<>();

		//구매취소완료시
		int TotalPrice = 0;
		int rowPerPage3 = 4; 
		if (pageNum3 == null || pageNum3.equals(""))
			pageNum3 = "1";
		int currentPage3 = Integer.parseInt(pageNum3);
		int total3 = ms.select_Max_cancle(id); // cancle y인 데이터 총개수
		int startRow3 = (currentPage3 - 1) * rowPerPage3 + 1;
		int endRow3 = startRow3 + rowPerPage3 - 1;

		PagingBean pb3 = new PagingBean(currentPage3, rowPerPage3, total3);
		System.out.println(pb3.getStartPage());
		System.out.println(pb3.getStartPage());
		
		for (Buy buyItem : buyList) {
			int a_no = buyItem.getA_no();
			Academy academy2 = ms.selectBuyInfo(a_no);

			if (academy2 != null) {
				// 구매정보 아카데미 DTO 추가
				academy2.setC_count(buyItem.getC_count());
				academy2.setCancle(buyItem.getCancle());
				academy2.setB_no(buyItem.getB_no());
				academy2.setB_date(buyItem.getB_date());
				// 저장된 아카데미객체 리스트로추가
				academyList.add(academy2);
				System.out.println("승인여부 : " + academy2.getCancle());
				// 취소완료 되지 않았으면 취소조회에 보이지않기위해 추가
				
				if (academy2.getCancle().equals("y")) { // 취소조회
					
					List<Academy> buyCancleList = ms.selectAcademyCancle(id,startRow3,endRow3);
				
					
					String cancleSuccess = "true";
					model.addAttribute("cancleSuccess", cancleSuccess);
					model.addAttribute("buyCancleList",buyCancleList);
				}
				TotalPrice += academy2.getPrice() * buyItem.getC_count();

			}
		}
		model.addAttribute("TotalPrice", TotalPrice);
		model.addAttribute("pageNum2", pageNum2);
		model.addAttribute("pb2", pb2);
		model.addAttribute("total2", total2);
		model.addAttribute("pageNum3", pageNum3);
		model.addAttribute("pb3", pb3);
		model.addAttribute("total3", total3);
		model.addAttribute("buyList",buyList);

		// 구매취소 시 해당 구매내역의 b_no를 전달받아 처리
		if (b_no != null) {
			Buy buy = ms.selectBuy(b_no);

			Academy academy3 = ms.selectImg(buy.getA_no());

			int buyTotal = buy.getC_count() * academy3.getPrice();

			model.addAttribute("buyItem", buy);
			model.addAttribute("academyItem", academy3);
			model.addAttribute("buyTotal", buyTotal);

		}

		// id로 위시리스트 조회 후 있으면 보여주기wish_list
		int rowPerPage = 6; // 한 화면에 보여주는 갯수
		if (pageNum == null || pageNum.equals(""))
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = ms.select_Max_Wish_No(id);
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		
		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Cart> wishlist = ms.selectWishList(id,startRow,endRow);
		
		if (wishlist != null) {
			List<Academy> academyArr = new ArrayList<>();
			int count_Wish_No = ms.select_Max_Wish_No(id);
			System.out.println("위시리스트 Max : " + count_Wish_No);

			for (Cart wish : wishlist) {
				int wish_Ano = wish.getA_no();
				Academy academy = ms.selectBuyInfo(wish_Ano);
				academyArr.add(academy);
			}

			model.addAttribute("count_Wish_No", count_Wish_No);
			model.addAttribute("academyArr", academyArr);
		}

		// id로 문의내역 있으면 보여주기 QnA
		int rowPerPage4 = 4; // 한 화면에 보여주는 갯수
		if (pageNum4 == null || pageNum4.equals(""))
			pageNum4 = "1";
		int currentPage4 = Integer.parseInt(pageNum4);
		System.out.println("커런트페이지 : "+currentPage4);
		int total4 = ms.select_Max_QnA_No_ID(id);
		System.out.println("qna최대번호 : " + total4);
		int startRow4 = (currentPage4 - 1) * rowPerPage4 + 1;
		int endRow4 = startRow4 + rowPerPage4 - 1;
				
		PagingBean pb4 = new PagingBean(currentPage4, rowPerPage4, total4);
		
		List<QnA> qnalist = ms.selectQnAList(id,startRow4,endRow4);
		System.out.println("qnalist :" +qnalist);
		model.addAttribute("pageNum4", pageNum4);
		model.addAttribute("pb4", pb4);
		model.addAttribute("total4", total4);
		model.addAttribute("qnalist", qnalist);
		int count_QnA_No = ms.select_count_QnA_No(id);
		model.addAttribute("count_QnA_No", count_QnA_No);
		
		

		model.addAttribute("academyList", academyList);
		model.addAttribute("buyList", buyList);
		model.addAttribute("member", member);
		model.addAttribute("id", id);
		model.addAttribute("menu", menu);
		model.addAttribute("kakaoChk", kakaoChk);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pb", pb);
		model.addAttribute("total", total);
		return "member/mypage";
	}

	@GetMapping("/cancleSelect")
	public String cancleSelect(Model model, Cancle_Buy Cancle_Buy, Buy buy) {
		int result = 0;

		if (Cancle_Buy.getCa_content() != null && Cancle_Buy.getCa_option() != null) {

			int maxCancle_No = ms.select_Max_CaNo();
			System.out.println("Max 취소번호 : " + maxCancle_No);
			int b_no = buy.getB_no();
			System.out.println("B_no : " + b_no);

			Cancle_Buy.setCa_no(maxCancle_No);
			Cancle_Buy.setB_no(b_no);

			result = ms.insertCancle(Cancle_Buy); // cancle_buy테이블에 추가
			try {
				ms.updateCancle(b_no); // 취소요청 n => r (request 약자)
			} catch (Exception e) {
				System.out.println("구매취소 업데이트 쿼리중 에러" + e.getMessage());
			}

		} else
			result = -1;

		model.addAttribute("result", result);
		return "member/cancle";
	}

	@GetMapping("/cancle_Stop")
	public String cancle_Stop(Model model, Buy buy) {
		System.out.println("들어온 B넘버 : " + buy.getB_no());
		int result = 0;

		result = ms.updateCancle_Stop(buy.getB_no());
		ms.deleteCancel(buy.getB_no()); // 취소철회시 cancle_Buy에 들어간 데이터중 b_no에 해당하는 데이터 삭제

		model.addAttribute("result", result);

		return "member/updateCancle_Stop";
	}

	@PostMapping("/update")
	public String update(Model model, Member member, MultipartHttpServletRequest mhr) throws IOException {
		int result = 0;

		Member member2 = ms.select(member.getId());

		if (member2 != null) {
			if (member.getFile() == null || member.getFile().isEmpty()) {
				member.setImage(member2.getImage()); // 이미지 수정없이 수정할시 기존이미지
				if (member.getPassword() != null) {
					String encPass = bpe.encode(member.getPassword()); // 저장될 암호를 암호화
					member.setPassword(encPass); // dto에 암호화 암호를 저장

				}
			} else {
				String originalFileName = member.getFile().getOriginalFilename(); // 업로드용 file에담긴 file명을 가져온다.
				System.out.println("originalFileName : " + originalFileName);
				UUID uuid = UUID.randomUUID(); // 랜덤한 문자열을 가져온다
				String fileName = uuid + originalFileName.substring(originalFileName.lastIndexOf(".")); // 랜덤문자 + 파일명 ,
																										// 마지막.기준 잘라내기
				System.out.println("fileName : " + fileName);
				member.setImage(fileName); // 파일명을 image 컬럼으로 저장
				String imgSaveUrl = "src/main/resources/static/upload"; // 이미지 파일이 저장될 경로
				FileOutputStream fos = new FileOutputStream(new File(imgSaveUrl + "/" + fileName));
				System.out.println("fos : " + fos);
				fos.write(member.getFile().getBytes()); // 저장용 file dto에 저장된 이미지의 크기
				fos.close();

				String encPass = bpe.encode(member.getPassword()); // 저장될 암호를 암호화
				member.setPassword(encPass); // dto에 암호화 암호를 저장
			}

			System.out.println("닉네임 : " + member.getName());
			System.out.println("암호 : " + member.getPassword());
			System.out.println("전화번호 : " + member.getTel());
			System.out.println("아이디 : " + member.getId());
			System.out.println("사진이름 : " + member.getImage());

			result = ms.updateMember(member);

		} else
			result = -1;

		model.addAttribute("result", result);
		return "member/update";
	}

	@GetMapping("/delete")
	public String delete(Model model, Member member, HttpSession session) {
		System.out.println("어왔니 아이디 " + member.getId());
		int result = 0;

		result = ms.delete(member.getId());
		session.removeAttribute("id");

		model.addAttribute("result", result);
		return "member/delete";
	}

	@GetMapping("/QnAWhiteForm")
	public String QnAWhiteForm(Model model, QnA qna , @RequestParam(value="answer" , defaultValue = "") String answer) {
		String answerChk = "";
		
		if (qna.getQ_no() != 0) { // 수정,답글일경우
			System.out.println("왔니");
			QnA qnaItem = ms.selectQnA(qna.getQ_no());
			if(answer.equals("true")) answerChk = answer;
			else answerChk = "false";
			System.out.println(answerChk);
			model.addAttribute("qnaItem", qnaItem);
			model.addAttribute("answer",answerChk);
		}

		return "member/QnAWhiteForm";
	}

	@GetMapping("/QnAWhite")
	public String QnAWhite(Model model, QnA qna, HttpSession session) {
		int result = 0;
		Object idObject = session.getAttribute("id");
		String id = "";

		if (idObject instanceof Long) {
			id = String.valueOf(idObject);

		} else {
			id = (String) session.getAttribute("id");
		}

		System.out.println("제목 : " + qna.getQ_title());
		System.out.println("내용 : " + qna.getQ_content());
		System.out.println("아이디 : " + id);

		int max_QnA_No = ms.select_Max_QnA_No(id);
		System.out.println("맥스 Qna넘버 : " + max_QnA_No);
		if (max_QnA_No == 0)
			qna.setQ_no(1);
		else
			qna.setQ_no(max_QnA_No);

		qna.setId(id);

		result = ms.insertQnA(qna);

		model.addAttribute("result", result);

		return "member/QnAWhite";
	}

	@GetMapping("/QnAUpdate")
	public String QnAUpdate(Model model, QnA qna) {
		int result = 0;
		System.out.println("qna : " + qna.getQ_no());
		if (qna.getQ_no() != 0) {
			System.out.println("문의번호 : " + qna.getQ_no());

			result = ms.QnAUpdate(qna);

		}
		model.addAttribute("result", result);
		return "member/QnAUpdate";
	}

	@GetMapping("/qnaDelete")
	public String qnaDelete(Model model, QnA qna) {
		int result = 0;
		System.out.println("문의번호 : " + qna.getQ_no());

		result = ms.deleteQnA(qna.getQ_no());

		model.addAttribute("result", result);

		return "member/QnADelete";
	}

	// 결제하기 아직 장바구니 미구현으로 http://localhost/buyAcademy?a_no=1&a_no=2 입력
	@GetMapping("/buyAcademy") // list에는 장바구니에담긴 아카데미의 a_no가 담긴다 , 추후 Cart로 받아서 처리예정
	public String buyAcademy(Model model, @RequestParam("a_no") List<Integer> a_noList, HttpSession session) {
		Object idObject = session.getAttribute("id");
		String id = "";
		List<Academy> academylist2 = new ArrayList<>();
		int TotalPrice = 0;

		if (idObject instanceof Long) { // 카카오로그인으로 구매시
			id = String.valueOf(idObject);
		} else { // 일반회원으로 구매시
			id = (String) session.getAttribute("id");
		}
		System.out.println("아이디체크 : " + id);

		Member member = ms.select(id);
		System.out.println("닉네임조회체크 : " + member.getName());

		for (Integer a_no : a_noList) { // 1개 아닌 2개이상 구매일경우도 있기에 list로 생성
			System.out.println("들어온 상품번호 : " + a_no);
			Academy academy2 = ms.selectBuyInfo(a_no); // list에담긴 a_no로 아카데미조회
			System.out.println("값이있는지 체크 : " + academy2.getTitle());
			TotalPrice += academy2.getPrice(); // 카트개수 * price로 변경
			academylist2.add(academy2);
		}

		model.addAttribute("member", member);
		model.addAttribute("academylist", academylist2);
		model.addAttribute("TotalPrice", TotalPrice);
		model.addAttribute("id", id);

		return "/member/buyAcademy";
	}

	@GetMapping("/buyComplete")
	public String getMethodName(Model model, @RequestParam("payment") String payment, HttpSession session,
			@RequestParam("TotalPrice") String TotalPrice, @RequestParam("a_no") String aNoArrayString) {
		Object idObject = session.getAttribute("id");
		String id = "";
		List<String> aNoList = null; // Json으로 들어온 a_no값을 list형식으로 담을준비

		if (idObject instanceof Long) { // 카카오로그인으로 구매시
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);

		} else { // 일반회원으로 구매시
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);
		}

		// JSON 문자열을 배열로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// objectMapper를이용하여 JSON데이터를 String형식의 list로 담는다.
			aNoList = objectMapper.readValue(aNoArrayString, new TypeReference<List<String>>() {
			});

		} catch (JsonProcessingException e) {
			e.printStackTrace(); // 담는과정에서 에러발생시 처리
		}

		List<Integer> int_anoList = new ArrayList<>();
		for (String ano : aNoList) {
			int_anoList.add(Integer.parseInt(ano));
		}
		System.out.println("리스트값 : " + int_anoList);
		List<Cart> cartList = cs.selectCartID(id);
		System.out.println("카트리스트 : " +cartList);
		// a_no가 정상적으로 들어왔다면 구매처리
		Buy buy = new Buy();
		// 장바구니에 저장된 아카데미 개수를 가져와서 buy.setc_count에 저장
		System.out.println("카트리스트사이즈 : " +cartList.size());
		System.out.println("a_nolist 사이즈 :" + aNoList.size());
		
		if (cartList.size() > 0) {
			for (Integer ano : int_anoList) {
				for (Cart cart : cartList) {
					System.out.println("카트겟번호 : " +cart.getA_no());
					if (ano == cart.getA_no()) {
						int maxBno = ms.select_Max_Bno();
						System.out.println("맥스 : "  +maxBno);
						buy.setA_no(ano);
						buy.setB_no(maxBno);
						buy.setC_count(cart.getC_count());
						buy.setId(id);
						int result = ms.insertBuy(buy);
						System.out.println("구매처리결과 : " + result);
						
						Map<String, Object> map = new HashMap<>();
						map.put("id", id);
						map.put("a_no", cart.getA_no());
						int reslut2 = cs.deleteCart(map);
						System.out.println("장바구니 삭제결과 : " + reslut2);
						cartList.remove(0);
						break;
					}else {
						int maxBno = ms.select_Max_Bno();
						System.out.println("맥스 : "  +maxBno);
						buy.setA_no(ano);
						buy.setB_no(maxBno);
						buy.setC_count(cart.getC_count());
						buy.setId(id);
						int result = ms.insertBuy(buy);
						System.out.println("구매처리결과 : " + result);
						break;
					}
				}
			}
		}else {
			for (Integer ano : int_anoList) {
				int maxBno = ms.select_Max_Bno();
				System.out.println("맥스 : "  +maxBno);
				buy.setB_no(maxBno);
				buy.setA_no(ano);
				buy.setC_count(1);
				buy.setId(id);
				int result = ms.insertBuy(buy);
				System.out.println("구매처리결과 : " + result);
			}
		}

		model.addAttribute("id", id);
		model.addAttribute("payment", payment); // 계좌이체 계좌정보안내
		model.addAttribute("TotalPrice", TotalPrice);
		return "/member/buyComplate";
	}

}
