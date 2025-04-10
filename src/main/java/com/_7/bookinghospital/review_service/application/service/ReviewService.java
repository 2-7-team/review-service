package com._7.bookinghospital.review_service.application.service;

import java.util.UUID;

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

	public ReviewResponseDto getReview(UUID hospitalId, UUID reviewId) {
		// todo. hospitalID가 실제 hospital 서비스에 존재하는지 확인하는 로직 구현

		Review review = reviewRepository.findById(reviewId);

		return ReviewResponseDto.from(review);
	}
}
