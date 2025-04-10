package com.app.e_commerc.exception;

public class ObjectNotExistException extends RuntimeException {
   
    	private static final long serialVersionUID = 1L;
    	public ObjectNotExistException (String message){
    		super(message);
    	}
    	public ObjectNotExistException(String message, Throwable cause) {
            		super(message, cause);
        	}

}

