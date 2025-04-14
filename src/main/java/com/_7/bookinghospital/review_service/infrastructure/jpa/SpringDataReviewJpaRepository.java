package com._7.bookinghospital.review_service.infrastructure.jpa;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com._7.bookinghospital.review_service.domain.model.Review;

public interface SpringDataReviewJpaRepository extends JpaRepository<Review, UUID> {

	@Query("SELECT r FROM Review r WHERE r.hospitalId = :hospitalId AND " +
		"(LOWER(r.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		" LOWER(r.content) LIKE LOWER(CONCAT('%', :keyword, '%')))")
	Page<Review> searchByHospitalIdAndKeyword(UUID hospitalId, String keyword, Pageable pageable);
}
