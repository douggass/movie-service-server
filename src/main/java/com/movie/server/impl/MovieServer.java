package com.movie.server.impl;

import java.io.IOException;
import java.net.ServerSocket;

import com.google.inject.Inject;
import com.movie.server.Server;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MovieServer implements Server {

	@Inject
	private ServerSocket serverSocket;

	@Override
	public void start() {
		try {
			log.info("Iniciando servidor no endereço: {}", serverSocket.getLocalSocketAddress());
			while (true)
				new ClientHandler(serverSocket.accept()).start();
		} catch (IOException e) {
			log.error("Erro ao criar ClientHandler", e);
		} finally {
			stop();
		}

	}

	@Override
	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			log.error("Erro ao parar ClientHandler", e);
		}

	}

}