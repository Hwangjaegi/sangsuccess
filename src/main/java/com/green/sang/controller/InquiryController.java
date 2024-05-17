package com.green.sang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.green.sang.dto.Inquiry;
import com.green.sang.service.InquiryService;

import jakarta.servlet.http.HttpSession;


@Controller
public class InquiryController {
	@Autowired
	private InquiryService is; 
	
	@GetMapping("inquiry/inquiry")
	public String getMethodName(Model model , HttpSession session) {
		Object idObject = session.getAttribute("id");
		String id = "";
		
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);
			
		}else {
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);	
		}
		model.addAttribute("id",id);
		
		List<Inquiry> inquiry = is.selectFaqList();
		
		for(Inquiry inquiry2 : inquiry) {
			System.out.println("test : " +inquiry2.getIn_no());
		}
		
		model.addAttribute("inquiry",inquiry);
		return "/inquiry/inquiryMain";
	}
	
}
