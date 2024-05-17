package com.green.sang.service;

import java.util.List;
import java.util.Map;

import com.green.sang.dto.Academy;
import com.green.sang.dto.Cart;

public interface CartService {

	List<Cart> cart_list(String id);
	// 회원이 장바구니에 해당 상품을 담았는지 조회
	Cart select(Cart cart);
	// 장바구니에 추가
	int insert(Cart cart);
	// 장바구니에 담겨있으면 재고 하나 추가
	int update(Cart cart2);;
	
	//장바구니에있는 a_no로 academy정보 가져오기
	Academy selectCartAcademy(int a_no);
	int deleteCart(Map<String, Object> map);
	int updateCountUp(int a_no);
	int updateCountDown(int a_no);
	List<Cart> selectCartID(String id);

}
