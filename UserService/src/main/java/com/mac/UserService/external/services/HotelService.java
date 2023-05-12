package com.mac.UserService.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mac.UserService.entities.Hotel;

@FeignClient (name = "HOTEL-SERVICE")
public interface HotelService {
	
	@GetMapping("/hotels/{hotelId}")
	Hotel getHotel(@PathVariable("hotelId") String hotelId );
}
