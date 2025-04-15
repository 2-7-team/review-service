package com._7.bookinghospital.review_service.infrastructure.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com._7.bookinghospital.review_service.infrastructure.dto.HospitalCheckResponse;

@FeignClient(name = "hospital-service")
public interface HospitalClient {

	@GetMapping("/api/hospitals/internal/{hospitalId}")
	HospitalCheckResponse getHospitalId(@PathVariable UUID hospitalId);
}
