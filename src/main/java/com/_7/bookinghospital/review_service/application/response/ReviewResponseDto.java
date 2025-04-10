package com._7.bookinghospital.review_service.application.response;

import java.util.UUID;

import com._7.bookinghospital.review_service.domain.model.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponseDto {

	private Long userId;
	private UUID reservationId;
	private UUID hospitalId;
	private String title;
	private String content;
	private Integer rating;

	public static ReviewResponseDto from(Review review) {
		return ReviewResponseDto.builder()
			.userId(review.getUserId())
			.reservationId(review.getReservationId())
			.hospitalId(review.getHospitalId())
			.title(review.getTitle())
			.content(review.getContent())
			.rating(review.getRating())
			.build();
	}
}
