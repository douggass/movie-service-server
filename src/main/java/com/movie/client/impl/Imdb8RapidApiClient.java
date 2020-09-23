package com.movie.client.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.movie.client.MovieClient;
import com.movie.dto.Movies;

import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class Imdb8RapidApiClient implements MovieClient {

	private static final String URL_GET_TITLES = "https://imdb8.p.rapidapi.com/title/auto-complete";
	private static final String HOST = "imdb8.p.rapidapi.com";
	private static final String KEY = "313d2d156cmsh32b590f599487c1p16dd41jsn36f1bef2e1bd";

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
					.orElseThrow(RuntimeException::new);

		} catch (Exception e) {
			log.error("Erro ao buscar filmes", e);
			throw new RuntimeException();
		}
	}

	private GetRequest buildRequest(final String title) throws URISyntaxException {
		return Unirest.get(buildUri(title).toString()).header("x-rapidapi-host", HOST).header("x-rapidapi-key", KEY);
	}

	private URI buildUri(final String title) throws URISyntaxException {
		return new URIBuilder(URL_GET_TITLES).addParameter("q", title).build();
	}

	private Movies handleResponseMovies(final String movies) {
		try {
			return objectMapper.readValue(movies, Movies.class);
		} catch (JsonProcessingException e) {
			log.error("Erro ao realizar parse dos filmes", e);
			return null;
		}
	}

}
