package com.erent.customer.web.servlte.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erent.commodity.service.CommodityException;
import com.erent.customer.domain.Customer;
import com.erent.customer.service.CustomerException;
import com.erent.customer.service.CustomerService;
import com.erent.utils.BaseServlet;
import com.erent.utils.TransformUtils;

import net.sf.json.JSONObject;

public class AdminCustomerServlet extends BaseServlet {
	CustomerService service = new CustomerService();
	
	/**
	 * 获取用户列表
	 */
	public void getAllUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");

		List<Customer> users = null;
		try {
			users  = service.getAllUser(nowPage, pageSize);
			String responseStr = TransformUtils.returnArrayString(users);
			response.getWriter().print(responseStr);
		} catch (CustomerException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
		}
	}
	
	public void remove(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//		String commodityId = request.getParameter("customer_id");
//		try {
//			service.remove(commodityId);
//			JSONObject object = new JSONObject();
//			object.accumulate("flag", true);
//			response.getWriter().print(object.toString());
//		} catch (CommodityException e) {
//			JSONObject object = TransformUtils.packException(e);
//			response.getWriter().print(object.toString());
//		}		
	}
}
