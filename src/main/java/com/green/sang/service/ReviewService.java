package com.green.sang.service;

import java.util.List;

import com.green.sang.dto.Review;

public interface ReviewService {
//	리뷰 총 개수	
	int getTotal();
	int getTotal_hobby();
	int getTotal_write();
	int getTotal_book();
	int getTotal_design();
	int getTotal_media();
	int getTotal_photo();
	
//	리뷰 목록
	List<Review> review_list(int startRow, int endRow);
	List<Review> review_hobby(int startRow, int endRow);
	List<Review> review_write(int startRow, int endRow);
	List<Review> review_book(int startRow, int endRow);	
	List<Review> review_design(int startRow, int endRow);
	List<Review> review_media(int startRow, int endRow);
	List<Review> review_photo(int startRow, int endRow);
	
//	상세
	Review select_d(int r_no);
	
//	입력
	Review select(String r_title);
	int insert(Review review);

//	수정
	int update(Review review);
	
//	삭제
	int delete(int r_no);
	
//	이전글-다음글 (전체)
	Review review_leg_list(Review review);
	Review review_lead_list(Review review);

//	이전글-다음글 (카테고리)
	Review review_leg(Review review);
	Review review_lead(Review review);
}
