package com.movie.controller;

import java.io.IOException;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.inject.Inject;
import com.movie.exception.BadRequestException;
import com.movie.exception.InternalServerErroException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DefaultController {

	private static final String ERRO_PARSE_TO_XML = "Erro ao realizar parse para xml";

	private static final String ERRO_PARSE_FROM_XML = "Erro ao realizar parse do xml";

	@Inject
	private XmlMapper mapper;

	protected String toXml(final Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (IOException e) {
			log.error(ERRO_PARSE_TO_XML, e);
			throw new InternalServerErroException(ERRO_PARSE_TO_XML, e);
		}
	}

	protected <T> T fromXml(final String xml, Class<T> classType) {
		try {
			return mapper.readValue(xml, classType);
		} catch (IOException e) {
			log.error(ERRO_PARSE_FROM_XML, e);
			throw new BadRequestException(ERRO_PARSE_FROM_XML, e);
		}
	}

}
