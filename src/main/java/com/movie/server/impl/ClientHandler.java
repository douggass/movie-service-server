package com.movie.server.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

import com.movie.controller.Controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHandler extends Thread {

	private Socket clientSocket;
	private Controller controller;
	private String methodName;
	private PrintWriter out;
	private BufferedReader in;
	
	public ClientHandler(Socket socket, Controller controller, String methodName) {
		this.clientSocket = socket;
		this.controller = controller;
		this.methodName = methodName;
	}

	@Override
	public void run() {
		try {
			this.out = new PrintWriter(clientSocket.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			handleConection(out, in);
			closeInOut();
			clientSocket.close();
		} catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			closeInOut();
			log.error("Erro na execução", e);
		}
	}

	private void closeInOut() {
		try {
			in.close();
			out.close();
		} catch (IOException e) {
			log.error("Erro ao fechar in/out", e);
		}
	}
	
	private void handleConection(final PrintWriter out, final BufferedReader in) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			log.info("Request={}", inputLine);
			String response = handleExcecution(inputLine);
			if (".".equals(inputLine)) {
				out.println("bye");
				break;
			}
			out.println(response);
			log.info("Response={}", response);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String handleExcecution(final String arg) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		try {
			Class clazz = Class.forName(controller.getClass().getName());
			log.info("clazz: {}", clazz);
			Method method = clazz.getMethod(methodName, String.class);
			log.info("method: {}", method);
			return (String) method.invoke(controller, arg);
		} catch (RuntimeException e) {
			log.error("Erro ao buscar filmes.", e);
			return "ERRO";
		}
	}

}