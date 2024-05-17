package com.green.sang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.sang.dto.PagingBean;
import com.green.sang.dto.Union;
import com.green.sang.service.UnionService;


@Controller
public class UnionController {
	@Autowired
	private UnionService us;

	@GetMapping("/search")
	public String search(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
            @RequestParam(value ="keyword", defaultValue = "") String keyword,
            Model model) {
		int rowPerPage = 10;
		if (pageNum == null || pageNum.equals("")) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = us.getTotal(keyword);
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Union> uni_list = us.uni_list(startRow, endRow, keyword);
		
//		u:text 오류나는 태그 삭제
//		for(Union uni : uni_list) {
//			String content1 = uni.getContent();
//			String content2 = content1.replace("<p>", "<br><br>");
//			String content = content2.replace("</p>", "");
//			uni.setContent(content);
//		}

		model.addAttribute("pb", pb);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("uni_list", uni_list);
		
		return "search";
	}
}