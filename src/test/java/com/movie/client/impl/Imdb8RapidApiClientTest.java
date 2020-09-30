package com.movie.client.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.movie.dto.Movies;
import com.movie.exception.HttpMovieClientException;

@RunWith(MockitoJUnitRunner.class)
public class Imdb8RapidApiClientTest {

	@InjectMocks
	private Imdb8RapidApiClient imdb8RapidApiClient;

	@Mock
	private ObjectMapper objectMapper;

	@Test
	public void findMoviesTest() throws UnirestException, URISyntaxException, JsonMappingException, JsonProcessingException {
		final String title = "Game of";
		final Movies movies = Movies.builder().build(); 
		when(objectMapper.readValue(anyString(), eq(Movies.class))).thenReturn(movies);
		Movies result = imdb8RapidApiClient.findMovies(title);
		assertNotNull(result);
		assertEquals(movies, result);
	}

	@Test(expected = HttpMovieClientException.class)
	public void findMoviesWhenNotExistTtitleTest() {
		imdb8RapidApiClient.findMovies("");
	}
}
