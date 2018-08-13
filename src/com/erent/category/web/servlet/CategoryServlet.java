package com.erent.category.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.erent.category.domain.Category;
import com.erent.category.service.CategoryService;
import com.erent.utils.BaseServlet;
import com.erent.utils.TransformUtils;

public class CategoryServlet extends BaseServlet {
	private CategoryService service = new CategoryService();
	
	public CategoryServlet() {
		super();
	}
	public void destroy() {
		super.destroy();

	}
	public void init() throws ServletException {
	}

	public void getAllCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> list = service.findAll();
		JSONObject array = TransformUtils.packArray(list);
		response.getWriter().print(array.toString());
	}
}
