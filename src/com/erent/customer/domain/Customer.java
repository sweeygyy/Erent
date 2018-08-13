package com.erent.customer.domain;

import java.util.Date;

public class Customer {
	private String customer_id;
	private String name;
	private int sex;
	private String school;
	private String department;
	private String classNum;
	private String hometown;
	private Date birthday;
	private Date time_enrolment;
	private String tel;
	private String password;
	private String pImage;
	private int XYD;
	@Override
	public String toString() {
		return "CustomerDao2 [customer_id=" + customer_id + ", name=" + name + ", sex="
				+ sex + ", school=" + school + ", department=" + department
				+ ", classNum=" + classNum + ", hometown=" + hometown
				+ ", birthday=" + birthday + ", time_enrolment="
				+ time_enrolment + ", tel=" + tel + ", password=" + password
				+ ", pImage=" + pImage + ", XYD=" + XYD + "]";
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String buy_num) {
		this.customer_id = buy_num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getTime_enrolment() {
		return time_enrolment;
	}
	public void setTime_enrolment(Date time_enrolment) {
		this.time_enrolment = time_enrolment;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getpImage() {
		return pImage;
	}
	public void setpImage(String pImage) {
		this.pImage = pImage;
	}
	public int getXYD() {
		return XYD;
	}
	public void setXYD(int xYD) {
		XYD = xYD;
	}
}
