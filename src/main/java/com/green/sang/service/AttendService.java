package com.green.sang.service;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;

import com.green.sang.dto.Attend;
import com.green.sang.dto.Heartsave;
import com.green.sang.dto.Member;
import com.green.sang.dto.ReplyAttend;
import com.green.sang.dto.Tenaudit;
import com.green.sang.dto.Tentext;
import com.green.sang.dto.Write;

public interface AttendService {
//	참여 조회	
	List<Attend> att_list();
	
	// 취미진심
	List<Attend> list_attend();
	int getTotal(String keyword);
	Attend select(int at_no);
	Member select_mb(int at_no);
	//조회수
	void increaseViewCount(int at_no);
	//좋아요
	void increaseGoodCount(int at_no);
	void decreaseGoodCount(int at_no);
	void aty_change(int at_no, String id);
	void atn_change(int at_no, String id);
	
	// 참여-글쓰기
	List<Write> list_write(int startRow, int endRow, String keyword);
	Write select_w(int w_no);
	Member select_mbw(int w_no);
	List<Member> list_member();
	//좋아요
	void w_increaseGoodCount(int w_no);
	void w_decreaseGoodCount(int w_no);
	void wy_change(int w_no, String id);
	void wn_change(int w_no, String id);
	
	//글쓰기입력
	Member select_wm(String id);
	void saveWrite(String title, String content, String id);
	//글쓰기 수정
	void updateWrite(String title, String content, int w_no);
	//글쓰기 삭제
	boolean deletePage(int w_no);
	
	/* 열줄공모전 */
	List<Tentext> list_tentext(int startRow, int endRow);
	int getTotal_tt();
	Tentext select_t(int t_no);
	
	// 열줄-심사평
	Tenaudit select_ta(int ta_no);
	List<Tenaudit> list_tenaudit();

	void at_countup(int at_bno);
	void w_countup(int w_bno);

}
