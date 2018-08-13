package com.erent.commodity.domain;

import java.util.Date;

import com.erent.category.domain.Category;
import com.erent.customer.domain.Customer;

public class Commodity {
	private String commodity_id;
	private String com_name;
	private Date bDate;
	private Date rDate;
	private String com_image;
	private String description;
	private int count;
	private Customer seller;
	private Category category;
	private double price;
	
	public String getCommodity_id() {
		return commodity_id;
	}
	public void setCommodity_id(String commodity_id) {
		this.commodity_id = commodity_id;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public Date getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
	public Date getrDate() {
		return rDate;
	}
	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}
	public String getCom_image() {
		return com_image;
	}
	public void setCom_image(String com_image) {
		this.com_image = com_image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Customer getSeller() {
		return seller;
	}
	public void setSeller(Customer seller) {
		this.seller = seller;
	}

	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Commodity [commodity_id=" + commodity_id + ", com_name="
				+ com_name + ", bDate=" + bDate + ", rDate=" + rDate
				+ ", com_image=" + com_image + ", description=" + description
				+ ", count=" + count + ", seller=" + seller + ", category="
				+ category + ", price=" + price + "]";
	}

}
