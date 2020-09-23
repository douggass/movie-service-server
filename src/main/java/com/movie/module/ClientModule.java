package com.movie.module;

import com.google.inject.AbstractModule;
import com.movie.client.MovieClient;
import com.movie.client.impl.Imdb8RapidApiClient;

public class ClientModule extends AbstractModule {
	
    @Override
    protected void configure() {
          bind(MovieClient.class).to(Imdb8RapidApiClient.class);
    }

}
