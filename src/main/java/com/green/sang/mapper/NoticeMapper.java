package com.green.sang.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.green.sang.dto.Notice;

@Mapper
public interface NoticeMapper {
//	공지 조회 (메인)
	List<Notice> nt_list();

//	공지 총 개수
	int getTotal();
	int getTotal_event();
	int getTotal_noti();
	
//	공지 목록
	List<Notice> notice_list(Map<String, Object> map);
	List<Notice> notice_event(Map<String, Object> map);
	List<Notice> notice_noti(Map<String, Object> map);

//	상세
	Notice select_d(int nt_no);

//	입력
	Notice select(String nt_title);
	int insert(Notice notice);

//	수정
	int update(Notice notice);

//	삭제
	int delete(int nt_no);
	
//	이전글-다음글 (전체)
	Notice notice_leg_list(Notice notice);
	Notice notice_lead_list(Notice notice);
	
//	이전글-다음글 (카테고리)
	Notice notice_leg(Notice notice);
	Notice notice_lead(Notice notice);
}