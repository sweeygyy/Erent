package com.erent.customer.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.erent.customer.domain.Customer;
import com.erent.customer.domain.Person;
import com.erent.utils.TxQueryRunner;

public class CustomerDao {
	private QueryRunner runner = new TxQueryRunner();

	/**
	 * 查询所有Customer
	 * 
	 * @return
	 */
	public List<Customer> findAll() {
		String sql = "select * from customer";
		List<Customer> result = null;
		try {
			result = runner.query(sql, new BeanListHandler<Customer>(
					Customer.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 按学号查询Customer
	 * 
	 * @param cNum
	 * @return
	 */
	public Customer findCustomerByPNum(String pNum) {
		String sql = "select * from customer where customer_id = ?";
		try {
			Customer customer = runner.query(sql, new BeanHandler<Customer>(
					Customer.class), pNum);
			// 将sql.Date转换成util.Date
			transformDate(customer);
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按手机号查询Customer
	 * 
	 * @param tel
	 * @return
	 */
	public Customer findCustomerByTel(String tel) {
		String sql = "select * from customer where tel = ?";
		try {
			Customer customer = runner.query(sql, new BeanHandler<Customer>(
					Customer.class), tel);
			transformDate(customer);
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加Customer
	 * 
	 * @param form
	 */
	public void add(Customer form) {
		String sql = "select * from customer where customer_id = ?";
		try {
			if (null == runner.query(sql,
					new BeanHandler<Customer>(Customer.class), form.getCustomer_id())) {
				sql = "insert into customer (customer_id, name, sex, school, department, classNum,"
						+ " hometown, birthday, time_enrolment, tel, password, pImage, XYD) values "
						+ "(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				Object[] params = { form.getCustomer_id(), form.getName(),
						form.getSex(), form.getSchool(), form.getDepartment(),
						form.getClassNum(), form.getHometown(),
						form.getBirthday(), form.getTime_enrolment(),
						form.getTel(), form.getPassword(), form.getpImage(), form.getXYD() };
				runner.update(sql, params);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改Customer方法
	 * 
	 * @param form
	 */
	public void update(Customer form) {
		try {
		String sql = "update customer set name=?, sex=?, school=?, department=?, classNum=?, hometown=?, birthday=?, time_enrolment=?, tel=?, password=?, pImage=?, XYD=? where customer_id=?";
		Object[] params = { form.getName(), form.getSex(), form.getSchool(),
				form.getDepartment(), form.getClassNum(), form.getHometown(),
				form.getBirthday(), form.getTime_enrolment(), form.getTel(),
				form.getPassword(), form.getpImage(), form.getXYD(), form.getCustomer_id() };
		runner.update(sql, params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将sql.Date 转化成 util.Date
	 * 
	 * @param customer
	 */
	private void transformDate(Customer customer) {
		if (customer != null) {
			if (customer.getBirthday() != null) {
				customer.setBirthday(new Date(customer.getBirthday().getTime()));
			}
			if (customer.getTime_enrolment() != null) {
				customer.setTime_enrolment(new Date(customer
						.getTime_enrolment().getTime()));
			}
		}
	}
}
