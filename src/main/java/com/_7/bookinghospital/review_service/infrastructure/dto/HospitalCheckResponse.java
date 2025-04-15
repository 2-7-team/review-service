package com._7.bookinghospital.review_service.infrastructure.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HospitalCheckResponse {

	private UUID hospitalId;
}
