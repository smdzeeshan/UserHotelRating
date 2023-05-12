package com.mac.RatingService.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mac.RatingService.entities.Rating;
import com.mac.RatingService.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingService service;
	
	@PostMapping("")
	public ResponseEntity<Rating> createRating(@RequestBody Rating myRating){
		Rating rating = service.create(myRating);
		return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<List<Rating>> getRatings(){
		List<Rating> rating = service.getRatings();
		return new ResponseEntity<List<Rating>>(rating, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
		List<Rating> rating = service.getRatingByUserId(userId);
		return new ResponseEntity<List<Rating>>(rating, HttpStatus.OK);
	}
	
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId){
		List<Rating> rating = service.getRatingByHotelId(hotelId);
		return new ResponseEntity<List<Rating>>(rating, HttpStatus.OK);
	}
}
