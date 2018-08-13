package com.erent.customer.domain;

import java.util.Date;

public class Person {
	private String pNum;
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
	public String getpNum() {
		return pNum;
	}
	public void setpNum(String pNum) {
		this.pNum = pNum;
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
	
	@Override
	public String toString() {
		return "Person [pNum=" + pNum + ", name=" + name + ", sex=" + sex
				+ ", school=" + school + ", department=" + department
				+ ", classNum=" + classNum + ", hometown=" + hometown
				+ ", birthday=" + birthday + ", time_enrolment="
				+ time_enrolment + ", tel=" + tel + ", password=" + password
				+ ", pImage=" + pImage + "]";
	}
}
