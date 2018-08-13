package com.erent.test;

import java.util.Date;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONBuilder;
import net.sf.json.util.JSONUtils;

import org.junit.Test;

import com.erent.customer.domain.Customer;

public class JSONTest {
	@Test
	public void fun1() {
		//brithday=1999-01-01, Time_enrolment=2015-09-13, tel=12345678, password=123456, pImage=]
		Customer c = new Customer();
		c.setName("admin");
//		c.setpNum("151543160");
//		c.setSex(1);
//		c.setcNum("1");
//		c.setDepartment("互联网学院");
//		c.setClassNum("1515431");
//		c.setHometown("广东广州");
//		c.setXYD(100);
//		c.setSchool("广东金融学院");
//		Date date = new Date(1999,1,1);
		java.sql.Date d = new java.sql.Date(new Date().getTime());
//		Date date = (Date)d;
		Date date = new Date(d.getTime());
		c.setBirthday(date);
		System.out.println(c);
		String json = JSONObject.fromObject(c).toString();
		System.out.println(json);
		
		JSONObject obj = JSONObject.fromObject(json);
		Customer c2 = (Customer)JSONObject.toBean(obj, Customer.class);
		System.out.println(c2.getBirthday());
	}
}
