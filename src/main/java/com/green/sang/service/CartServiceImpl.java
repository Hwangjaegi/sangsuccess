package com.green.sang.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.sang.dto.Academy;
import com.green.sang.dto.Cart;
import com.green.sang.mapper.CartMapper;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cm; 

	public List<Cart> cart_list(String id) {
		return cm.cart_list(id);
	}

	@Override
	public Cart select(Cart cart) {
		return cm.select(cart);
	}

	@Override
	public int insert(Cart cart) {
		return cm.insert(cart);
	}

	@Override
	public int update(Cart cart2) {
		return cm.update(cart2);
	}

	@Override
	public Academy selectCartAcademy(int a_no) {
		return cm.selectCartAcademy(a_no);
	}

	@Override
	public int deleteCart(Map<String, Object> map) {
		
		return cm.deleteCart(map);
	}

	@Override
	public int updateCountUp(int a_no) {
		
		return cm.updateCountUp(a_no);
	}

	@Override
	public int updateCountDown(int a_no) {
		
		return cm.updateCountDown(a_no);
	}

	@Override
	public List<Cart> selectCartID(String id) {
		
		return cm.selectCartID(id);
	}


}
