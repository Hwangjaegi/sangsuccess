package com.green.sang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.sang.dto.Inquiry;

@Mapper
public interface InquiryMapper {

	List<Inquiry> selectFaqList();
	
}
