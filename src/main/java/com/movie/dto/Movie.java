package com.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Movie {

	@JsonProperty("l")
	private String name;
}
