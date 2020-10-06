package com.movie.controller.impl;

import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movie.controller.DefaultController;
import com.movie.controller.MovieController;
import com.movie.dto.Input;
import com.movie.exception.InternalServerErroException;
import com.movie.service.MovieService;

@Singleton
public class MovieControllerV1 extends DefaultController implements MovieController {

	@Inject
	private MovieService movieService;

	@Override
	public String findMovies(final String xml) {
		final Input title = this.fromXml(xml, Input.class);

		return Optional.of(movieService.findMovies(title))
				.map(this::toXml)
				.orElseThrow(InternalServerErroException::new);
	}

}
