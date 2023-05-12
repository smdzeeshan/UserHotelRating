package com.mac.UserService.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.mac.UserService.payload.ApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mac.UserService.entities.Hotel;
import com.mac.UserService.entities.Rating;
import com.mac.UserService.entities.User;
import com.mac.UserService.external.services.HotelService;
import com.mac.UserService.external.services.RatingService;
import com.mac.UserService.services.UserService;

import sun.print.resources.serviceui;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService service;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User myUser){
		User user = service.saveUser(myUser);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	//int retryCount = 1;

	@GetMapping("/{userId}")
	// @CircuitBreaker(name = "ratingHotelbreaker", fallbackMethod = "ratingHotelFallback")
	// @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "UserRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getUser(@PathVariable String userId){

		//System.out.println("Retry count : " + retryCount);
		//retryCount++;
		User user = service.getUser(userId);
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/" + userId , Rating[].class);
		List<Rating> ratings = Arrays.stream(ratingsOfUser).collect(Collectors.toList());
		
		List<Rating> ratingList = ratings.stream().map(rating -> {
			// ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		
		}).collect(Collectors.toList());

		user.setRatings(ratingList); 
		return ResponseEntity.ok(user);
	}

	public ResponseEntity<ApiResponse> ratingHotelFallback(String userId, Exception ex) {
		System.out.println("Fallback is executed because some service is down due to "  +ex.getMessage());

		ApiResponse apiResponse = new ApiResponse("API Limit reached ", false, HttpStatus.FORBIDDEN);
				
				
				
				

		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = service.getAllUsers();
		return ResponseEntity.ok().body(users);
	}
	
	@PostMapping("/ratings/{userId}")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating, @PathVariable String userId) {
		rating.setUserId(userId);
		return new ResponseEntity<Rating>(ratingService.createRating(rating), HttpStatus.CREATED);
	}
}