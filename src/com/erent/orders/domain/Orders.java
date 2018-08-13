package com.erent.orders.domain;

import java.util.Date;

import com.erent.commodity.domain.Commodity;
import com.erent.customer.domain.Customer;

public class Orders {
	private String orders_id;
	private Customer customer;
	private Commodity commodity;
	private Date order_time;
	private int state;
	private int count;
	private double total;
	public String getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(String orders_id) {
		this.orders_id = orders_id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Orders [orders_id=" + orders_id + ", customer=" + customer
				+ ", commodity=" + commodity + ", order_time=" + order_time
				+ ", state=" + state + ", count=" + count + ", total=" + total
				+ "]";
	}
	
	
}
