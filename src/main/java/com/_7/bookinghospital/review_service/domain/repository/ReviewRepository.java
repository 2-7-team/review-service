package com._7.bookinghospital.review_service.domain.repository;

import java.util.UUID;

import com._7.bookinghospital.review_service.domain.model.Review;

public interface ReviewRepository {
	void save(Review review);

	Review findById(UUID reviewId);
}
