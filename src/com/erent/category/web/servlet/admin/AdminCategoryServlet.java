package com.erent.category.web.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.erent.category.domain.Category;
import com.erent.category.service.CategoryException;
import com.erent.category.service.CategoryService;
import com.erent.utils.BaseServlet;
import com.erent.utils.CommonUtils;
import com.erent.utils.TransformUtils;

public class AdminCategoryServlet extends BaseServlet {
	private CategoryService service = new CategoryService();

	/*
	 * 查询所有分类
	 */
	public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> categoryList = service.findAll();
		JSONObject array = TransformUtils.packArray(categoryList);
		response.getWriter().print(array.toString());
	}
	
	/*
	 * 添加分类
	 */
	public void addCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		try {
			service.add(category);
		} catch (CategoryException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
		}
	}
}
