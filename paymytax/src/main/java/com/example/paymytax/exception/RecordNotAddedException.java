package com.example.paymytax.exception;

public class RecordNotAddedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6475432332243011211L;

	public RecordNotAddedException() {
		super();
	}


	public RecordNotAddedException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecordNotAddedException(String message) {
		super(message);
	}

	public RecordNotAddedException(Throwable cause) {
		super(cause);
	}
	
	

}
