package com.green.sang.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.sang.dto.Review;
import com.green.sang.mapper.ReviewMapper;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewMapper rm;
	
//	리뷰 총 개수
	public int getTotal() {
		return rm.getTotal();
	}
	public int getTotal_hobby() {
		return rm.getTotal_hobby();
	}
	public int getTotal_write() {
		return rm.getTotal_write();
	}
	public int getTotal_book() {
		return rm.getTotal_book();
	}
	public int getTotal_design() {
		return rm.getTotal_design();
	}
	public int getTotal_media() {
		return rm.getTotal_media();
	}
	public int getTotal_photo() {
		return rm.getTotal_photo();
	}
	
//	리뷰 목록
	public List<Review> review_list(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return rm.review_list(map);
	}
	public List<Review> review_hobby(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return rm.review_hobby(map);
	}
	public List<Review> review_write(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return rm.review_write(map);
	}
	public List<Review> review_book(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return rm.review_book(map);
	}
	public List<Review> review_design(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return rm.review_design(map);
	}
	public List<Review> review_media(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return rm.review_media(map);
	}
	public List<Review> review_photo(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return rm.review_photo(map);
	}
	
//	상세
	public Review select_d(int r_no) {
		return rm.select_d(r_no);
	}
	
//	입력
	public Review select(String r_title) {
		return rm.select(r_title);
	}
	public int insert(Review review) {
		return rm.insert(review);
	}

//	수정
	public int update(Review review) {
		return rm.update(review);
	}
	
//	삭제
	public int delete(int r_no) {
		return rm.delete(r_no);
	}

//	이전글-다음글 (전체)
	public Review review_leg_list(Review review) {
		return rm.review_leg_list(review);
	}
	public Review review_lead_list(Review review) {
		return rm.review_lead_list(review);
	}
	
//	이전글-다음글 (카테고리)	
	public Review review_leg(Review review) {
		return rm.review_leg(review);
	}
	public Review review_lead(Review review) {
		return rm.review_lead(review);
	}
}