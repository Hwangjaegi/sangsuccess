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

import com.green.sang.dto.Notice;
import com.green.sang.dto.PagingBean;
import com.green.sang.service.NoticeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService ns;

//	공지 목록
	@GetMapping("/notice/notice_list")
	public void notice_list(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
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
		int total = ns.getTotal();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Notice> notice_list = ns.notice_list(startRow, endRow);

		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("notice_list", notice_list);
		model.addAttribute("id",id);
	}

	@GetMapping("/notice/notice_event")
	public void notice_event(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
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
		int total = ns.getTotal_event();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		
		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Notice> notice_event = ns.notice_event(startRow, endRow);
		
		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("notice_event", notice_event);
		model.addAttribute("id",id);
	}

	@GetMapping("/notice/notice_noti")
	public void notice_noti(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
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
		int total = ns.getTotal_noti();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		
		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		List<Notice> notice_noti = ns.notice_noti(startRow, endRow);
		
		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("notice_noti", notice_noti);
		model.addAttribute("id",id);
	}
	
//	상세
	@GetMapping("/notice/notice_list_detail")
	public void notice_list_detail (@RequestParam(value = "nt_no", defaultValue = "0") int nt_no,
			HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		Notice notice = ns.select_d(nt_no);
		Notice notice_leg_list = ns.notice_leg_list(notice);
		Notice notice_lead_list = ns.notice_lead_list(notice);

		model.addAttribute("id",id);
		model.addAttribute("notice", notice);
		model.addAttribute("notice_leg_list", notice_leg_list);
		model.addAttribute("notice_lead_list", notice_lead_list);
	}
	
	@GetMapping({"/notice/notice_event_detail", 
	"/notice/notice_noti_detail"})
	public void notice_event_detail (@RequestParam(value = "nt_no", defaultValue = "0") int nt_no,
			HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		Notice notice = ns.select_d(nt_no);
		Notice notice_leg = ns.notice_leg(notice);
		Notice notice_lead = ns.notice_lead(notice);
		
		model.addAttribute("id",id);
		model.addAttribute("notice", notice);
		model.addAttribute("notice_leg", notice_leg);
		model.addAttribute("notice_lead", notice_lead);
	}
	
//	공지 입력
	@GetMapping("/notice/notice_insertForm")
	public void notice_insertForm (HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		model.addAttribute("id", id);
	}
	
	@PostMapping("/notice/notice_insert")
	public void notice_insert(@RequestParam("file") MultipartFile file,
	        Notice notice, Model model, HttpSession session) throws IOException {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
	    int result = 0;
	    Notice notice2 = ns.select(notice.getNt_title());
	    if (notice2 == null) {
			String fileName1 = notice.getFile().getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + fileName1.substring(fileName1.lastIndexOf("."));
			notice.setNt_image(fileName);
			String imgSaveUrl = "src/main/resources/static/images/notice";
			FileOutputStream fos = new FileOutputStream(new File(imgSaveUrl + "/" + fileName));
			fos.write(notice.getFile().getBytes());
			fos.close();
	            
	        notice.setId(id);
	        result = ns.insert(notice);
	    } else {
	        result = -1;
	    }
	    model.addAttribute("id", id);
	    model.addAttribute("result", result);
	}
	
//	공지 수정
	@GetMapping("/notice/notice_updateForm")
	public void notice_updateForm(@RequestParam("nt_no") int nt_no,
			HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		Notice notice = ns.select_d(nt_no);
		
		model.addAttribute("notice", notice);
		model.addAttribute("id", id);
	}
	
	@PostMapping("/notice/notice_update")
	public void notice_update(@RequestParam("file") MultipartFile file,
			@RequestParam("nt_no") int nt_no,
	        Notice notice, Model model, HttpSession session) throws IOException {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		int result = 0;
		Notice notice2 = ns.select_d(notice.getNt_no());
		
		if (notice2 != null) {
			if (notice.getFile() == null || notice.getFile().isEmpty()) {
				notice.setNt_image(notice2.getNt_image());
			} else  {
				String fileName1 = notice.getFile().getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				String fileName = uuid + fileName1.substring(fileName1.lastIndexOf("."));
				notice.setNt_image(fileName);
				String imgSaveUrl = "src/main/resources/static/images/notice";
				FileOutputStream fos = new FileOutputStream(new File(imgSaveUrl + "/" + fileName));
				fos.write(notice.getFile().getBytes());
				fos.close();
			}
	    }

	    notice.setId(id);
	    result = ns.update(notice);
	    
	    model.addAttribute("id", id);
	    model.addAttribute("result", result);
	    model.addAttribute("notice", notice);
	}
	
//	공지 삭제
	@GetMapping("/notice/notice_delete")
	public void notice_delete (@RequestParam("nt_no") int nt_no, 
			HttpSession session, Model model) {
		Object idObject = session.getAttribute("id");
		String id = "";
		if(idObject instanceof Long) {
			id = String.valueOf(idObject);	
		}else {
			id = (String) session.getAttribute("id");
		}
		
		Notice notice = ns.select_d(nt_no);
		int result = ns.delete(nt_no);
		
		model.addAttribute("id", id);
		model.addAttribute("notice", notice);
		model.addAttribute("result", result);
	}
	
}