package com.green.sang.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.green.sang.dto.Academy;
import com.green.sang.dto.Buy;
import com.green.sang.dto.Cancle_Buy;
import com.green.sang.dto.Cart;
import com.green.sang.dto.Kakao;
import com.green.sang.dto.Member;
import com.green.sang.dto.QnA;

@Mapper
public interface MemberMapper {

	Member select(String id);

	Member selectEmail(String email);

	Member select_PW_Email(Map<String, Object> map);

	int insert(Member member);

	void updatePass(Member member2);

	List<Member> selectNameToTel(Map<String, Object> map);

	int updateMember(Member member);

	int delete(String id);

	List<Buy> selectBuyList(Map<String, Object> map);
	
	List<Buy> selectAdminBuyList(Map<String, Object> map);

	Academy selectBuyInfo(int a_no);

	Buy selectBuy(int b_no);

	Academy selectImg(Integer b_no);

	int select_Max_CaNo();

	int insertCancle(Cancle_Buy Cancle_Buy);

	int updateCancle(int b_no);

	int updateCancle_Stop(int b_no);

	List<Cart> selectWishList(Map<String, Object> map);

	int insertQnA(QnA qna);

	int select_Max_QnA_No(String id);

	List<QnA> selectQnAList(Map<String, Object> map);

	int deleteQnA(int q_no);

	QnA selectQnA(int q_no);

	int QnAUpdate(QnA qna);

	int select_Max_Wish_No(String id);

	Member selectKakao(String id);

	void insertKakao(Kakao kakao);

	int select_count_QnA_No(String id);

	void deleteCancel(int b_no);

	int select_Max_Bno();
	
	int getTotal();

	List<Member> paginglist(Map<String, Object> map);

	int deleteAcademy(int a_no);

	void deleteDetail(int a_no);

	Academy selectAcademy(int a_no);

	int updateAcademy(Academy academy);

	Academy selectBuyAcademy(int a_no);

	int updateCancleOk(int b_no);

	int updateMember2(String id);

	List<QnA> adminQnAList(Map<String,Object> map);

	int getTotalOrder();

	int getTotalQnA();

	int insertAcademy(Academy academy);

	int select_max_a_no();

	int insertBuy(Buy buy);

	int select_Max_Buy(String id);

	int select_Max_QnA_No_ID(String id);

	int select_Max_cancle(String id);

	List<Academy> selectAcademyCancle(Map<String, Object> map);


}
