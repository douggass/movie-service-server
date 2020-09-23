package com.movie.server.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHandler extends Thread {

	private Socket clientSocket;

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
	}

	@Override
	public void run() {
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				log.info("Mensagem: {}", inputLine);
				if (".".equals(inputLine)) {
					out.println("bye");
					break;
				}
				out.println(inputLine);
			}
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			log.error("Erro ao tratar requisição", e);
		}
	}
}