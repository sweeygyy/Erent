package com.erent.orders.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.erent.category.domain.Category;
import com.erent.commodity.dao.CommodityDao;
import com.erent.commodity.domain.Commodity;
import com.erent.customer.dao.CustomerDao;
import com.erent.customer.domain.Customer;
import com.erent.orders.domain.Orders;
import com.erent.utils.CommonUtils;
import com.erent.utils.JdbcUtils;
import com.erent.utils.TxQueryRunner;

public class OrdersDao {
	private QueryRunner qr = new TxQueryRunner();
	private CommodityDao comdao = new CommodityDao();
	private CustomerDao cusdao = new CustomerDao();

	public void add(Orders orders) {
		String sql = "insert into orders (orders_id, customer_id, order_time, state, count, total, commodity_id) values(?,?,?,?,?,?,?)";
		Object[] params = { orders.getOrders_id(),
				orders.getCustomer().getCustomer_id(), orders.getOrder_time(),
				orders.getState(), orders.getCount(), orders.getTotal(),
				orders.getCommodity().getCommodity_id() };
		try {
			JdbcUtils.beginTransaction();
			qr.update(sql, params);
			sql = "update commodity set count = ? where commodity_id = ?";
			Object[] params2 = {
					(orders.getCommodity().getCount()) - orders.getCount(),
					orders.getCommodity().getCommodity_id() };
			qr.update(sql, params2);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Orders findOrdersById(String orders_id) {
		String sql = "select * from orders where orders_id = ?";
		Orders orders = null;
		try {
			Map<String, Object> map = qr
					.query(sql, new MapHandler(), orders_id);
			if (map != null) {
				orders = CommonUtils.toBean(map, Orders.class);
				Commodity com = CommonUtils.toBean(map, Commodity.class);
				Customer cus = CommonUtils.toBean(map, Customer.class);
				com = comdao.findCommodityById(com.getCommodity_id());
				cus = cusdao.findCustomerByPNum(cus.getCustomer_id());
				orders.setCommodity(com);
				orders.setCustomer(cus);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return orders;
	}

	public List<Orders> findOrdersByCustomerId(String customer_id) {
		String sql = "select * from orders where customer_id = ? order by order_time DESC";
		List<Orders> result = new ArrayList<Orders>();
		try {
			List<Map<String, Object>> list = qr.query(sql,
					new MapListHandler(), customer_id);
			for (Map<String, Object> map : list) {
				Orders orders = CommonUtils.toBean(map, Orders.class);
				Commodity com = CommonUtils.toBean(map, Commodity.class);
				Customer cus = CommonUtils.toBean(map, Customer.class);
				com = comdao.findCommodityById(com.getCommodity_id());
				cus = cusdao.findCustomerByPNum(cus.getCustomer_id());
				orders.setCommodity(com);
				orders.setCustomer(cus);
				result.add(orders);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public List<Orders> findOrdersBySellerId(String seller_id) {
		String sql = "select * from orders, commodity where orders.commodity_id = commodity.commodity_id and seller_id = ? order by order_time DESC";
		List<Orders> result = new ArrayList<Orders>();
		try {
			List<Map<String, Object>> list = qr.query(sql,
					new MapListHandler(), seller_id);
			for (Map<String, Object> map : list) {
				Orders orders = CommonUtils.toBean(map, Orders.class);
				Commodity com = CommonUtils.toBean(map, Commodity.class);
				Customer cus = CommonUtils.toBean(map, Customer.class);
				com = comdao.findCommodityById(com.getCommodity_id());
				cus = cusdao.findCustomerByPNum(cus.getCustomer_id());
				orders.setCommodity(com);
				orders.setCustomer(cus);
				result.add(orders);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 将sql.Date 转化成 util.Date
	 * 
	 * @param customer
	 */
	private void transformDate(Orders orders) {
		if (orders != null) {
			if (orders.getOrder_time() != null) {
				orders.setOrder_time(new Date(orders.getOrder_time().getTime()));
			}
		}
	}

	public void updateState(Orders orders) {
		String sql = "update orders set state = ? where orders_id = ?";
		Object[] param = { orders.getState(), orders.getOrders_id() };
		try {
			qr.update(sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Orders> findOrdersLimit(int nowPage, int pageSize) {
		String sql = "select * from orders order by order_time DESC limit ?, ?";
		List<Orders> result = new ArrayList<Orders>();
		try {
			List<Map<String, Object>> list = qr.query(sql,
					new MapListHandler(), (nowPage - 1) * pageSize, pageSize);
			for (Map<String, Object> map : list) {
				//处理关联关系
				Orders orders = CommonUtils.toBean(map, Orders.class);
				Commodity com = CommonUtils.toBean(map, Commodity.class);
				Customer cus = CommonUtils.toBean(map, Customer.class);
				com = comdao.findCommodityById(com.getCommodity_id());
				cus = cusdao.findCustomerByPNum(cus.getCustomer_id());
				orders.setCommodity(com);
				orders.setCustomer(cus);
				result.add(orders);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void update(Orders orders) {
		String sql = "update orders set  customer_id = ?, order_time = ?, state = ?, count = ?, total = ?, commodity_id = ?  where orders_id = ?";
		Object[] param = {orders.getCustomer().getCustomer_id(), orders.getOrder_time(),orders.getState(), orders.getCount(), orders.getTotal(), orders.getCommodity().getCommodity_id(), orders.getOrders_id() };
		try {
			qr.update(sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteOrders(Orders orders) {
		String sql = "delete from orders where orders_id = ?";
		Object[] param = {orders.getOrders_id() };
		try {
			qr.update(sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
