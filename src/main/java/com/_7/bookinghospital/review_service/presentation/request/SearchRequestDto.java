package com._7.bookinghospital.review_service.presentation.request;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequestDto {

	private String keyword = "";

	private int page = 0;

	private int size = 10;

	private Sort.Direction direction = Sort.Direction.ASC;

	private String sortBy = "createdAt";
}
