package com._7.bookinghospital.review_service.domain.repository;

import com._7.bookinghospital.review_service.domain.model.Review;

public interface ReviewRepository {
	void save(Review review);
}
