package com.movie.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

	private static final String PROPERTIE_FILE = "bootstrap.properties";

	private PropertiesLoader() {
		throw new IllegalStateException("Utility class");
	}

	public static String getValues(final String key) {
		try {
			return PropertiesLoader.loadProperties(PROPERTIE_FILE).getProperty(key, null);
		} catch (IOException e) {
			return null;
		}
	}

	private static Properties loadProperties(final String file) throws IOException {
		Properties configuration = new Properties();
		InputStream inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream(file);
		configuration.load(inputStream);
		inputStream.close();
		return configuration;
	}

}
