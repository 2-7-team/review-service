package com._7.bookinghospital.review_service.domain.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com._7.bookinghospital.review_service.domain.model.Review;

public interface ReviewRepository {
	void save(Review review);

	Review findById(UUID reviewId);

	void deleteById(UUID reviewId);

	Page<Review> searchByHospitalIdAndKeyword(UUID hospitalId, String keyword, PageRequest pageRequest);

	Long countByHospitalId(UUID hospitalId);

	Float findAvgRatingByHospitalId(UUID hospitalId);
}
