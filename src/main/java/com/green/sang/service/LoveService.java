package com.green.sang.service;

import com.green.sang.dto.Love;

public interface LoveService {

	void love_insert(Love love);
	Love findLoveByA_NoAndId(int a_no, String id);
	void love_delete(Love checklove);
}
