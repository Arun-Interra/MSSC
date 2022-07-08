package com.mazdausa.ssc.exception;

public class SSCException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SSCException(String message) {
		super(message);
	}

	public SSCException(String message, Throwable exp) {
		super(message, exp);
	}

}
