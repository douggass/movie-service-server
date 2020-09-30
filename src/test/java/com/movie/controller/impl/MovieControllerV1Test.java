package com.movie.controller.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.movie.dto.Input;
import com.movie.dto.Output;
import com.movie.exception.BadRequestException;
import com.movie.exception.HttpMovieClientException;
import com.movie.exception.InternalServerErroException;
import com.movie.service.MovieService;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerV1Test {

	private static final Input ANY_INPUT = Input.builder()
			.length(10)
			.query("Test")
			.build();

	private static final XmlMapper XML_MAPPER = new XmlMapper();

	@InjectMocks
	private MovieControllerV1 movieControllerV1;

	@Mock
	private MovieService movieService;

	@Mock
	private XmlMapper mapper;

	@Test
	public void findMoviesTest() throws JsonProcessingException {
		final String inputXml = XML_MAPPER.writeValueAsString(ANY_INPUT);

		final Output output = Output.builder()
				.length(2)
				.payload("Test \n Test: 12")
				.build();

		final String xmlOutput =  XML_MAPPER.writeValueAsString(output);

		when(movieService.findMovies(ANY_INPUT)).thenReturn(output);
		when(mapper.readValue(inputXml, Input.class)).thenReturn(ANY_INPUT);
		when(mapper.writeValueAsString(output)).thenReturn(xmlOutput);

		String result = movieControllerV1.findMovies(inputXml);

		assertEquals(xmlOutput, result);
	}

	@Test(expected = BadRequestException.class)
	public void findMoviesWhenBadlyFormattedXmlTest() throws JsonMappingException, JsonProcessingException {
		final String inputXml = "{}";

		when(mapper.readValue(inputXml, Input.class)).thenThrow(new JsonMappingException(null, ""));

		movieControllerV1.findMovies(inputXml);
	}

	@Test(expected = InternalServerErroException.class)
	public void findMoviesWhenErrorToParseObjectOutputToXmlTest() throws JsonProcessingException {
		final String inputXml = XML_MAPPER.writeValueAsString(ANY_INPUT);

		final Output output = Output.builder()
				.length(2)
				.payload("Test \n Test: 12")
				.build();

		when(movieService.findMovies(ANY_INPUT)).thenReturn(output);
		when(mapper.readValue(inputXml, Input.class)).thenReturn(ANY_INPUT);
		when(mapper.writeValueAsString(output)).thenThrow(new JsonMappingException(null, ""));

		movieControllerV1.findMovies(inputXml);
	}

	@Test(expected = HttpMovieClientException.class)
	public void findMoviesWhenServiceReturnEmptyTest() throws JsonProcessingException {

		final String inputXml = XML_MAPPER.writeValueAsString(ANY_INPUT);

		when(movieService.findMovies(ANY_INPUT)).thenThrow(new HttpMovieClientException());
		when(mapper.readValue(inputXml, Input.class)).thenReturn(ANY_INPUT);

		movieControllerV1.findMovies(inputXml);
	}

}
