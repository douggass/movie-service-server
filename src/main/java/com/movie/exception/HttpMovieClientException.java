package com.movie.exception;

public class HttpMovieClientException extends RuntimeException {

	private static final long serialVersionUID = -2563137835718826425L;

	public HttpMovieClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpMovieClientException() {
		super();
	}

}
