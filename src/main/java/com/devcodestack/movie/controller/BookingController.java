package com.devcodestack.movie.controller;

import javax.mail.MessagingException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.devcodestack.movie.model.Booking;
import com.devcodestack.movie.model.BookingStatus;
import com.devcodestack.movie.util.MailService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
@Slf4j
public class BookingController {
	
	private RestTemplate restTemplate;
	
	private MailService mailService;
	
	@PostMapping
	public ResponseEntity<BookingStatus> bookMovieSeats(@RequestBody Booking booking) {
		log.info("Booking Data: {}", booking);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Booking> entity = new HttpEntity<Booking>(booking, headers);
		BookingStatus status = restTemplate.exchange("http://movie-seat-availability/cinemax/avail/update", HttpMethod.POST, entity, BookingStatus.class).getBody();
		try {
			mailService.sendMail(status);
		} catch (MessagingException e) {
			log.info("Mail couldn't be sent due to error: {}", e.getMessage());
		}
		return ResponseEntity.ok(status);
	}
}
