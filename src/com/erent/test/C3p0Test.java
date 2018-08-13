package com.erent.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Test {
//	private static String url = "jdbc:mysql://10.28.6.19:3306/zulinbao";
//	private static String DriverClass = "com.mysql.jdbc.Driver";
//	private static String user = "root";
//	private static String password = "root";
//	static {
//		try {
//			Class.forName(DriverClass);
//			Connection con = DriverManager.getConnection(url, user, password); 
//			System.out.println(con);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public static void main(String[] args) throws SQLException {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		System.out.println(ds.getPassword());
		System.out.println(ds.getUser());
		System.out.println(ds.getDriverClass());
		System.out.println(ds.getJdbcUrl());
		try{
			System.out.println(ds.getConnection());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
