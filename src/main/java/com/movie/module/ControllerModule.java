package com.movie.module;

import com.google.inject.AbstractModule;
import com.movie.controller.MovieController;
import com.movie.controller.impl.MovieControllerV1;

public class ControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(MovieController.class).to(MovieControllerV1.class);
	}

}
