package com._7.bookinghospital.review_service.infrastructure;

import java.util.UUID;

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

	@Override
	public Review findById(UUID reviewId) {

		return reviewJpaRepository.findById(reviewId)
			.orElseThrow(() -> new IllegalArgumentException("해당 ID의 리뷰가 존재하지 않습니다."));
	}

	@Override
	public void deleteById(UUID reviewId) {
		reviewJpaRepository.deleteById(reviewId);
	}

}
