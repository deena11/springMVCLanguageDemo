package com.example.paymytax.exception;

public class RecordNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8787078734022115681L;

	public RecordNotFoundException() {
		super();
	}

	public RecordNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecordNotFoundException(String message) {
		super(message);
	}

	public RecordNotFoundException(Throwable cause) {
		super(cause);
	}
	
	

}
