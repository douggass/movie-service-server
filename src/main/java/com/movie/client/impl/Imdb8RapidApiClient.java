package com.movie.client.impl;

import static com.movie.properties.PropertiesLoader.getValues;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.movie.client.MovieClient;
import com.movie.dto.Movies;
import com.movie.exception.HttpMovieClientException;
import com.movie.exception.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class Imdb8RapidApiClient implements MovieClient {

	private static final String FIND_MOVIES_ERROR = "Erro ao buscar filmes";
	private static final String PARSE_ERROR = "Erro ao realizar parse para o objeto filmes";
	private static final String LOG_FIND_MOVIES_NOT_FOUND = "Não foram encontrados filmes para o titulo {}";
	
	@VisibleForTesting
	static final String URL_GET_TITLES = getValues("client.rapidapi.url.find-title");

	@VisibleForTesting
	static final String HOST = getValues("client.rapidapi.header.host");

	@VisibleForTesting
	static final String KEY = getValues("client.rapidapi.header.key");

	@Inject
	private ObjectMapper objectMapper;

	@Override
	public Movies findMovies(final String title) {
		try {
			HttpResponse<String> response = buildRequest(title).asString();

			return Optional.ofNullable(response)
					.filter(item -> item.getStatus() == HttpStatus.SC_OK)
					.map(HttpResponse::getBody)
					.map(this::handleResponseMovies)
					.orElseThrow(NotFoundException::new);
		} catch (NotFoundException e) {
			log.error(LOG_FIND_MOVIES_NOT_FOUND, title, e);
			return Movies.builder().build();
		} catch (UnirestException | URISyntaxException e) {
			log.error(FIND_MOVIES_ERROR, e);
			throw new HttpMovieClientException(FIND_MOVIES_ERROR, e);
		}
	}

	private GetRequest buildRequest(final String title) throws URISyntaxException {
		return Unirest.get(buildUri(title).toString())
				.header("x-rapidapi-host", HOST)
				.header("x-rapidapi-key", KEY);
	}

	private URI buildUri(final String title) throws URISyntaxException {
		return new URIBuilder(URL_GET_TITLES).addParameter("q", title).build();
	}

	private Movies handleResponseMovies(final String movies) {
		try {
			return objectMapper.readValue(movies, Movies.class);
		} catch (JsonProcessingException e) {
			log.error(PARSE_ERROR, e);
			return null;
		}
	}

}
