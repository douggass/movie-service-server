package com.movie;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.concurrent.Executors;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.movie.dto.Input;
import com.movie.module.ServerModule;

public class MainTest {

	private Socket clientSocket1;
	private Socket clientSocket2;
	private Socket clientSocket3;
	
	private int port = ServerModule.getPort();

	@BeforeClass
	public static void setUp() throws UnknownHostException, IOException, InterruptedException {
		Executors.newSingleThreadExecutor().submit(() -> Main.main((String[]) Arrays.asList("dev").toArray()));
		Thread.sleep(500);
	}

	@Test
	public void givenClient1() throws IOException {
		clientSocket1 = new Socket("127.0.0.1", port);
		PrintWriter out = new PrintWriter(clientSocket1.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));

		final String xml = new XmlMapper().writeValueAsString(Input.builder().query("Game of Th").length(10).build());
		out.println(xml);
		String resp = in.readLine();
		in.close();
		out.close();
		clientSocket1.close();
		assertNotNull(resp);
	}

	@Test
	public void givenClient2() throws IOException {
		clientSocket2 = new Socket("127.0.0.1", port);
		PrintWriter out = new PrintWriter(clientSocket2.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));

		final String xml = new XmlMapper().writeValueAsString(Input.builder().query("Nar").length(10).build());
		out.println(xml);
		String resp = in.readLine();
		in.close();
		out.close();
		clientSocket2.close();
		assertNotNull(resp);
	}

	@Test
	public void givenClient3() throws IOException {
		clientSocket3 = new Socket("127.0.0.1", port);
		PrintWriter out = new PrintWriter(clientSocket3.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket3.getInputStream()));

		final String xml = new XmlMapper().writeValueAsString(Input.builder().query("Bat").length(10).build());
		out.println(xml);
		String resp = in.readLine();
		in.close();
		out.close();
		clientSocket3.close();
		assertNotNull(resp);
	}

}
