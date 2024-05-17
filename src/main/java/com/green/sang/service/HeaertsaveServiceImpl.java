package com.green.sang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.sang.dto.Heartsave;
import com.green.sang.mapper.HeartsaveMapper;

@Service
public class HeaertsaveServiceImpl implements HeartsaveService {
	@Autowired
	private HeartsaveMapper hsm;

	@Override
	public void save_atinsert(Heartsave at_save) {
		hsm.save_atinsert(at_save);	
	}
	@Override
	public void save_atdelete(Heartsave check_save) {
		hsm.save_atdelete(check_save);		
	}
	@Override
	public Heartsave findAt_noAndId(int at_no, String id) {
		Heartsave heart_at = new Heartsave();
		heart_at.setAt_no(at_no);
		heart_at.setId(id);
		return hsm.findAt_noAndId(heart_at);
	}
	
	@Override
	public void save_winsert(Heartsave w_save) {
		hsm.save_winsert(w_save);
	}
	@Override
	public void save_wdelete(Heartsave check_wsave) {
		hsm.save_wdelete(check_wsave);
	}
	@Override
	public Heartsave findW_noAndId(int w_no, String id) {
		Heartsave heart_w = new Heartsave();
		heart_w.setW_no(w_no);
		heart_w.setId(id);
		return hsm.findW_noAndId(heart_w);
	}
	
	@Override
	public List<Heartsave> list_save() {
		return hsm.list_save();
	}
}
