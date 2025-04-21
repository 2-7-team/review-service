package com._7.bookinghospital.review_service.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com._7.bookinghospital.review_service.exception.NotExistHospitalException;
import com._7.bookinghospital.review_service.infrastructure.client.HospitalClient;
import com._7.bookinghospital.review_service.infrastructure.dto.HospitalCheckResponse;
import com._7.bookinghospital.review_service.presentation.request.SearchRequestDto;
import com._7.bookinghospital.review_service.application.response.ReviewResponseDto;
import com._7.bookinghospital.review_service.domain.model.Review;
import com._7.bookinghospital.review_service.domain.repository.ReviewRepository;
import com._7.bookinghospital.review_service.presentation.request.ReviewRequestDto;
import com._7.bookinghospital.review_service.presentation.request.ReviewUpdateRequestDto;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final HospitalClient hospitalClient;

	@Transactional
	public ReviewResponseDto createReview(ReviewRequestDto request, Long userId) {
		Review review = Review.create(request, userId);
		reviewRepository.save(review);

		return ReviewResponseDto.from(review);
	}

	public List<ReviewResponseDto> getHospitalReviews(UUID hospitalId, SearchRequestDto request) {
		validateHospitalExist(hospitalId);
		PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());
		Page<Review> reviews = reviewRepository.searchByHospitalIdAndKeyword(
			hospitalId,
			request.getKeyword(),
			pageRequest
		);

		return reviews.stream().map(ReviewResponseDto::from).toList();
	}

	public ReviewResponseDto getReview(UUID hospitalId, UUID reviewId) {
		validateHospitalExist(hospitalId);
		Review review = reviewRepository.findById(reviewId);

		return ReviewResponseDto.from(review);
	}

	public ReviewResponseDto getReviewById(UUID reviewId) {
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

	public Long countHospitalReviews(UUID hospitalId) {
		validateHospitalExist(hospitalId);

		return reviewRepository.countByHospitalId(hospitalId);
	}

	public Float getRating(UUID hospitalId) {
		validateHospitalExist(hospitalId);

		Float avgRating = reviewRepository.findAvgRatingByHospitalId(hospitalId);

		return avgRating == null ? 0.0f : avgRating;
	}

	// todo utils로 뺄지 아니면 Service에 놓을지 고민 필요
	private void validateHospitalExist(UUID hospitalId) {
		try {
			hospitalClient.getHospitalId(hospitalId);
		} catch (FeignException e) {
			throw new NotExistHospitalException("해당 병원은 존재하지 않습니다.");
		}
	}
}
