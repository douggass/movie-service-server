package com.movie.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Movies {

	@JsonProperty("d")
	private List<Movie> listOfMovies;
}
