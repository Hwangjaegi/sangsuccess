package com.green.sang.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.green.sang.dto.Love;

@Mapper
public interface LoveMapper {

	void love_insert(Love love);
	Love findLoveByA_NoAndId(Love love);
	void love_delete(Love checklove);

}
