package com.erent.category.service;

import java.util.List;

import com.erent.category.dao.CategoryDao;
import com.erent.category.domain.Category;
import com.erent.utils.CommonUtils;

public class CategoryService {
	private CategoryDao dao = new CategoryDao();

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	
	public Category findCategoryById(String id) {
		return dao.findCategoryById(id);
	}
	
	public void add(Category category) throws CategoryException {
		if(dao.findCategoryByName(category.getCate_name()) == null) {
			category.setCate_id(CommonUtils.uuid());
			dao.add(category);
		} else {
			throw new CategoryException("分类已存在", 602);
		}
	}

}
