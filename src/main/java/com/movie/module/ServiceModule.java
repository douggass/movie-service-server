package com.movie.module;

import com.google.inject.AbstractModule;
import com.movie.service.MovieService;

public class ServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(MovieService.class);
	}

}
