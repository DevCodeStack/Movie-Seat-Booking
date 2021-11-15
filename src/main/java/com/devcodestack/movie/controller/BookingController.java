package com.devcodestack.movie.controller;

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

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
@Slf4j
public class BookingController {
	
	private RestTemplate restTemplate;
	
	@PostMapping
	public ResponseEntity<BookingStatus> bookMovieSeats(@RequestBody Booking booking) {
		log.info("Booking Data: {}", booking);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Booking> entity = new HttpEntity<Booking>(booking, headers);
		BookingStatus status = restTemplate.exchange("https://localhost:8500/cinemax/avail/update", HttpMethod.POST, entity, BookingStatus.class).getBody();
		return ResponseEntity.ok(status);
	}
}
