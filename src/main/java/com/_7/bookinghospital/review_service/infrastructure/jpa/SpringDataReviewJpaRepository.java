package com._7.bookinghospital.review_service.infrastructure.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com._7.bookinghospital.review_service.domain.model.Review;

public interface SpringDataReviewJpaRepository extends JpaRepository<Review, UUID> {
}
