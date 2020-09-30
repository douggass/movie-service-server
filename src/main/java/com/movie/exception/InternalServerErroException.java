package com.movie.exception;

public class InternalServerErroException extends RuntimeException {

	private static final long serialVersionUID = 4993536308455337304L;

	public InternalServerErroException(String message, Throwable cause) {
		super(message, cause);
	}

	public InternalServerErroException() {
		super();
	}

}
