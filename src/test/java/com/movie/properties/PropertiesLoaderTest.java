package com.movie.properties;

import static com.movie.properties.PropertiesLoader.getValues;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;

public class PropertiesLoaderTest {

	@Test
	public void getPropertyTest() throws IOException {
		String result = getValues("server.port");
		assertNotNull(result);
		assertEquals("8080", result);
	}
	
	@Test
	public void getPropertyWhenReturnNonExistentPropertiesTest() throws IOException {
		String result = getValues("teste.nao.existente");
		assertNull(result);
	}
}
