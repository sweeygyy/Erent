package com.erent.category.web.servlet.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.erent.category.domain.Category;
import com.erent.category.service.CategoryException;
import com.erent.category.service.CategoryService;
import com.erent.utils.BaseServlet;
import com.erent.utils.CommonUtils;
import com.erent.utils.TransformUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AdminCategoryServlet extends BaseServlet {
	private CategoryService service = new CategoryService();

	/*
	 * 查询所有分类
	 */
	public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> categoryList = service.findAll(); 
		JSONArray arr = JSONArray.fromObject(categoryList);
//		JSONObject array = TransformUtils.packArray(categoryList);
		response.getWriter().print(arr.toString());
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
