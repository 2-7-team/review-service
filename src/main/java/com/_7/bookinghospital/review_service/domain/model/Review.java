package com._7.bookinghospital.review_service.domain.model;

import java.util.UUID;

import com._7.bookinghospital.review_service.presentation.request.ReviewRequestDto;
import com._7.bookinghospital.review_service.presentation.request.ReviewUpdateRequestDto;

import bookinghospital.common_module.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_review")
public class Review extends BaseEntity {

	@Builder
	public Review(Long userId, UUID reservationId, UUID hospitalId, String title, String content, Integer rating) {
		this.userId = userId;
		this.reservationId = reservationId;
		this.hospitalId = hospitalId;
		this.title = title;
		this.content = content;
		this.rating = rating;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private Long userId;

	@Column(nullable = false)
	private UUID reservationId;

	@Column(nullable = false)
	private UUID hospitalId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private Integer rating;

	public static Review create(ReviewRequestDto request, Long userId) {
		return Review.builder()
			.userId(userId)
			.reservationId(request.getReservationId())
			.hospitalId(request.getHospitalId())
			.title(request.getTitle())
			.content(request.getContent())
			.rating(request.getRating())
			.build();
	}

	public void update(ReviewUpdateRequestDto request) {
		this.title = request.getTitle();
		this.content = request.getContent();
		this.rating = request.getRating();
	}
}
