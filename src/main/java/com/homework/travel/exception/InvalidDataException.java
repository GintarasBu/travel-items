package com.homework.travel.exception;

import org.springframework.validation.Errors;

public class InvalidDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6657605223719920286L;
	private Errors errors;
	
	public InvalidDataException(String message) {
		super(message);
	}
	
	public InvalidDataException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidDataException(Errors errors) {
		super();
		this.errors = errors;
	}
	
	public Errors getErrors() {
		return errors;
	}
}
