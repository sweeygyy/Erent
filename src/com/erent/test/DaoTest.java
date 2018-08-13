package com.erent.test;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.junit.Test;

import com.erent.commodity.domain.Commodity;
import com.erent.customer.dao.CustomerDao;
import com.erent.customer.dao.SellerDao;
import com.erent.customer.domain.Customer;
import com.erent.customer.domain.Person;
import com.erent.customer.domain.Seller;
import com.erent.utils.TxQueryRunner;

public class DaoTest {
	@Test
	public void fun1() {
		CustomerDao dao = new CustomerDao();
		List<Customer> result = dao.findAll();
		for(Customer c : result) {
//			printInfo(c);
			System.out.println(c);
		}
	}
	
	@Test
	public void fun2() {
		CustomerDao dao = new CustomerDao();
		System.out.println(dao.findCustomerByPNum("151543160"));
	}
	
//	@Test
//	public void fun3() {
//		SellerDao dao = new SellerDao();
//		List<Seller> result = dao.findAll();
//		for(Seller s : result) {
//			printInfo(s);
//		}
//	}
	
	@Test
	public void fun4() {
		SellerDao dao = new SellerDao();
		List<Seller> result = dao.findSellerBySNum("1");
		for(Seller s : result) {
//			printInfo(s);
			System.out.println(s);
		}
	}
	
	@Test
	public void fun6() {
		QueryRunner qr = new TxQueryRunner();
		String sql = "select * from commodity where commodity_id = ?";
		Commodity com = null;
		try {
			Map<String, Object> map = qr.query(sql, new MapHandler(), "abcd");
			System.out.println(map == null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
//	private void printInfo(Person c) {
//		System.out.println(c.getpNum());
//		System.out.println(c.getClassNum());
//		System.out.println(c.getDepartment());
//		System.out.println(c.getHometown());
//		System.out.println(c.getName());
//		System.out.println(c.getPassword());
//		System.out.println(c.getpImage());
//		System.out.println(c.getSchool());
//		System.out.println(c.getSex());
//		System.out.println(c.getTel());
//		System.out.println(c.getBirthday());
//		System.out.println(c.getTime_enrolment());
//		if(c instanceof Customer) {
//			Customer c1 = (Customer)c;
//			System.out.println(c1.getcNum());
//			System.out.println(c1.getXYD());
//		} else {
//			Seller s = (Seller)c;
//			System.out.println(s.getsNum());
//			System.out.println(s.getXYD());
//		}
//	}
}
