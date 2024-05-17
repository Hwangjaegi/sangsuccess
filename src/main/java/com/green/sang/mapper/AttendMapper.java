package com.green.sang.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.green.sang.dto.Attend;
import com.green.sang.dto.Member;
import com.green.sang.dto.ReplyAttend;
import com.green.sang.dto.Tenaudit;
import com.green.sang.dto.Tentext;
import com.green.sang.dto.Write;

@Mapper
public interface AttendMapper {
//	참여 조회
	List<Attend> att_list();
	
	//취미진심
	List<Attend> list_attend();	
	Attend select(int at_no);
	Member select_mb(int at_no);
	//조회수
	void increaseViewCount(int at_no);
	//좋아요
	void increaseGoodCount(int at_no);
	void decreaseGoodCount(int at_no);
	void aty_change(Attend y_attend);
	void atn_change(Attend n_attend);
	
	// 참여-글쓰기
	List<Write> list_write(Map<String, Object> map);
	int getTotal(String keyword);
	Write select_w(int w_no);
	Member select_mbw(int w_no);
	List<Member> list_member();
	//좋아요
	void w_increaseGoodCount(int w_no);
	void w_decreaseGoodCount(int w_no);
	void wy_change(Write y_write);
	void wn_change(Write n_write);
	//글쓰기 입력
	Member select_wm(String id);
	void saveWrite(Write write);
	//글쓰기 수정
	void updateWrite(Write write_update);
	//글쓰기 삭제
	boolean deletePage(int w_no);
	
	//열줄공모전
	List<Tentext> list_tentext(Map<String, Object> map);
	int getTotal_tt();
	Tentext select_t(int t_no);
	
	//열줄-심사평
	Tenaudit select_ta(int ta_no);
	List<Tenaudit> list_tenaudit();
	
	//참여 댓글수
	void at_countup(int at_bno);
	void w_countup(int w_bno);

}
