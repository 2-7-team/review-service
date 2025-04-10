package com._7.bookinghospital.review_service.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com._7.bookinghospital.review_service.application.response.ReviewResponseDto;
import com._7.bookinghospital.review_service.domain.model.Review;
import com._7.bookinghospital.review_service.domain.repository.ReviewRepository;
import com._7.bookinghospital.review_service.presentation.request.ReviewRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

	private final ReviewRepository reviewRepository;

	@Transactional
	public ReviewResponseDto createReview(ReviewRequestDto request) {
		Review review = Review.create(request);
		reviewRepository.save(review);

		return ReviewResponseDto.from(review);
	}
}
