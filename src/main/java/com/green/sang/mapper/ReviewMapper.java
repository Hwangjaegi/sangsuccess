package com.green.sang.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.green.sang.dto.Review;

@Mapper
public interface ReviewMapper {
//	리뷰 총 개수
	int getTotal();
	int getTotal_hobby();
	int getTotal_write();
	int getTotal_book();
	int getTotal_design();
	int getTotal_media();
	int getTotal_photo();
	
//	리뷰 목록
	List<Review> review_list(Map<String, Object> map);
	List<Review> review_hobby(Map<String, Object> map);
	List<Review> review_write(Map<String, Object> map);
	List<Review> review_book(Map<String, Object> map);	
	List<Review> review_design(Map<String, Object> map);
	List<Review> review_media(Map<String, Object> map);
	List<Review> review_photo(Map<String, Object> map);
	
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