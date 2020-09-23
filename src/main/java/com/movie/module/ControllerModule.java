package com.movie.module;

import com.google.inject.AbstractModule;
import com.movie.controller.MovieController;

public class ControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(MovieController.class);
	}

}
