package com.green.sang.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.sang.dto.Academy;
import com.green.sang.dto.Cart;
import com.green.sang.dto.Member;
import com.green.sang.service.CartService;
import com.green.sang.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	@Autowired
	private CartService cs;
	
	@Autowired
	private MemberService ms;
	
	@GetMapping("/cartList")
	public String cart(Model model , HttpSession session) {
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
		
		List<Cart> cartList = cs.cart_list(id);
		int cartTotal = cartList.size();
		System.out.println(cartTotal);
		
		List<Academy> academyList = new ArrayList<>();
		List<Integer> TotalPriceList = new ArrayList<>();
		int realTotalPrice = 0;
		int TotalCount = 0;
		for(Cart cart : cartList) {
			Academy academy = cs.selectCartAcademy(cart.getA_no());
			int totalprice = cart.getC_count() * academy.getPrice();
			System.out.println("총금액 : " +totalprice);
			realTotalPrice += totalprice;
			TotalPriceList.add(totalprice);
			TotalCount += cart.getC_count();
			academyList.add(academy); // 가져온 아카데미정보가 복수개일경우를 대비하여 list에담는다
		}
		
		model.addAttribute("id",id); // main에서 login,logout 버튼활성화에 사용
		model.addAttribute("cartList", cartList);
		model.addAttribute("cartTotal",cartTotal);
		model.addAttribute("academyList",academyList); //담은list를 보낸다
		model.addAttribute("TotalPriceList",TotalPriceList);
		model.addAttribute("realTotalPrice",realTotalPrice);
		model.addAttribute("TotalCount",TotalCount);
		return "cart/cartList";
	}
	
	@GetMapping("/cartList/addCart")
	public String addCart(@RequestParam("a_no") int a_no, Model model, HttpSession session) {
		
		return "cart/addCart";
	}
	
	@PostMapping("/deleteCartAcademy")
	@ResponseBody
	public String deleteCartAcademy(@RequestParam("a_noList") String a_noList1
			,HttpSession session) {
		Object idobj = session.getAttribute("id"); // 카카오 id타입이 Long이라서 검사위해 추가
		String id = null;
		
		if(idobj instanceof Long) { // id 데이터타입이 Long이라면
			Long idLong = (Long)idobj;
			id = String.valueOf(idLong); // string 형식으로 변환하여 id에 저장
		}else if(idobj instanceof String) {
			id=(String)idobj; // id데이터타입이 String이라면 id에 그대로저장
		}
		
		System.out.println("list = "+a_noList1);
		String[] a_list = a_noList1.split(","); // 하나의 문자열로 a_no,a_no,a_no ... 들어오기에 split사용
		try {
			for(String a_no1 : a_list) {
		    	int a_no = Integer.parseInt(a_no1);
		    	Map<String, Object> map = new HashMap<>();
		    	map.put("a_no", a_no);
		    	map.put("id", id);
	            int result = cs.deleteCart(map);
	            if(result > 0) System.out.println("삭제완료");
	            else System.out.println("삭제실패");
	  	    }
			return "삭제성공";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "삭제실패";
		}
	}
	@PostMapping("/upCartCount")
	@ResponseBody
	public void upCartCount(@RequestParam("a_no") String a_no) {
		System.out.println("들어온 a_no : " + a_no);
		int result = cs.updateCountUp(Integer.parseInt(a_no));
		System.out.println("성공여부 : " +result);
	}
	@PostMapping("/downCartCount")
	@ResponseBody
	public void downCartCount(@RequestParam("a_no") String a_no) {
		System.out.println("들어온 a_no : " + a_no);
		int result = cs.updateCountDown(Integer.parseInt(a_no));
		System.out.println("성공여부 : " +result);
	}
	
	@GetMapping("/orderCartAcademy")
	public String orderCartAcademy(Model model , @RequestParam("a_noList")List<Integer> a_noList 
			,HttpSession session) {
		Object idObject = session.getAttribute("id");
		String id = "";
		List<Academy> academylist2 = new ArrayList<>();
		int TotalPrice = 0;
		
		if(idObject instanceof Long) {	//카카오로그인으로 구매시
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);	
		}else {	//일반회원으로 구매시
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);	
		}

		Member member = ms.select(id);

		for(Integer a_no : a_noList) {	//1개 아닌 2개이상 구매일경우도 있기에 list로 생성
			System.out.println("들어온 상품번호 : " +a_no);
			Academy academy2 = ms.selectBuyInfo(a_no); //list에담긴 a_no로 아카데미조회
			System.out.println("값이있는지 체크 : " + academy2.getTitle());
			TotalPrice += academy2.getPrice(); //카트개수 * price로 변경
			academylist2.add(academy2);
		}
		
		model.addAttribute("academylist",academylist2);
		model.addAttribute("member",member);
		model.addAttribute("id",id);
		model.addAttribute("TotalPrice",TotalPrice);
		
		return "/member/buyAcademy";
	}
	
	
}
