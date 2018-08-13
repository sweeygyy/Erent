package com.erent.orders.service;

public class OrdersException extends Exception {
private int errorCode;
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public OrdersException() {
		super();
	}

	public OrdersException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
}
