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

public interface MemberService {

	Member select(String id); // 아이디로 초회

	Member selectEmail(String email); // 이메일로 아이디찾기 

	Member select_PW_Email(String email , String id); // 비밀번호 이메일전송

	int insert(Member member);

	void updatePass(Member member2);

	List<Member> selectNameToTel(Map<String, Object> map);

	int updateMember(Member member);

	int delete(String id);

	List<Buy> selectBuyList(String id, int startRow2, int endRow2);

	Academy selectBuyInfo(int a_no);

	Buy selectBuy(int b_no);

	Academy selectImg(Integer b_no);

	int select_Max_CaNo();

	int insertCancle(Cancle_Buy Cancle_Buy);

	void updateCancle(int b_no);

	int updateCancle_Stop(int b_no);

	List<Cart> selectWishList(String id, int startRow, int endRow);

	int insertQnA(QnA qna);

	int select_Max_QnA_No(String id);

	List<QnA> selectQnAList(String id, int startRow4, int endRow4);

	int deleteQnA(int q_no);

	QnA selectQnA(int q_no);

	int QnAUpdate(QnA qna);

	int select_Max_Wish_No(String id);

	int select_count_QnA_No(String id);

	Member selectKakao(String id);

	void insertKakao(Kakao kakao);

	void deleteCancel(int b_no);

	int select_Max_Bno();
	// 회원 갯수
	int getTotal();
	// 모든 회원 조회(페이징)
	List<Member> paginglist(int startRow, int endRow);

	void deleteDetail(int a_no);

	int deleteAcademy(int a_no);

	Academy selectAcademy(int a_no);

	int updateAcademy(Academy academy);

	List<Buy> selectAdminBuyList(int startRow, int endRow);

	Academy selectBuyAcademy(int a_no);

	int updateCancleOk(int b_no);

	int updateMember2(String id);

	List<QnA> adminQnAList(int startRow, int endRow);

	int getTotalOrder();

	int getTotalQnA();

	int insertAcademy(Academy academy);

	int select_max_a_no();

	int insertBuy(Buy buy);

	int select_Max_Buy(String id);

	int select_Max_QnA_No_ID(String id);

	int select_Max_cancle(String id);

	List<Academy> selectAcademyCancle(String id, int startRow3, int endRow3);



}
