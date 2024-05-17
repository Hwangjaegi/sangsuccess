package com.green.sang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.sang.dto.Member;
import com.green.sang.service.MemberService;

import jakarta.mail.internet.MimeMessage;

@RestController
public class FindController {
	@Autowired
	private MemberService ms;
	
	@Autowired
	private JavaMailSender jms; // 메일전송
	
	@Autowired
	private BCryptPasswordEncoder bpe; //암호화
	
	@PostMapping("checkEmail")
	public Member checkEmail(Member member) throws NotFoundException {
		
		Member member2 = ms.selectEmail(member.getEmail());
		
		if(member2 == null) return null;
		else return member2;
	}
	
	@PostMapping("sendEmail")	//아이디찾기,비밀번호찾기 두가지경우존재
	public int sendEmail(Member member) {
		System.out.println("들어온 메일값은 : " + member.getEmail());
		System.out.println("들어온 아이디는 : " + member.getId()); // 아이디찾기경우 null이 들어감
		int result = 0;
		MimeMessage mm = jms.createMimeMessage(); // 메일발송위한 객체
			
		if(member.getId() != null) {
			Member member2 = ms.select_PW_Email(member.getEmail(),member.getId()); // 입력한 email값으로 DB조회
			String randomPass = UUID.randomUUID().toString().substring(0,8); //랜덤 8자 패스워드발행
			String setPass = bpe.encode(randomPass); //랜덤8자 패스워드 암호화
			member2.setPassword(setPass); //암호화 패스워드 dto저장
			
			ms.updatePass(member2); // 암호화 패스워드를 DB에서 수정
			try {
				MimeMessageHelper mmh = new MimeMessageHelper(mm,true,"utf-8");	
				mmh.setSubject("임시 비밀번호를 전송해 드립니다 =>"+randomPass+"[상상마당 드림]");
				mmh.setText("임시 비밀번호는 => "+randomPass+" 입니다 감사합니다.");
				mmh.setTo(member.getEmail());
				mmh.setFrom("hanmeansuh@naver.com"); // 본인 naver 이메일 , 보낸이[application.properties에서 id,pass 입력]
				jms.send(mm);
				result = 1;
				
			} catch (Exception e) {
				System.out.println("메일전송중 에러가 발생하였습니다"+e.getMessage());
				result = -1;
			}
		}else if(member.getId() == null) {
			Member member3 = ms.selectEmail(member.getEmail());// 입력한 email값으로 DB조회
			System.out.println("찾아온정보 : " +member3.getId());
			String randomPass = UUID.randomUUID().toString().substring(0,8); //랜덤 8자 패스워드발행
			String setPass = bpe.encode(randomPass); //랜덤8자 패스워드 암호화
			member3.setPassword(setPass); //암호화 패스워드 dto저장
			
			ms.updatePass(member3); // 암호화 패스워드를 DB에서 수정
			try {
				MimeMessageHelper mmh = new MimeMessageHelper(mm,true,"utf-8");	
				mmh.setSubject("임시 비밀번호를 전송해 드립니다 =>"+randomPass+"[상상마당 드림]");
				mmh.setText("임시 비밀번호는 => "+randomPass+" 입니다 감사합니다.");
				mmh.setTo(member.getEmail());
				mmh.setFrom("hanmeansuh@naver.com"); // 본인 naver 이메일 , 보낸이[application.properties에서 id,pass 입력]
				jms.send(mm);
				result = 1;
				
			} catch (Exception e) {
				System.out.println("메일전송중 에러가 발생하였습니다"+e.getMessage());
				result = -1;
			}
		}
		
		return result;
	}
	
	@PostMapping("checkNameToTel")
	public List<Member> checkNameToTel(Member member) {
		System.out.println("들어온 이름 : " +member.getName());
		System.out.println("들어온 번호 : " +member.getTel());
		Map<String, Object> map = new HashMap<>();
		map.put("name", member.getName());
		map.put("tel", member.getTel());
		
		List<Member> member2 = ms.selectNameToTel(map);
		
		return member2;
	}
	
	@PostMapping("find_PW")
	public Member find_PW(Member member) {
		Member member2 = ms.select(member.getId());

		if(member2 == null) return null;
		else return member2;
	}
	
}
