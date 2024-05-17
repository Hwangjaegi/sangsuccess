package com.green.sang.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.sang.dto.Academy;
import com.green.sang.dto.Buy;
import com.green.sang.dto.Cancle_Buy;
import com.green.sang.dto.Cart;
import com.green.sang.dto.Kakao;
import com.green.sang.dto.Member;
import com.green.sang.dto.QnA;
import com.green.sang.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberMapper mm;

	@Override
	public Member select(String id) {
		
		return mm.select(id);
	}

	@Override
	public int insert(Member member) {

		return mm.insert(member);
	}
	
	@Override
	public Member selectEmail(String email) {
		System.out.println("메일값 : " + email);
		return mm.selectEmail(email);
	}

	@Override
	public Member select_PW_Email(String email , String id) {
		System.out.println("이메일값 : " +email);
		System.out.println("아이디값 : " + id);
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		map.put("id", id);
		return mm.select_PW_Email(map);
	}

	@Override
	public void updatePass(Member member2) {
		mm.updatePass(member2);
		
	}

	@Override
	public List<Member> selectNameToTel(Map<String, Object> map) {
		
		return mm.selectNameToTel(map);
	}

	@Override
	public int updateMember(Member member) {
		
		return mm.updateMember(member);	}

	@Override
	public int delete(String id) {
		
		return mm.delete(id);
	}

	@Override
	public List<Buy> selectBuyList(String id, int startRow2, int endRow2) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("startRow", startRow2);
		map.put("endRow", endRow2);
		
		return mm.selectBuyList(map);
	}
	
	@Override
	public List<Buy> selectAdminBuyList(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		return mm.selectAdminBuyList(map);
	}

	@Override
	public Academy selectBuyInfo(int a_no) {
		
		return mm.selectBuyInfo(a_no);
	}

	@Override
	public Buy selectBuy(int b_no) {
		
		return mm.selectBuy(b_no);
	}

	@Override
	public Academy selectImg(Integer b_no) {
		
		return mm.selectImg(b_no);
	}

	@Override
	public int select_Max_CaNo() {
		
		return mm.select_Max_CaNo();
	}

	@Override
	public int insertCancle(Cancle_Buy Cancle_Buy) {
		
		return mm.insertCancle(Cancle_Buy);
	}

	@Override
	public void updateCancle(int b_no) {
		mm.updateCancle(b_no);
		
	}

	@Override
	public int updateCancle_Stop(int b_no) {
		
		return mm.updateCancle_Stop(b_no);
	}

	@Override
	public List<Cart> selectWishList(String id, int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		return mm.selectWishList(map);
	}

	@Override
	public int insertQnA(QnA qna) {
		
		return mm.insertQnA(qna);
	}

	@Override
	public int select_Max_QnA_No(String id) {
		
		return mm.select_Max_QnA_No(id);
	}

	@Override
	public List<QnA> selectQnAList(String id, int startRow4, int endRow4) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("startRow", startRow4);
		map.put("endRow", endRow4);
		
		return mm.selectQnAList(map);
	}

	@Override
	public int deleteQnA(int q_no) {
		
		return mm.deleteQnA(q_no);
	}

	@Override
	public QnA selectQnA(int q_no) {
		
		return mm.selectQnA(q_no);
	}

	@Override
	public int QnAUpdate(QnA qna) {
		
		return mm.QnAUpdate(qna);
	}

	@Override
	public int select_Max_Wish_No(String id) {
		
		return mm.select_Max_Wish_No(id);
	}

	@Override
	public Member selectKakao(String id) {
		
		return mm.selectKakao(id);
	}

	@Override
	public void insertKakao(Kakao kakao) {
		mm.insertKakao(kakao);
		
	}

	@Override
	public int select_count_QnA_No(String id) {
		
		return mm.select_count_QnA_No(id);
	}

	@Override
	public void deleteCancel(int b_no) {
		mm.deleteCancel(b_no);
		
	}

	@Override
	public int select_Max_Bno() {
		
		return mm.select_Max_Bno();
	}
	
	//민권님
	public int getTotal() {
		return mm.getTotal();
	}

	public List<Member> paginglist(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return mm.paginglist(map);
	}

	@Override
	public int deleteAcademy(int a_no) {
		
		return mm.deleteAcademy(a_no);
	}

	@Override
	public void deleteDetail(int a_no) {
		mm.deleteDetail(a_no);
		
	}

	@Override
	public Academy selectAcademy(int a_no) {
		
		return mm.selectAcademy(a_no);
	}

	@Override
	public int updateAcademy(Academy academy) {
		
		return mm.updateAcademy(academy);
	}

	@Override
	public Academy selectBuyAcademy(int a_no) {
	
		return mm.selectBuyAcademy(a_no);
	}

	@Override
	public int updateCancleOk(int b_no) {
		
		return mm.updateCancleOk(b_no);
	}

	@Override
	public int updateMember2(String id) {
		
		return mm.updateMember2(id);
	}

	@Override
	public List<QnA> adminQnAList(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		return mm.adminQnAList(map);
	}

	@Override
	public int getTotalOrder() {
		
		return mm.getTotalOrder();
	}

	@Override
	public int getTotalQnA() {
		
		return mm.getTotalQnA();
	}

	@Override
	public int insertAcademy(Academy academy) {
		
		return mm.insertAcademy(academy);
	}

	@Override
	public int select_max_a_no() {
		
		return mm.select_max_a_no();
	}

	@Override
	public int insertBuy(Buy buy) {
		
		return mm.insertBuy(buy);
	}

	@Override
	public int select_Max_Buy(String id) {
		System.out.println("아이디값 : " + id);
		return mm.select_Max_Buy(id);
	}

	@Override
	public int select_Max_QnA_No_ID(String id) {
		
		return mm.select_Max_QnA_No_ID(id);
	}

	@Override
	public int select_Max_cancle(String id) {
		
		return mm.select_Max_cancle(id);
	}

	@Override
	public List<Academy> selectAcademyCancle(String id, int startRow3, int endRow3) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("startRow", startRow3);
		map.put("endRow", endRow3);
		
		
		return mm.selectAcademyCancle(map);
	}



}
