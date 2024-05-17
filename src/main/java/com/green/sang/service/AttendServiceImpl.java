package com.green.sang.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.green.sang.dto.Attend;
import com.green.sang.dto.Member;
import com.green.sang.dto.ReplyAttend;
import com.green.sang.dto.Tenaudit;
import com.green.sang.dto.Tentext;
import com.green.sang.dto.Write;
import com.green.sang.mapper.AttendMapper;

@Service
public class AttendServiceImpl implements AttendService {
	@Autowired
	private AttendMapper atm;

//	참여 조회
	@Override
	public List<Attend> list_attend() {
		return atm.list_attend();
	}
	
	// 취미진심
	@Override
	public List<Attend> att_list() {
		return atm.att_list();
	}	
	@Override
	public Attend select(int at_no) {
		return atm.select(at_no);
	}
	@Override 
	public Member select_mb(int at_no) {
		return atm.select_mb(at_no);
	}
	//조회수
	@Override
	public void increaseViewCount(int at_no) {
		atm.increaseViewCount(at_no);		
	}
	//좋아요
	@Override
	public void increaseGoodCount(int at_no) {
		atm.increaseGoodCount(at_no);
	}
	@Override
	public void decreaseGoodCount(int at_no) {
		atm.decreaseGoodCount(at_no);
	}
	@Override
	public void aty_change(int at_no, String id) {
		Attend y_attend = new Attend();
		y_attend.setAt_no(at_no);
		y_attend.setId(id);
		atm.aty_change(y_attend);
	}

	@Override
	public void atn_change(int at_no, String id) {
		Attend n_attend = new Attend();
		n_attend.setAt_no(at_no);
		n_attend.setId(id);
		atm.atn_change(n_attend);		
	}
	
	//참여-글쓰기
	@Override
	public int getTotal(String keyword) {
		return atm.getTotal(keyword);
	}
	@Override
	public List<Write> list_write(int startRow, int endRow, String keyword) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("keyword", keyword);
		return atm.list_write(map);
	}
	@Override
	public Write select_w(int w_no) {
		return atm.select_w(w_no);
	}
	@Override
	public Member select_mbw(int w_no) {
		return atm.select_mbw(w_no);
	}
	@Override
	public List<Member> list_member() {
		return atm.list_member();
	}
	// 좋아요
	@Override
	public void w_increaseGoodCount(int w_no) {
		atm.w_increaseGoodCount(w_no);
	}
	@Override
	public void w_decreaseGoodCount(int w_no) {
		atm.w_decreaseGoodCount(w_no);
	}
	@Override
	public void wy_change(int w_no, String id) {
		Write y_write = new Write();
		y_write.setW_no(w_no);
		y_write.setId(id);
		atm.wy_change(y_write);
	}
	@Override
	public void wn_change(int w_no, String id) {
		Write n_write = new Write();
		n_write.setW_no(w_no);
		n_write.setId(id);
		atm.wn_change(n_write);
		
	}

	//글쓰기 입력
	@Override
	public Member select_wm(String id) {
		return atm.select_wm(id);
	}
	@Override
	public void saveWrite(String title, String content, String id) {
		 Write writing = new Write();
		 writing.setW_title(title);
		 writing.setW_content(content);
		 writing.setId(id);
	        atm.saveWrite(writing);		
	}
	//글쓰기 수정
	@Override
	public void updateWrite(String title, String content, int w_no) {
		 Write write_update = new Write();
		 write_update.setW_title(title);
		 write_update.setW_content(content);
		 write_update.setW_no(w_no);
	     atm.updateWrite(write_update);		
	}	
//	글쓰기 삭제
	@Override
	public boolean deletePage(int w_no) {
		return atm.deletePage(w_no);
	}
	
	// 열줄공모전
	@Override
	public List<Tentext> list_tentext(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return atm.list_tentext(map);
	}
	@Override
	public int getTotal_tt() {
		return atm.getTotal_tt();
	}
	@Override
	public Tentext select_t(int t_no) {
		return atm.select_t(t_no);
	}

	//열줄-심사평
	@Override
	public Tenaudit select_ta(int ta_no) {
		return atm.select_ta(ta_no);
	}
	@Override
	public List<Tenaudit> list_tenaudit() {
		return atm.list_tenaudit();
	}

	@Override
	public void at_countup(int at_bno) {
		atm.at_countup(at_bno);
		
	}
	@Override
	public void w_countup(int w_bno) {
		System.out.println("s w_bno : "+w_bno);
		atm.w_countup(w_bno);
	}

}
