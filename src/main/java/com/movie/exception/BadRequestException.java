package com.movie.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 3557105554006399532L;

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadRequestException() {
		super();
	}
}
