package com.erent.commodity.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.erent.category.dao.CategoryDao;
import com.erent.category.domain.Category;
import com.erent.commodity.domain.Collection;
import com.erent.commodity.domain.Commodity;
import com.erent.customer.dao.CustomerDao;
import com.erent.customer.domain.Customer;
import com.erent.utils.CommonUtils;
import com.erent.utils.TxQueryRunner;

public class CommodityDao {
	QueryRunner qr = new TxQueryRunner();
	CategoryDao cateDao = new CategoryDao();
	CustomerDao custDao = new CustomerDao();

	/**
	 * 查询所有商品
	 * 
	 * @return
	 */
	public List<Commodity> findAll() {
		String sql = "select * from commodity";
		List<Commodity> result = null;
		try {
			result = qr.query(sql, new BeanListHandler<Commodity>(
					Commodity.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<Collection> findCollectionLimit(String customer_id,
			int nowPage, int pageSize) {
		String sql = "select * from collect where customer_id = ? limit ?, ?";
		List<Collection> result = new ArrayList<Collection>();
		try {
			List<Map<String, Object>> list = qr.query(sql,
					new MapListHandler(), customer_id,
					(nowPage - 1) * pageSize, pageSize);
			for (Map<String, Object> map : list) {
				Collection coll = CommonUtils.toBean(map, Collection.class);
				Commodity com = CommonUtils.toBean(map, Commodity.class);
				Customer customer = CommonUtils.toBean(map, Customer.class);
				com = findCommodityById(com.getCommodity_id());
				customer = custDao.findCustomerByPNum(customer.getCustomer_id());
				transformDate(com);
				coll.setCommodity(com);
				coll.setCustomer(customer);
				result.add(coll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<Commodity> search(String keyWord, int nowPage, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select * from commodity where com_name LIKE ? limit ?,?";
		List<Commodity> result = new ArrayList<Commodity>();
		try {
			List<Map<String, Object>> list = qr.query(sql,
					new MapListHandler(), "%" + keyWord + "%", (nowPage - 1)
							* pageSize, pageSize);
			for (Map<String, Object> map : list) {
				//处理关联关系
				Commodity com = CommonUtils.toBean(map, Commodity.class);
				Category cate = CommonUtils.toBean(map, Category.class);
				Customer seller = new Customer();
				seller.setCustomer_id(map.get("seller_id").toString());
				cate = cateDao.findCategoryById(cate.getCate_id());
				seller = custDao.findCustomerByPNum(seller.getCustomer_id());
				com.setCategory(cate);
				com.setSeller(seller);
				transformDate(com);
				result.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<Commodity> findCommodityLimit(int nowPage, int pageSize, boolean admin) {
		String sql = "select * from commodity where deleted=0 && count > 0 order by bDate DESC limit ?, ?";
		if(admin) {
			sql = "select * from commodity order by bDate DESC limit ?,?";
		}
		List<Commodity> result = new ArrayList<Commodity>();
		try {
			List<Map<String, Object>> list = qr.query(sql,
					new MapListHandler(), (nowPage - 1) * pageSize, pageSize);
			for (Map<String, Object> map : list) {
				//处理关联关系
				Commodity com = CommonUtils.toBean(map, Commodity.class);
				Category cate = CommonUtils.toBean(map, Category.class);
				Customer seller = new Customer();
				seller.setCustomer_id(map.get("seller_id").toString());
				cate = cateDao.findCategoryById(cate.getCate_id());
				seller = custDao.findCustomerByPNum(seller.getCustomer_id());
				com.setCategory(cate);
				com.setSeller(seller);
				transformDate(com);
				result.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void add(Commodity form) {
		String sql = "insert into commodity (commodity_id, com_name, bDate, rDate, com_image, deleted,"
				+ "description , count, seller_id, cate_id) values "
				+ "(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { form.getCommodity_id(), form.getCom_name(),
				form.getbDate(), form.getrDate(), form.getCom_image(), 0,
				form.getDescription(), form.getCount(),
				form.getSeller().getCustomer_id(),
				form.getCategory().getCate_id()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将sql.Date 转化成 util.Date
	 * 
	 * @param commodity
	 */
	private void transformDate(Commodity commodity) {
		if (commodity != null) {
			if (commodity.getbDate() != null) {
				commodity.setbDate(new Date(commodity.getbDate().getTime()));
			}
			if (commodity.getrDate() != null) {
				commodity.setrDate(new Date(commodity.getrDate().getTime()));
			}
		}
	}

	public Commodity findCommodityById(String commodity_id) {
		String sql = "select * from commodity where commodity_id = ?";
		Commodity com = null;
		try {
			Map<String, Object> map = qr.query(sql, new MapHandler(), commodity_id);
				//处理关联关系
			if(map != null) {
				com = CommonUtils.toBean(map, Commodity.class);
				Category cate = CommonUtils.toBean(map, Category.class);
				Customer seller = new Customer();
				seller.setCustomer_id(map.get("seller_id").toString());
				cate = cateDao.findCategoryById(cate.getCate_id());
				seller = custDao.findCustomerByPNum(seller.getCustomer_id());
				com.setCategory(cate);
				com.setSeller(seller);
				transformDate(com);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return com;
	}

	public List<Commodity> findCommodityByCustomerId(String customer_id) {
		String sql = "select * from commodity where seller_id = ?";
		List<Commodity> result = new ArrayList<Commodity>();
		try {
			List<Map<String, Object>> list = qr.query(sql, new MapListHandler(), customer_id);
			for (Map<String, Object> map : list) {
				//处理关联关系
				Commodity com = CommonUtils.toBean(map, Commodity.class);
				Category cate = CommonUtils.toBean(map, Category.class);
				Customer seller = new Customer();
				seller.setCustomer_id(map.get("seller_id").toString());
				cate = cateDao.findCategoryById(cate.getCate_id());
				seller = custDao.findCustomerByPNum(seller.getCustomer_id());
				com.setCategory(cate);
				com.setSeller(seller);
				transformDate(com);
				result.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
