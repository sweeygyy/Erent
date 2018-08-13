package com.erent.commodity.service;

public class CommodityException extends Exception {
	private int errorCode;
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public CommodityException() {
		super();
	}

	public CommodityException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
