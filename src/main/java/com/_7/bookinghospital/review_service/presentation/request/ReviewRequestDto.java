package com._7.bookinghospital.review_service.presentation.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewRequestDto {

	private Long userId;
	private UUID reservationId;
	private String title;
	private String content;
	private Integer rating;
}
