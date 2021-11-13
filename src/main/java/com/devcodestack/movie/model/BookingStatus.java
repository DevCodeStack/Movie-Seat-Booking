package com.devcodestack.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingStatus {
	
	private boolean bookingState = false;
	private String bookingMsg;
}
