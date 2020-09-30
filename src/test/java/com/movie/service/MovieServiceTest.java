package com.movie.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.movie.client.MovieClient;
import com.movie.dto.Input;
import com.movie.dto.Movie;
import com.movie.dto.Movies;
import com.movie.dto.Output;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

	private static final String ANY_MOVIE_TITLE = "Test";
	private static final List<Movie> ANY_MOVIES = Arrays.asList(
				Movie.builder().name("Test: 1").build(),
				Movie.builder().name("Test: 2").build()
			);
	private static final Input INPUT = Input.builder()
			.length(ANY_MOVIE_TITLE.length())
			.query(ANY_MOVIE_TITLE)
			.build();

	@InjectMocks
	private MovieService movieService;

	@Mock
	private MovieClient movieClient;

	@Test
	public void findMoviesTest() {
		final Movies movies = Movies.builder()
				.listOfMovies(ANY_MOVIES)
				.build();
		
		when(movieClient.findMovies(ANY_MOVIE_TITLE)).thenReturn(movies);
		
		final Output result = movieService.findMovies(INPUT);
		
		final String payload = StringUtils.join(ANY_MOVIES.stream().map(Movie::getName).collect(Collectors.toList()), "\n");
		final Output expected = Output.builder()
				.payload(payload)
				.length(payload.length())
				.build();

		assertEquals(expected, result);
		verify(movieClient, times(1)).findMovies(ANY_MOVIE_TITLE);
	}
	
	@Test
	public void findMoviesWhenClientReturnEmptyListTest() {
		final Movies movies = Movies.builder()
				.listOfMovies(Collections.emptyList())
				.build();

		when(movieClient.findMovies(ANY_MOVIE_TITLE)).thenReturn(movies);

		final Output result = movieService.findMovies(INPUT);

		final Output expected = Output.builder()
				.payload(StringUtils.EMPTY)
				.length(0)
				.build();

		assertEquals(expected, result);
		verify(movieClient, times(1)).findMovies(ANY_MOVIE_TITLE);
	}

}
