package com.movie.service;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movie.client.MovieClient;
import com.movie.dto.Movies;

import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class MovieService {

	@Inject
	private MovieClient movieClient;

	@Inject
	private XmlMapper mapper;

	public Optional<String> findMovies(final String title) {
		log.info("Buscando filmes para o titulo: {}", title);
		final Movies movies = movieClient.findMovies(title);
		return Optional.ofNullable(moviesToXml(movies));
	}
	
	private String moviesToXml(final Movies movies) {
		try {
			return mapper.writeValueAsString(movies);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

}
