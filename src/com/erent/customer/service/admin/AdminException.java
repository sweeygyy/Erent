package com.erent.customer.service.admin;

public class AdminException extends Exception {
	private int errorCode;
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public AdminException() {
		super();
	}

	public AdminException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
}
