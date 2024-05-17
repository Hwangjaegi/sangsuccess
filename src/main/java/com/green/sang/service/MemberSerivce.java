package com.green.sang.service;

import java.util.List;
import java.util.Map;

import com.green.sang.dto.Academy;
import com.green.sang.dto.Buy;
import com.green.sang.dto.Cancle_Buy;
import com.green.sang.dto.Cart;
import com.green.sang.dto.Kakao;
import com.green.sang.dto.Member;
import com.green.sang.dto.QnA;

public interface MemberSerivce {

	Member select(String id); // 아이디로 초회

	Member selectEmail(String email); // 이메일로 아이디찾기 

	Member select_PW_Email(String email , String id); // 비밀번호 이메일전송

	int insert(Member member);

	void updatePass(Member member2);

	List<Member> selectNameToTel(Map<String, Object> map);

	int update(Member member);

	int delete(String id);

	List<Buy> selectBuyList(String id);

	Academy selectBuyInfo(int a_no);

	Buy selectBuy(int b_no);

	Academy selectImg(Integer b_no);

	int select_Max_CaNo();

	int insertCancle(Cancle_Buy Cancle_Buy);

	void updateCancle(int b_no);

	int updateCancle_Stop(int b_no);

	List<Cart> selectWishList(String id);

	int insertQnA(QnA qna);

	int select_Max_QnA_No(String id);

	List<QnA> selectQnAList(String id);

	int deleteQnA(int q_no);

	QnA selectQnA(int q_no);

	int QnAUpdate(QnA qna);

	int select_Max_Wish_No(String id);

	Member selectKakao(String id);

	void insertKakao(Kakao kakao);

	



}
