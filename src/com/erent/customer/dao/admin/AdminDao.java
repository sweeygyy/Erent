package com.erent.customer.dao.admin;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.erent.customer.domain.admin.Administrator;
import com.erent.utils.TxQueryRunner;

public class AdminDao {
	private QueryRunner runner = new TxQueryRunner();

	/**
	 * 查询所有Administrator
	 * 
	 * @return
	 */
	public List<Administrator> findAll() {
		String sql = "select * from admin";
		List<Administrator> result = null;
		try {
			result = runner.query(sql, new BeanListHandler<Administrator>(Administrator.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 按学号查询Administrator
	 * 
	 * @param cNum
	 * @return
	 */
	public Administrator findAdministratorByName(String adminName) {
		String sql = "select * from admin where admin_id = ?";
		try {
			Administrator admin = runner.query(sql, new BeanHandler<Administrator>(Administrator.class), adminName);
			// 将sql.Date转换成util.Date
			transformDate(admin);
			return admin;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * 添加Administrator
	 * 
	 * @param form
	 */
	public void add(Administrator form) {
		String sql = "select * from admin where admin_id = ?";
		try {
			if (null == runner.query(sql, new BeanHandler<Administrator>(Administrator.class),
					form.getAdmin_name())) {
				sql = "insert into admin (admin_name, admin_pwd, Creator, last_login_time, permission) values "
						+ "(?,?,?,?,?)";
				Object[] params = { form.getAdmin_name(), form.getAdmin_pwd(), form.getCreator(),
						form.getLast_login_time(), form.getPermission() };
				runner.update(sql, params);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改Administrator方法
	 * 
	 * @param form
	 */
	public void update(Administrator form) {
		try {
			String sql = "update admin set admin_pwd=?, Creator=?, last_login_time=?, permission=? where admin_name=?";
			Object[] params = { form.getAdmin_pwd(), form.getCreator(), form.getLast_login_time(),
					form.getPermission(), form.getAdmin_name()};
			runner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将sql.Date 转化成 util.Date
	 * 
	 * @param admin
	 */
	private void transformDate(Administrator admin) {
		if (admin != null) {
			if (admin.getLast_login_time() != null) {
				admin.setLast_login_time(new Date(admin.getLast_login_time().getTime()));
			}
		}
	}

}
