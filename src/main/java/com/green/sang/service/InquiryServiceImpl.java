package com.green.sang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.sang.dto.Inquiry;
import com.green.sang.mapper.InquiryMapper;

@Service
public class InquiryServiceImpl implements InquiryService {
	@Autowired
	private InquiryMapper im;

	@Override
	public List<Inquiry> selectFaqList() {
		
		return im.selectFaqList();
	}
}
