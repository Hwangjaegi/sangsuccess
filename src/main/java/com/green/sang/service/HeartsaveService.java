package com.green.sang.service;

import java.util.List;

import com.green.sang.dto.Heartsave;

public interface HeartsaveService {

	void save_atinsert(Heartsave at_save);
	void save_atdelete(Heartsave check_save);
	Heartsave findAt_noAndId(int at_no, String id);
	
	void save_winsert(Heartsave w_save);
	void save_wdelete(Heartsave check_wsave);
	Heartsave findW_noAndId(int w_no, String id);
	
	List<Heartsave> list_save();	

}
