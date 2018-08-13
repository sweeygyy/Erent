package com.erent.commodity.domain;

import com.erent.customer.domain.Customer;

public class Collection {
	private String collect_id;
	private Commodity commodity;
	private Customer customer;
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getCollect_id() {
		return collect_id;
	}
	public void setCollect_id(String collect_id) {
		this.collect_id = collect_id;
	}
	@Override
	public String toString() {
		return "Collection [collect_id=" + collect_id + ", commodity="
				+ commodity + ", customer=" + customer + "]";
	}
	
}	
