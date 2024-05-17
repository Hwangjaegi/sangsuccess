package com.green.sang.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

	void sendEmail(SimpleMailMessage message);
	
}
