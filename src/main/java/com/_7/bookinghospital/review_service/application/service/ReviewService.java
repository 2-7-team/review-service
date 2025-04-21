package com._7.bookinghospital.review_service.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	// todo try-catch문 반복되는 부분 유틸화 고려 필요.

	private final ReviewRepository reviewRepository;
	private final HospitalClient hospitalClient;

	@Transactional
	public ReviewResponseDto createReview(ReviewRequestDto request, Long userId) {
		Review review = Review.create(request, userId);
		reviewRepository.save(review);

		return ReviewResponseDto.from(review);
	}

	public List<ReviewResponseDto> getHospitalReviews(UUID hospitalId, SearchRequestDto request) {
		try {
			HospitalCheckResponse checkedHospitalId = hospitalClient.getHospitalId(hospitalId);

			PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());
			Page<Review> reviews = reviewRepository.searchByHospitalIdAndKeyword(
				checkedHospitalId.getHospitalId(),
				request.getKeyword(),
				pageRequest
			);
			return reviews.stream().map(ReviewResponseDto::from).toList();

		} catch (FeignException.NotFound e) {
			// todo. 커스텀 예외로 수정 예정
			throw new IllegalArgumentException("해당 병원은 존재하지 않습니다.");
		}

	}

	public ReviewResponseDto getReview(UUID hospitalId, UUID reviewId) {
		try {
			hospitalClient.getHospitalId(hospitalId);

			Review review = reviewRepository.findById(reviewId);

			return ReviewResponseDto.from(review);

		} catch (FeignException.NotFound e) {
			// todo. 커스텀 예외로 수정 예정
			throw new IllegalArgumentException("해당 병원은 존재하지 않습니다.");
		}
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
		try {
			return reviewRepository.countByHospitalId(hospitalId);

		} catch (FeignException.NotFound e) {
			// todo. 커스텀 예외로 수정 예정
			throw new IllegalArgumentException("해당 병원은 존재하지 않습니다.");
		}
	}

	public Float getRating(UUID hospitalId) {
		try {
			Float avgRating = reviewRepository.findAvgRatingByHospitalId(hospitalId);

			return avgRating == null ? 0.0f : avgRating;

		} catch (FeignException.NotFound e) {
			// todo. 커스텀 예외로 수정 예정
			throw new IllegalArgumentException("해당 병원은 존재하지 않습니다.");
		}
	}
}
