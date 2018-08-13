package com.erent.category.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.erent.category.domain.Category;
import com.erent.utils.TxQueryRunner;

public class CategoryDao {
	QueryRunner runner = new TxQueryRunner();
	
	public List<Category> findAll() {
		String sql = "select * from category";
		List result = null;
		try{
			result = runner.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	public Category findCategoryById(String id) {
		String sql = "select * from category where cate_id = ?";
		Category category = null;
		try {
			category = runner.query(sql, new BeanHandler<Category>(Category.class), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return category;
	}
	
	public Category findCategoryByName(String name) {
		String sql = "select * from category where cate_name = ?";
		Category category = null;
		try {
			category = runner.query(sql, new BeanHandler<Category>(Category.class), name);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return category;
	}

	public void add(Category category) {
		String sql = "insert into category (cate_id, cate_name) values (?, ?)";
		try {
			runner.update(sql, category.getCate_id(), category.getCate_name());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
