package com.green.sang.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.sang.dto.Notice;
import com.green.sang.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeMapper nm;

//	공지 조회 (메인)
	public List<Notice> nt_list() {
		return nm.nt_list();
	}

//	공지 총 개수
	public int getTotal() {
		return nm.getTotal();
	}
	public int getTotal_event() {
		return nm.getTotal_event();
	}
	public int getTotal_noti() {
		return nm.getTotal_noti();
	}
	
//	공지 목록	
	public List<Notice> notice_list(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return nm.notice_list(map);
	}
	public List<Notice> notice_event(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return nm.notice_event(map);
	}
	public List<Notice> notice_noti(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return nm.notice_noti(map);
	}

//	상세
	public Notice select_d(int nt_no) {
		return nm.select_d(nt_no);
	}

//	입력
	public Notice select(String nt_title) {
		return nm.select(nt_title);
	}
	public int insert(Notice notice) {
		return nm.insert(notice);
	}

//	수정
	public int update(Notice notice) {
		return nm.update(notice);
	}

//	삭제
	public int delete(int nt_no) {
		return nm.delete(nt_no);
	}

//	이전글-다음글 (전체)
	public Notice notice_leg_list(Notice notice) {
		return nm.notice_leg_list(notice);
	}
	public Notice notice_lead_list(Notice notice) {
		return nm.notice_lead_list(notice);
	}
	
//	이전글-다음글 (카테고리)
	public Notice notice_leg(Notice notice) {
		return nm.notice_leg(notice);
	}
	public Notice notice_lead(Notice notice) {
		return nm.notice_lead(notice);
	}
}