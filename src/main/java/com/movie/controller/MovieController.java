package com.movie.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movie.service.MovieService;

@Singleton
public class MovieController implements Controller {

	@Inject private MovieService movieService;

	public String findMovies(final String title) {
		return movieService
				.findMovies(title)
				.orElse("");
	}
	
}
