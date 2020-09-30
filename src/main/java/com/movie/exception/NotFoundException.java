package com.movie.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4405960525393884638L;

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException() {
		super();
	}

}
