package com.green.sang.service;

import java.util.List;

import com.green.sang.dto.Academy;
import com.green.sang.dto.Cart;

public interface AcademyService {
//	강의 new 조회
	List<Academy> new_list();
//	강의 best 조회
	List<Academy> best_list();
//	강의 online 조회
	List<Academy> online_list();
//	강의 offline 조회	
	List<Academy> offline_list();

// 예지님 추가
	List<Academy> list_Free();
	List<Academy> list_Hobby();
	List<Academy> list_Write();
	List<Academy> list_Book();
	List<Academy> list_Design();
	List<Academy> list_Media();
	List<Academy> list_Photo();
	Academy select(int a_no);
	List<Academy> ac_list(String id);
	String findIdByANo(int a_no);
	
	//아카데미 전체개수
	int getTotal();
	List<Academy> AcademyList(int startRow, int endRow);
	
	//좋아요
	void increaseGoodCount(int a_no);
	void decreaseGoodCount(int a_no);
	//조회수
	void increaseViewCount(int a_no);
	//장바구니
	void insert_cart(Cart cart);
	
}
