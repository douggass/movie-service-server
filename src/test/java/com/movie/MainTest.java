package com.movie;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	@Before
	public void setUp() throws UnknownHostException, IOException {
		clientSocket = new Socket("127.0.0.1", 6666);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	@After
	public void stopClient() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}
	
	@Test
    public void sendMessage() throws IOException {
        out.println("ROLA");
        String resp = in.readLine();
        assertTrue("ROLA".equals(resp));
    }

}
