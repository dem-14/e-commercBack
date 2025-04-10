package com.app.e_commerc.exception;

public class DuplicateIdException extends RuntimeException {
	private static final long serialVersionUID = 1L;	
	public DuplicateIdException(String message) {
		super(message);
    	}
}