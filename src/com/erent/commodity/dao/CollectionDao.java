package com.erent.commodity.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

import com.erent.commodity.domain.Collection;
import com.erent.utils.TxQueryRunner;

public class CollectionDao {
	QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 添加收藏
	 * @param form
	 */
	public void add(Collection form) {
		String sql = "insert into collect(commodity_id, customer_id, collect_id) values (?, ?, ?)";
		Object[] params = {form.getCommodity().getCommodity_id(), form.getCustomer().getCustomer_id(), form.getCollect_id()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean isExist(Collection form) {
		String sql = "select * from collect where commodity_id = ? and customer_id = ?";
		Object[] params = {form.getCommodity().getCommodity_id(), form.getCustomer().getCustomer_id()};
		try {
			return qr.query(sql, new MapHandler(), params) != null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
}
