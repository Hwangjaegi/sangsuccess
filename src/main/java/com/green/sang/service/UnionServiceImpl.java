package com.green.sang.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.sang.dto.Union;
import com.green.sang.mapper.UnionMapper;

@Service
public class UnionServiceImpl implements UnionService {
	@Autowired
	private UnionMapper um;

	public int getTotal(String keyword) {
	    return um.getTotal(keyword);
	}

	public List<Union> uni_list(int startRow, int endRow, String keyword) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("keyword", keyword);
		return um.uni_list(map);
	}
}
