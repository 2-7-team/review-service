package com._7.bookinghospital.review_service.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com._7.bookinghospital.review_service.presentation.request.SearchRequestDto;
import com._7.bookinghospital.review_service.application.response.ReviewResponseDto;
import com._7.bookinghospital.review_service.domain.model.Review;
import com._7.bookinghospital.review_service.domain.repository.ReviewRepository;
import com._7.bookinghospital.review_service.presentation.request.ReviewRequestDto;
import com._7.bookinghospital.review_service.presentation.request.ReviewUpdateRequestDto;

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

	public List<ReviewResponseDto> getHospitalReviews(UUID hospitalId, SearchRequestDto request) {
		// todo. hospitalID가 실제 hospital 서비스에 존재하는지 확인하는 로직 구현

		PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());

		Page<Review> reviews = reviewRepository.searchByHospitalIdAndKeyword(
			hospitalId,
			request.getKeyword(),
			pageRequest
		);

		return reviews.stream().map(ReviewResponseDto::from).toList();
	}

	public ReviewResponseDto getReview(UUID hospitalId, UUID reviewId) {
		// todo. hospitalID가 실제 hospital 서비스에 존재하는지 확인하는 로직 구현

		Review review = reviewRepository.findById(reviewId);

		return ReviewResponseDto.from(review);
	}

	@Transactional
	public ReviewResponseDto updateReview(UUID reviewId, ReviewUpdateRequestDto request) {
		Review review = reviewRepository.findById(reviewId);

		review.update(request);

		return ReviewResponseDto.from(review);
	}

	@Transactional
	public void deleteReview(UUID reviewId) {
		reviewRepository.deleteById(reviewId);
	}
}
