package com.green.sang.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.green.sang.dto.Union;

@Mapper
public interface UnionMapper {
	int getTotal(String keyword);
	List<Union> uni_list(Map<String, Object> map);
}
