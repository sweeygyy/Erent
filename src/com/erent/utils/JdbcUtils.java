package com.erent.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JdbcUtils v1.2
 * 
 * @author acer-pc
 * 
 */
public class JdbcUtils {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
//	 private static ComboPooledDataSource dataSource = new
//	 ComboPooledDataSource("Wifi-config");
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	public static Connection getConnection() throws SQLException {
		Connection con = tl.get();
		if (con == null) {
			return dataSource.getConnection();
		} else {
			return con;
		}
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static void beginTransaction() throws SQLException {
		Connection con = tl.get();
		if (con != null) {
			throw new RuntimeException("您已经开启了事务，无法再开启事务！");
		}
		con = getConnection();
		con.setAutoCommit(false);
		tl.set(con);
	}

	public static void commitTransaction() throws SQLException {
		Connection con = tl.get();
		if (con == null) {
			throw new RuntimeException("您未开启事务，无法提交！");
		}
		con.commit();
		con.close();
		tl.remove();
	}

	public static void rollbackTransaction() throws SQLException {
		Connection con = tl.get();
		if (con == null) {
			throw new RuntimeException("您未开启事务，无法回滚！");
		}
		con.rollback();
		con.close();
		tl.remove();
	}

	public static void releaseConnection(Connection connection)
			throws SQLException {
		Connection con = tl.get();
		if (con == null) {
			connection.close();
		}
		if (con != connection) {
			connection.close();
		}
	}
}
