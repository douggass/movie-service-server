package com.movie.module;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.inject.AbstractModule;

public class GeneralModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ObjectMapper.class)
				.toInstance(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
		bind(XmlMapper.class);
	}

}
