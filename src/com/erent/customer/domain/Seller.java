package com.erent.customer.domain;

public class Seller extends Person {
	private String sNum;
	private int XYD;
	public String getsNum() {
		return sNum;
	}
	public void setsNum(String sNum) {
		this.sNum = sNum;
	}
	public int getXYD() {
		return XYD;
	}
	public void setXYD(int xYD) {
		XYD = xYD;
	}
	@Override
	public String toString() {
		return "Seller [sNum=" + sNum + ", XYD=" + XYD + "] " + super.toString();
	}
	
}
