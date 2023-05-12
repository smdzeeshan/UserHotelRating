package com.mac.RatingService.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mac.RatingService.entities.Rating;
import com.mac.RatingService.repository.RatingRepo;
import com.mac.RatingService.services.RatingService;
@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingRepo repo;
	
	@Override
	public Rating create(Rating rating) {
		rating.setRatingId(UUID.randomUUID().toString());
		return repo.save(rating);
		
	}

	@Override
	public List<Rating> getRatings() {
		return repo.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return repo.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return repo.findByHotelId(hotelId);
	}

}
