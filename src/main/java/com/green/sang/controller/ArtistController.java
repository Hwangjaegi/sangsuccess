package com.green.sang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.sang.dto.Artist;
import com.green.sang.dto.PagingBean;
import com.green.sang.service.ArtistService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ArtistController {
	@Autowired
	private ArtistService ars;
	
	// 목록
	@GetMapping("/artist/artist_list")
	public void artist_list(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum, Model model
			,HttpSession session) {
		Object idObject = session.getAttribute("id");
		String id = "";
		
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);
			System.out.println("변환된 id = " + id);
			
		}else {
			id = (String) session.getAttribute("id");
			System.out.println("일반아이디 : " + id);	
		}
	
		int rowPerPage = 8;
		if (pageNum == null || pageNum.equals("")) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = ars.getTotal();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Artist> artist_list = ars.artist_list(startRow, endRow);

		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("artist_list", artist_list);
		model.addAttribute("id",id);
	}
	@GetMapping("/artist/artist_interview")
	public void artist_interview(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum, Model model
			,HttpSession session) {
		int rowPerPage = 8;
		if (pageNum == null || pageNum.equals("")) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = ars.getTotal_interview();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Artist> artist_interview = ars.artist_interview(startRow, endRow);
		
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

		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("artist_interview", artist_interview);
	}
	@GetMapping("/artist/artist_news")
	public void artist_news(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum, Model model
			,HttpSession session) {
		
		int rowPerPage = 8;
		if (pageNum == null || pageNum.equals("")) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = ars.getTotal_news();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		
		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Artist> artist_news = ars.artist_news(startRow, endRow);
		
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
		
		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("artist_news", artist_news);
	}
	
//	상세
	@GetMapping({"/artist/artist_list_detail",
		"/artist/artist_interview_detail",
		"/artist/artist_news_detail"})
	public void artist_list_detail (@RequestParam(value = "ar_no", defaultValue = "0") int ar_no, 
			Model model ,HttpSession session) {
		Artist artist = ars.select_d(ar_no);
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
		model.addAttribute("artist", artist);
	}
}
