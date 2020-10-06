package com.movie.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movie.client.MovieClient;
import com.movie.dto.Input;
import com.movie.dto.Movie;
import com.movie.dto.Movies;
import com.movie.dto.Output;
import com.movie.exception.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class MovieService {

	@VisibleForTesting
	static final String LOG_FIND_MOVIES = "Buscando filmes para o titulo: {}";

	@Inject
	private MovieClient movieClient;

	public Output findMovies(final Input input) {
		log.info(LOG_FIND_MOVIES, input.getQuery());
		try {
			final Movies movies = movieClient.findMovies(input.getQuery());
			final String payload = this.buildPayload(movies);
			return Output.builder()
					.payload(payload)
					.length(payload.length())
					.build();
		} catch (NotFoundException e) {
			return Output.builder().build();
		} catch (Exception e) {
			throw e;
		}

	}

	private String buildPayload(final Movies movies) {
		final List<String> listOfMovies = Optional.ofNullable(movies.getListOfMovies())
				.orElseGet(Collections::emptyList)
				.stream()
				.map(Movie::getName)
				.collect(Collectors.toList());
		return StringUtils.join(listOfMovies, "\n");
	}

}
