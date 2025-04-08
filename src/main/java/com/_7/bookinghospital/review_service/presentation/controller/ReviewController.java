package com._7.bookinghospital.review_service.presentation.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._7.bookinghospital.review_service.application.response.GetReviewResponseDto;
import com._7.bookinghospital.review_service.application.response.ReviewResponseDto;
import com._7.bookinghospital.review_service.presentation.request.ReviewRequestDto;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

	@PostMapping()
	public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto request) {

		return null;
	}

	@GetMapping("/{hospitalId}")
	public List<ResponseEntity<GetReviewResponseDto>> getHospitalReviews(@PathVariable UUID hospitalId) {

		return null;
	}

	@GetMapping("/{hospitalId}/{reviewId}")
	public ResponseEntity<GetReviewResponseDto> getReview(@PathVariable UUID hospitalId, @PathVariable UUID reviewId) {

		return null;
	}

	@PutMapping("/{reviewId}")
	public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable UUID reviewId, @RequestBody ReviewRequestDto request) {

		return null;
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<ReviewResponseDto> deleteReview(@PathVariable UUID reviewId) {

		return null;
	}
}
