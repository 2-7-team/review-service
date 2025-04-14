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

import com._7.bookinghospital.review_service.presentation.request.SearchRequestDto;
import com._7.bookinghospital.review_service.application.response.ReviewResponseDto;
import com._7.bookinghospital.review_service.application.service.ReviewService;
import com._7.bookinghospital.review_service.presentation.request.ReviewRequestDto;
import com._7.bookinghospital.review_service.presentation.request.ReviewUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	@PostMapping()
	public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto request) {
		ReviewResponseDto response = reviewService.createReview(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{hospitalId}")
	public ResponseEntity<List<ReviewResponseDto>> getHospitalReviews(@PathVariable UUID hospitalId,
		@ModelAttribute SearchRequestDto request) {
		List<ReviewResponseDto> reviews = reviewService.getHospitalReviews(hospitalId, request);

		return ResponseEntity.status(HttpStatus.OK).body(reviews);
	}

	@GetMapping("/{hospitalId}/{reviewId}")
	public ResponseEntity<ReviewResponseDto> getReview(@PathVariable UUID hospitalId, @PathVariable UUID reviewId) {
		ReviewResponseDto response = reviewService.getReview(hospitalId, reviewId);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PatchMapping("/{reviewId}")
	public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable UUID reviewId, @RequestBody ReviewUpdateRequestDto request) {
		ReviewResponseDto response = reviewService.updateReview(reviewId, request);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable UUID reviewId) {
		reviewService.deleteReview(reviewId);

		return ResponseEntity.ok("정상적으로 삭제 되었습니다.");
	}
}
