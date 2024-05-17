package com.green.sang.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.green.sang.dto.PagingBean;
import com.green.sang.dto.Review;
import com.green.sang.service.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewController {
	@Autowired
	private ReviewService rs;

//	목록
	@GetMapping("/review/review_list")
	public void review_list(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
            HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		int rowPerPage = 12;
		if (pageNum == null || pageNum.equals("")) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = rs.getTotal();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Review> review_list = rs.review_list(startRow, endRow);

		model.addAttribute("id", id);
		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("review_list", review_list);
	}
	@GetMapping("/review/review_hobby")
	public void review_hobby(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
            HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		int rowPerPage = 12;
		if (pageNum == null || pageNum.equals("")) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = rs.getTotal_hobby();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Review> review_hobby = rs.review_hobby(startRow, endRow);
		
		model.addAttribute("id", id);
		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		
		model.addAttribute("review_hobby", review_hobby);
	}
	@GetMapping("/review/review_write")
	public void review_write(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
            HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		int rowPerPage = 12;
		if (pageNum == null || pageNum.equals("")) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = rs.getTotal_write();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Review> review_write = rs.review_write(startRow, endRow);
		
		model.addAttribute("id", id);
		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("review_write", review_write);
	}
	@GetMapping("/review/review_book")
	public void review_book(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
            HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		int rowPerPage = 12;
		if (pageNum == null || pageNum.equals("")) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = rs.getTotal_book();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Review> review_book = rs.review_book(startRow, endRow);
		
		model.addAttribute("id", id);
		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("review_book", review_book);
	}
	@GetMapping("/review/review_design")
	public void review_design(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
            HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		int rowPerPage = 12;
		if (pageNum == null || pageNum.equals("")) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = rs.getTotal_design();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Review> review_design = rs.review_design(startRow, endRow);
		
		model.addAttribute("id", id);
		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("review_design", review_design);
	}
	@GetMapping("/review/review_media")
	public void review_media(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
            HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		int rowPerPage = 12;
		if (pageNum == null || pageNum.equals("")) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = rs.getTotal_media();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Review> review_media = rs.review_media(startRow, endRow);
		
		model.addAttribute("id", id);
		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("review_media", review_media);
	}
	@GetMapping("/review/review_photo")
	public void review_photo(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
            HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		int rowPerPage = 12;
		if (pageNum == null || pageNum.equals("")) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int total = rs.getTotal_photo();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Review> review_photo = rs.review_photo(startRow, endRow);
		
		model.addAttribute("id", id);
		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("review_photo", review_photo);
	}
	
//	상세
	@GetMapping("/review/review_list_detail")
	public void review_list_detail (@RequestParam(value = "r_no", defaultValue = "0") int r_no,
			HttpSession session, Review revi, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		Review review = rs.select_d(r_no);
		Review review_leg_list = rs.review_leg_list(review);
		Review review_lead_list = rs.review_lead_list(review);

		model.addAttribute("id", id);
		model.addAttribute("review", review);
		model.addAttribute("review_leg_list", review_leg_list);
		model.addAttribute("review_lead_list", review_lead_list);
	}
	
	@GetMapping({"/review/review_hobby_detail", "/review/review_write_detail",
		"/review/review_book_detail", "/review/review_design_detail",
		"/review/review_media_detail", "/review/review_photo_detail"})
	public void review_hobby_detail (@RequestParam(value = "r_no", defaultValue = "0") int r_no,
			Review revi, HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		Review review = rs.select_d(r_no);
		Review review_leg = rs.review_leg(review);
		Review review_lead = rs.review_lead(review);

		model.addAttribute("id", id);
		model.addAttribute("review", review);
		model.addAttribute("review_leg", review_leg);
		model.addAttribute("review_lead", review_lead);
	}
	
//	입력
	@GetMapping("/review/review_insertForm")
	public void review_insertForm(HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		model.addAttribute("id", id);
	}
	
	@PostMapping("/review/review_insert")
	public void revice_insert (@RequestParam("file") MultipartFile file,
			Review review, Model model, HttpSession session) throws IOException {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		int result = 0;
		Review review2 = rs.select(review.getR_title());
		if (review2 == null) {
			String fileName1 = review.getFile().getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + fileName1.substring(fileName1.lastIndexOf("."));
			review.setR_image(fileName);
			String imgSaveUrl = "src/main/resources/static/images/review";
			FileOutputStream fos = new FileOutputStream(new File(imgSaveUrl + "/" + fileName));
			fos.write(review.getFile().getBytes());
			fos.close();
			
			review.setId(id);
			result = rs.insert(review);
		} else {
			result = -1;
		}
		model.addAttribute("id", id);
		model.addAttribute("result", result);
	}
	
//	수정
	@GetMapping("/review/review_updateForm")
	public void review_updateForm (@RequestParam("r_no") int r_no,
			HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		Review review = rs.select_d(r_no);		
		model.addAttribute("review", review);
		model.addAttribute("id", id);
	}
	
	@PostMapping("/review/review_update")
	public void revice_update (@RequestParam("file") MultipartFile file,
			@RequestParam("r_no") int r_no,
			Review review, Model model, HttpSession session) throws IOException {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		int result = 0;
		Review review2 = rs.select_d(review.getR_no());
		
		if (review2 != null) {
			if (review.getFile() == null || review.getFile().isEmpty()) {
				review.setR_image(review2.getR_image());
			} else {
				String fileName1 = review.getFile().getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				String fileName = uuid + fileName1.substring(fileName1.lastIndexOf("."));
				review.setR_image(fileName);
				String imgSaveUrl = "src/main/resources/static/images/review";
				FileOutputStream fos = new FileOutputStream(new File(imgSaveUrl + "/" + fileName));
				fos.write(review.getFile().getBytes());
				fos.close();
			}
		}
		
		review.setId(id);
		result = rs.update(review);
		
		model.addAttribute("id", id);
		model.addAttribute("result", result);
		model.addAttribute("review", review);
	}
	
//	삭제
	@GetMapping("/review/review_delete")
	public void review_delete (@RequestParam("r_no") int r_no,
			HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		Review review = rs.select_d(r_no);
		int result = rs.delete(r_no);

		model.addAttribute("id", id);
		model.addAttribute("review", review);
		model.addAttribute("result", result);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}