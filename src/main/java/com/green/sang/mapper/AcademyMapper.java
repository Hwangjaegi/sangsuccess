package com.green.sang.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.green.sang.dto.Academy;
import com.green.sang.dto.Cart;

@Mapper
public interface AcademyMapper {
//	강의 new 조회
	List<Academy> new_list();
//	강의 best 조회
	List<Academy> best_list();
//	강의 online 조회	
	List<Academy> online_list();
//	강의 offline 조회	
	List<Academy> offline_list();

// 예지님추가
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
	// 좋아요
	void increaseGoodCount(int a_no);
	void decreaseGoodCount(int a_no);
	//조회수
	void increaseViewCount(int a_no);
	
// 민권님추가
	int getTotal();
	List<Academy> AcademyList(Map<String, Object> map);
	//장바구니
	void insert_cart(Cart cart);



}