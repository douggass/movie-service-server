package com.movie.client;

import com.movie.dto.Movies;

public interface MovieClient {

	public Movies findMovies(String title);

}
