package com.erent.customer.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.erent.customer.domain.Seller;
import com.erent.utils.TxQueryRunner;

public class SellerDao {
private QueryRunner runner = new TxQueryRunner();
	
	public List<Seller> findAll() {
		String sql = "select person.pNum, name, sex, school, department, classNum, hometown, brithday, Time_enrolment, tel, password, pImage, sNum, XYD from seller, person where seller.pNum = person.pNum";
		List<Seller> result = null;
		try {
			result = runner.query(sql, new BeanListHandler<Seller>(Seller.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Seller> findSellerBySNum(String sNum) {
		//TODO
		String sql = "select person.pNum, name, sex, school, department, classNum, hometown, brithday, Time_enrolment, tel, password, pImage, sNum, XYD from seller, person where seller.pNum = person.pNum and sNum = ?";
		List<Seller> result = null;
		try {
			result = runner.query(sql, new BeanListHandler<Seller>(Seller.class), sNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
