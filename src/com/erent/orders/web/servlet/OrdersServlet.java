package com.erent.orders.web.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.erent.commodity.service.CommodityException;
import com.erent.customer.service.CustomerException;
import com.erent.orders.domain.Orders;
import com.erent.orders.service.OrdersException;
import com.erent.orders.service.OrdersService;
import com.erent.utils.BaseServlet;
import com.erent.utils.TransformUtils;

public class OrdersServlet extends BaseServlet {
	private OrdersService service = new OrdersService();

	/**
	 * Constructor of the object.
	 */
	public OrdersServlet() {
		super();
	}

	public void destroy() {
		super.destroy();

	}

	public void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String customer_id = request.getParameter("customer_id");
		String commodity_id = request.getParameter("commodity_id");
		int count = Integer.parseInt(request.getParameter("count"));
		try {
			service.add(customer_id, commodity_id, count);
			JSONObject object = new JSONObject();
			object.accumulate("flag", true);
			response.getWriter().print(object.toString());
		} catch (CustomerException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
			e.printStackTrace();
		} catch (CommodityException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
			e.printStackTrace();
		}
	}
	
	public void customerConfirm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String orders_id = request.getParameter("orders_id");
		try {
			service.customerConfirm(orders_id);
			JSONObject object = new JSONObject();
			object.accumulate("flag", true);
			response.getWriter().print(object.toString());
		} catch (OrdersException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
			e.printStackTrace();
		}
	}
	
	public void sellerConfirm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String orders_id = request.getParameter("orders_id");
		try {
			service.sellerConfirm(orders_id);
			JSONObject object = new JSONObject();
			object.accumulate("flag", true);
			response.getWriter().print(object.toString());
		} catch (OrdersException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
			e.printStackTrace();
		}
	}
	
	public void sellerGetOrders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String customer_id = request.getParameter("customer_id");
		
		List<Orders> result = service.SellerGetOrders(customer_id);
		JSONObject object = TransformUtils.packArray(result);
		response.getWriter().print(object.toString());
		
	}
	
	public void customerGetOrders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String customer_id = request.getParameter("customer_id");
		
		List<Orders> result = service.CustomerGetOrders(customer_id);
		JSONObject object = TransformUtils.packArray(result);
		response.getWriter().print(object.toString());
		
	}
	
	public void init() throws ServletException {

	}

}
