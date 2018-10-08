package com.erent.orders.web.servlet.admin;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erent.commodity.service.CommodityException;
import com.erent.commodity.service.CommodityService;
import com.erent.customer.domain.Customer;
import com.erent.customer.service.CustomerException;
import com.erent.customer.service.CustomerService;
import com.erent.orders.domain.Orders;
import com.erent.orders.service.OrdersException;
import com.erent.orders.service.OrdersService;
import com.erent.utils.BaseServlet;
import com.erent.utils.CommonUtils;
import com.erent.utils.TransformUtils;

import net.sf.json.JSONObject;

public class AdminOrdersServlet extends BaseServlet {
	private OrdersService service = new OrdersService();

	/**
	 * Constructor of the object.
	 */
	
	/**
	 * 管理员获取订单功能
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getOrders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		
		try {
			List<Orders> result = service.adminGetOrders(nowPage, pageSize);
			String object = TransformUtils.returnArrayString(result);
			response.getWriter().print(object);
		} catch (OrdersException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
			e.printStackTrace();
		}
	}
	
	
	public void modity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Orders orders = CommonUtils.toBean(request.getParameterMap(), Orders.class);
		String customer_id = request.getParameter("customer_id");
		String commodity_id = request.getParameter("commodity_id");
		try {
			Orders newOrders = service.modify(orders, customer_id, commodity_id);
			response.getWriter().print(TransformUtils.packObject(newOrders));
		} catch (OrdersException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
			e.printStackTrace();
		} catch (CustomerException cue) {
			JSONObject object = TransformUtils.packException(cue);
			response.getWriter().print(object.toString());
			cue.printStackTrace();
		} catch (CommodityException coe) {
			JSONObject object = TransformUtils.packException(coe);
			response.getWriter().print(object.toString());
			coe.printStackTrace();
		}
	}
	
	public void remove(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String orders_id = request.getParameter("orders_id");
		try {
			service.remove(orders_id);
			JSONObject object = new JSONObject();
			object.accumulate("flag", true);
			response.getWriter().print(object.toString());
		} catch (OrdersException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
			e.printStackTrace();
		}
	}
}
