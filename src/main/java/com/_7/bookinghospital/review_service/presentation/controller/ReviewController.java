package com._7.bookinghospital.review_service.presentation.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._7.bookinghospital.review_service.exception.NotReviewOwnerException;
import com._7.bookinghospital.review_service.presentation.request.SearchRequestDto;
import com._7.bookinghospital.review_service.application.response.ReviewResponseDto;
import com._7.bookinghospital.review_service.application.service.ReviewService;
import com._7.bookinghospital.review_service.presentation.request.ReviewRequestDto;
import com._7.bookinghospital.review_service.presentation.request.ReviewUpdateRequestDto;

import bookinghospital.common_module.userInfo.UserDetails;
import bookinghospital.common_module.userInfo.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "리뷰컨트롤러!!!!!")
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	@PostMapping()
	public ResponseEntity<ReviewResponseDto> createReview(
		@RequestBody ReviewRequestDto request,
		@UserInfo UserDetails userDetails) {
		// todo 예약이 끝난 유저만 리뷰를 생성할 수 있는 로직 추가 예정
		Long userId = userDetails.getUserId();
		ReviewResponseDto response = reviewService.createReview(request, userId);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{hospitalId}")
	public ResponseEntity<List<ReviewResponseDto>> getHospitalReviews(
		@PathVariable UUID hospitalId,
		@ModelAttribute SearchRequestDto request,
		@UserInfo UserDetails user) {
		// todo 로그인한 유저만 볼 수 있게 설정?
		List<ReviewResponseDto> reviews = reviewService.getHospitalReviews(hospitalId, request);

		return ResponseEntity.status(HttpStatus.OK).body(reviews);
	}

	@GetMapping("/{hospitalId}/{reviewId}")
	public ResponseEntity<ReviewResponseDto> getReview(
		@PathVariable UUID hospitalId,
		@PathVariable UUID reviewId,
		@UserInfo UserDetails user) {
		ReviewResponseDto response = reviewService.getReview(hospitalId, reviewId);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PatchMapping("/{reviewId}")
	public ResponseEntity<ReviewResponseDto> updateReview(
		@PathVariable UUID reviewId,
		@RequestBody ReviewUpdateRequestDto request,
		@UserInfo UserDetails user) {
		validateReviewOwnerOrThrow(reviewId, user, "본인의 리뷰만 수정할 수 있습니다.");

		ReviewResponseDto response = reviewService.updateReview(reviewId, request);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable UUID reviewId, @UserInfo UserDetails user) {
		validateReviewOwnerOrThrow(reviewId, user, "본인의 리뷰만 삭제할 수 있습니다.");

		reviewService.deleteReview(reviewId);

		return ResponseEntity.ok("정상적으로 삭제 되었습니다.");
	}

	@GetMapping("/{hospitalId}/count")
	public ResponseEntity<Long> countHospitalReviews(@PathVariable UUID hospitalId) {
		Long reviewCount = reviewService.countHospitalReviews(hospitalId);

		return ResponseEntity.status(HttpStatus.OK).body(reviewCount);
	}

	@GetMapping("/{hospitalId}/rating")
	public ResponseEntity<Float> getRating(@PathVariable UUID hospitalId) {
		Float rating = reviewService.getRating(hospitalId);

		return ResponseEntity.status(HttpStatus.OK).body(rating);
	}

	// todo utils로 뺄지 아니면 Controller에 놓을지 고민 필요
	private void validateReviewOwnerOrThrow(UUID reviewId, UserDetails user, String message) {
		ReviewResponseDto review = reviewService.getReviewById(reviewId);

		if (!user.getUserId().equals(review.getUserId())) {
			throw new NotReviewOwnerException(message);
		}
	}
}
