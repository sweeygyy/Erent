package com.erent.category.service;

import java.util.List;

import com.erent.category.dao.CategoryDao;
import com.erent.category.domain.Category;
import com.erent.commodity.dao.CommodityDao;
import com.erent.commodity.domain.Commodity;
import com.erent.utils.CommonUtils;

public class CategoryService {
	private CategoryDao dao = new CategoryDao();
	private CommodityDao comdao = new CommodityDao();

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	
	public Category findCategoryById(String id) {
		return dao.findCategoryById(id);
	}
	
	public List<Category> add(Category category) throws CategoryException {
		if(dao.findCategoryByName(category.getCate_name()) == null) {
			category.setCate_id(CommonUtils.uuid());
			dao.add(category);
			return findAll();
		} else {
			throw new CategoryException("分类已存在", 602);
		}
	}

	public List<Category> delete(Category category) throws CategoryException {
		if(dao.findCategoryById(category.getCate_id()) == null) {
			throw new CategoryException("分类不存在",603);
		} else {
			Category others = dao.findCategoryById("00000000");
			List<Commodity> commodities = comdao.findCommodityByCategoryId(category.getCate_id());
			for(Commodity com : commodities) {
				com.setCategory(others);
			}
			dao.delete(category);
			return findAll();
		}
	}

	public List<Category> modify(Category category) throws CategoryException {
		if(dao.findCategoryById(category.getCate_id()) == null) {
			throw new CategoryException("分类不存在",603);
		} else {
			dao.update(category);
			return findAll();
		}
	}

}
