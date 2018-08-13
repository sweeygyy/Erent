package com.erent.customer.service;

public class CustomerException extends Exception {
	private int errorCode;
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public CustomerException() {
		super();
	}

	public CustomerException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
}
