package com.erent.customer.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.erent.customer.domain.Person;
import com.erent.utils.TxQueryRunner;

public class PersonDao {
	QueryRunner runner = new TxQueryRunner();
	public Person findPersonByPNum(String pNum) {
		String sql = "select * from person where pNum = ?";
		try {
			return runner.query(sql,new BeanHandler<Person>(Person.class), pNum);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Person findPersonByTel(String tel) {
		String sql = "select * from person where tel = ?";
		try {
			return runner.query(sql,new BeanHandler<Person>(Person.class), tel);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void add(Person form) {
		try{
		String sql = "insert into person values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] params = {form.getpNum(), form.getName(), form.getSex(), form.getSchool(),
				form.getDepartment(), form.getClassNum(), form.getHometown(), 
				form.getBirthday(), form.getTime_enrolment(), form.getTel(), form.getPassword(),
				form.getpImage()};
		runner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
