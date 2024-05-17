package com.green.sang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.sang.dto.Love;
import com.green.sang.mapper.LoveMapper;

@Service
public class LoveServiceImpl implements LoveService {
	@Autowired
	private LoveMapper lm;
	
	@Override
	public void love_insert(Love love) {
		lm.love_insert(love);
	}
	@Override
	public void love_delete(Love checklove) {
		lm.love_delete(checklove);
	}
	@Override
	public Love findLoveByA_NoAndId(int a_no, String id) {
		Love love = new Love();
		love.setA_no(a_no);
		love.setId(id);
		return lm.findLoveByA_NoAndId(love);
	}

}
