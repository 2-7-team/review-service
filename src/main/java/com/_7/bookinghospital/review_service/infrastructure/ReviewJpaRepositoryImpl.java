package com._7.bookinghospital.review_service.infrastructure;

import org.springframework.stereotype.Repository;

import com._7.bookinghospital.review_service.domain.model.Review;
import com._7.bookinghospital.review_service.domain.repository.ReviewRepository;
import com._7.bookinghospital.review_service.infrastructure.jpa.SpringDataReviewJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReviewJpaRepositoryImpl implements ReviewRepository {

	private final SpringDataReviewJpaRepository reviewJpaRepository;

	@Override
	public void save(Review review) {
		reviewJpaRepository.save(review);
	}
}
