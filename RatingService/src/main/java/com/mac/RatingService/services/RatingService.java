package com.mac.RatingService.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mac.RatingService.entities.Rating;

@Service
public interface RatingService {
	Rating create(Rating rating);
	List<Rating> getRatings();
	List<Rating> getRatingByUserId(String userId);
	List<Rating> getRatingByHotelId(String hotelId);
}
