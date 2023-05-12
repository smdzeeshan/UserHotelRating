package com.mac.RatingService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mac.RatingService.entities.Rating;

public interface RatingRepo extends JpaRepository<Rating, String>{
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);
}
