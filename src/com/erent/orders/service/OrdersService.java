package com.erent.orders.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.erent.commodity.dao.CommodityDao;
import com.erent.commodity.domain.Commodity;
import com.erent.commodity.service.CommodityException;
import com.erent.customer.dao.CustomerDao;
import com.erent.customer.domain.Customer;
import com.erent.customer.service.CustomerException;
import com.erent.orders.dao.OrdersDao;
import com.erent.orders.domain.Orders;
import com.erent.utils.CommonUtils;

public class OrdersService {
	private OrdersDao dao = new OrdersDao();
	private CustomerDao custdao = new CustomerDao();
	private CommodityDao comdao = new CommodityDao();

	public void add(String customer_id, String commodity_id, int count) throws CustomerException, CommodityException {
		Customer customer = custdao.findCustomerByPNum(customer_id);
		Commodity commodity = comdao.findCommodityById(commodity_id);
		if(customer == null) {
			throw new CustomerException("用户不存在", 101);
		}
		if(commodity == null) {
			throw new CommodityException("商品不存在", 500);
		}
		
		if(commodity.getSeller().getCustomer_id().equals(customer.getCustomer_id())) {
			throw new CommodityException("不能接自己发布的任务", 503);
		}
		
		System.out.println(commodity.getCount());
		System.out.println(count);
		if(commodity.getCount() < count) {
			throw new CommodityException("商品数量不足", 501);
		}
		
		BigDecimal price = new BigDecimal(commodity.getPrice() + "");
		BigDecimal newCount = new BigDecimal(count + "");
		double total = price.multiply(newCount).doubleValue();
		
		Orders orders = new Orders();
		orders.setCommodity(commodity);
		orders.setCustomer(customer);
		orders.setCount(count);
		orders.setOrder_time(new Date());
		orders.setState(0);
		orders.setTotal(total);
		orders.setOrders_id(CommonUtils.uuid());

		dao.add(orders);	
	}

	public void customerConfirm(String orders_id) throws OrdersException {
		Orders orders = dao.findOrdersById(orders_id);
		if(orders == null) {
			throw new OrdersException("该订单不存在", 703);
		}
		if(orders.getState() == 1) {
			throw new OrdersException("客户已经确认完成订单", 700);
		} else if(orders.getState() == 3) {
			throw new OrdersException("该订单已经完成", 702);
		} else if(orders.getState() == 2) {
			orders.setState(3);
		} else {
			orders.setState(1);
		}
		dao.updateState(orders);
	}
	
	public void sellerConfirm(String orders_id) throws OrdersException {
		Orders orders = dao.findOrdersById(orders_id);
		if(orders == null) {
			throw new OrdersException("该订单不存在", 703);
		}
		if(orders.getState() == 2) {
			throw new OrdersException("卖家已经确认完成订单", 701);
		} else if(orders.getState() == 3) {
			throw new OrdersException("该订单已经完成", 702);
		} else  if(orders.getState() == 1) {
			orders.setState(3);
		} else {
			orders.setState(2);
		}
		dao.updateState(orders);
	}

	public List<Orders> CustomerGetOrders(String customer_id) {
		List<Orders> result = dao.findOrdersByCustomerId(customer_id);
		return result;
	}
	
	public List<Orders> SellerGetOrders(String seller_id) {
		List<Orders> result = dao.findOrdersBySellerId(seller_id);
		return result;
	}
}
