package com.devcodestack.movie.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.devcodestack.movie.model.BookingStatus;
import com.devcodestack.movie.model.MailModel;

import lombok.extern.slf4j.Slf4j;

@Service
@EnableConfigurationProperties(MailModel.class)
@Slf4j
public class MailService {
	
	@Autowired
	private MailModel mailModel;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(BookingStatus status) throws MessagingException {
		
		if(mailModel.isEnabled()) {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			
			helper.setFrom(mailModel.getFrom());
			helper.setTo(mailModel.getTo());
			helper.setCc(mailModel.getCc());
			helper.setBcc(mailModel.getBcc());
			helper.setSubject("Booking status");
			helper.setText(status.getBookingMsg());
			
			mailSender.send(message);
			log.info("Mail sent...");
		}
	}

}
