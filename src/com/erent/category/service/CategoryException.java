package com.erent.category.service;

public class CategoryException extends Exception {
	private int errorCode;
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public CategoryException() {
		super();
	}

	public CategoryException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
