package com.movie.module;

import java.io.IOException;
import java.net.ServerSocket;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.inject.AbstractModule;
import com.movie.server.Server;
import com.movie.server.impl.MovieServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneralModule extends AbstractModule {

	private static final int PORT = 6666;

	@Override
	protected void configure() {
		bind(ObjectMapper.class).toInstance(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
		bind(XmlMapper.class);
		bind(ServerSocket.class).toInstance(buildServerSocket());
		bind(Server.class).to(MovieServer.class);
	}

	private ServerSocket buildServerSocket() {
		try {
			return new ServerSocket(PORT);
		} catch (IOException e) {
			log.error("Erro ao iniciar servidor | erro={}", e.getMessage(), e);
			throw new RuntimeException("Erro ao iniciar servidor", e);
		}
	}

}
