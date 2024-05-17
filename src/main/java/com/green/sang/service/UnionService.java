package com.green.sang.service;

import java.util.List;

import com.green.sang.dto.Union;

public interface UnionService {
	int getTotal(String keyword);
	List<Union> uni_list(int startRow, int endRow, String keyword);

}
