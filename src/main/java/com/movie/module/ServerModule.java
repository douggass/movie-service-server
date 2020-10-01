package com.movie.module;

import static com.movie.properties.PropertiesLoader.getValues;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;

import com.google.inject.AbstractModule;
import com.movie.server.Server;
import com.movie.server.impl.MovieServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerModule extends AbstractModule {

	private static final int DEFAULT_PORT = 8080;

	@Override
	protected void configure() {
		bind(Server.class).to(MovieServer.class);
		bind(ServerSocket.class).toInstance(buildServerSocket());
	}

	private ServerSocket buildServerSocket() {
		try {
			return new ServerSocket(getPort());
		} catch (IOException e) {
			log.error("Erro ao iniciar servidor | erro={}", e.getMessage(), e);
			throw new RuntimeException("Erro ao iniciar servidor", e);
		}
	}

	public static int getPort() {
		return  Optional.ofNullable(getValues("server.port"))
					.map(Integer::valueOf)
					.orElse(DEFAULT_PORT);
	}

}
