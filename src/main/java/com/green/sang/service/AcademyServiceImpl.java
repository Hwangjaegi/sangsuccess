package com.green.sang.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.sang.dto.Academy;
import com.green.sang.dto.Cart;
import com.green.sang.mapper.AcademyMapper;

@Service
public class AcademyServiceImpl implements AcademyService {
	@Autowired
	private AcademyMapper am;

//	강의 new 조회
	public List<Academy> new_list() {
		return am.new_list();
	}
//	강의 best 조회
	public List<Academy> best_list() {
		return am.best_list();
	}
//	강의 online 조회
	public List<Academy> online_list() {
		return am.online_list();
	}
//	강의 offline 조회
	public List<Academy> offline_list() {
		return am.offline_list();
	}

// 예지님 추가
@Override
	public List<Academy> list_Free() {
		return am.list_Free();
	}
	@Override
	public List<Academy> list_Hobby() {
		return am.list_Hobby();
	}
	@Override
	public List<Academy> list_Write() {
		return am.list_Write();
	}
	@Override
	public List<Academy> list_Book() {
		return am.list_Book();
	}
	@Override
	public List<Academy> list_Design() {
		return am.list_Design();
	}
	@Override
	public List<Academy> list_Media() {
		return am.list_Media();
	}
	@Override
	public List<Academy> list_Photo() {
		return am.list_Photo();
	}
	@Override
	public Academy select(int a_no) {
		return am.select(a_no);
	}
	@Override
	public List<Academy> ac_list(String id) {
		return am.ac_list(id);
	}
	@Override
	public String findIdByANo(int a_no) {
		return am.findIdByANo(a_no);
	}
	@Override
	public int getTotal() {
		
		return am.getTotal();
	}
	@Override
	public List<Academy> AcademyList(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
	
		return am.AcademyList(map);
	}
	// 좋아요
	@Override
	public void increaseGoodCount(int a_no) {
		am.increaseGoodCount(a_no);		
	}
	@Override
	public void decreaseGoodCount(int a_no) {
		am.decreaseGoodCount(a_no);
		
	}
	//조회수
	@Override
	public void increaseViewCount(int a_no) {
		am.increaseViewCount(a_no);
	}
	//장바구니
	@Override
	public void insert_cart(Cart cart) {
		System.out.println("s a_no : "+cart.getA_no());
		System.out.println("s c_count : "+cart.getC_count());
		System.out.println("s id : "+cart.getId());
		am.insert_cart(cart);
		
	}

}