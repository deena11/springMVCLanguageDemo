package com.example.paymytax.exception;

public class TaxCalculationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8408817271182555561L;

	public TaxCalculationException() {
		super();
	}


	public TaxCalculationException(String message, Throwable cause) {
		super(message, cause);
	}

	public TaxCalculationException(String message) {
		super(message);
	}

	public TaxCalculationException(Throwable cause) {
		super(cause);
	}
	
	

}
