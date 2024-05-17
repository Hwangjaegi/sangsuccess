package com.green.sang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.sang.dto.Heartsave;

@Mapper
public interface HeartsaveMapper {

	void save_atinsert(Heartsave at_save);
	void save_atdelete(Heartsave check_save);
	Heartsave findAt_noAndId(Heartsave heart_at);
	
	void save_winsert(Heartsave w_save);
	void save_wdelete(Heartsave check_wsave);
	Heartsave findW_noAndId(Heartsave heart_w);
	
	List<Heartsave> list_save();
	
}
