package com.mac.UserService.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mac.UserService.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	
	@PostMapping("/ratings")
	Rating createRating(@RequestBody Rating rating);
	
	@PutMapping("/ratings/{ratingId}")
	Rating updateRating(@PathVariable ("ratingId") String ratingId, @RequestBody Rating rating);
	
	@DeleteMapping("/ratings/{ratingId}")
	void deleteRating(@PathVariable String ratingId);
}
