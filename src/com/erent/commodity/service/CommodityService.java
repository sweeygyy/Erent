package com.erent.commodity.service;

import java.util.List;

import com.erent.commodity.dao.CollectionDao;
import com.erent.commodity.dao.CommodityDao;
import com.erent.commodity.domain.Collection;
import com.erent.commodity.domain.Commodity;
import com.erent.customer.dao.CustomerDao;
import com.erent.customer.domain.Customer;
import com.erent.customer.service.CustomerException;
import com.erent.utils.CommonUtils;

public class CommodityService {
	private CommodityDao dao = new CommodityDao();
	private CollectionDao coldao = new CollectionDao();
	private CustomerDao custdao = new CustomerDao();
	/**
	 * 获取收藏逻辑
	 * @param customer_id
	 * @param nowPage
	 * @param pageSize
	 * @return
	 * @throws CommodityException
	 */
	public List<Collection> getCollections(String customer_id, String nowPage,
			String pageSize) throws CommodityException {
		List<Collection> result = null;
		try {
			int nowp = Integer.parseInt(nowPage);
			int pages = Integer.parseInt(pageSize);
			result = dao.findCollectionLimit(customer_id, nowp, pages);
		} catch (NumberFormatException e) {
			throw new CommodityException("页码无效", 201);
		}
		return result;
	}

	/**
	 * 搜索逻辑
	 * @param keyWord
	 * @param nowPage
	 * @param pageSize
	 * @return
	 * @throws CommodityException
	 */
	public List<Commodity> search(String keyWord, String nowPage, String pageSize) throws CommodityException {
		// TODO Auto-generated method stub
		List<Commodity> result = null;
		try {
			int nowp = Integer.parseInt(nowPage);
			int pages = Integer.parseInt(pageSize);
			result = dao.search(keyWord, nowp, pages);
		} catch (NumberFormatException e) {
			throw new CommodityException("页码无效", 201);
		}		
		if (result.size() < 1) {
			throw new CommodityException("没有更多了", 200);
		}
		return result;
	}

	/**
	 * 前台获取商品列表逻辑
	 * @param nowPage
	 * @param pageSize
	 * @return
	 * @throws CommodityException
	 */
	public List<Commodity> getCommodity(String nowPage, String pageSize)
			throws CommodityException {
		List<Commodity> result = null;
		try {
			int lowp = Integer.parseInt(nowPage);
			int highp = Integer.parseInt(pageSize);
			result = dao.findCommodityLimit(lowp, highp, false);
		} catch (NumberFormatException e) {
			throw new CommodityException("页码无效", 201);
		}
		return result;
	}
	
	/**
	 * 后台获取商品列表逻辑
	 * @param nowPage
	 * @param pageSize
	 * @return
	 * @throws CommodityException
	 */
	public List<Commodity> adminGetCommodity(String nowPage, String pageSize)
			throws CommodityException {
		List<Commodity> result = null;
		try {
			int lowp = Integer.parseInt(nowPage);
			int highp = Integer.parseInt(pageSize);
			result = dao.findCommodityLimit(lowp, highp, true);
		} catch (NumberFormatException e) {
			throw new CommodityException("页码无效", 201);
		}
		return result;
	}

	public Commodity add(Commodity form) throws CommodityException{
		//补全默认信息
		form.setCommodity_id(CommonUtils.uuid());
		System.out.println(form.getCommodity_id());
		dao.add(form);
		return dao.findCommodityById(form.getCommodity_id());
	}

	public void collect(String customer_id, String commodity_id) throws CommodityException, CustomerException{
		if(dao.findCommodityById(commodity_id) == null) {
			throw new CommodityException("商品不存在", 500);
		}
		if(custdao.findCustomerByPNum(customer_id) == null) {
			throw new CustomerException("用户不存在", 101);
		}	
		Collection coll = new Collection();
		Commodity com = new Commodity();
		com.setCommodity_id(commodity_id);
		Customer cus = new Customer();
		cus.setCustomer_id(customer_id);
		coll.setCommodity(com);
		coll.setCustomer(cus);
		if(coldao.isExist(coll)) {
			throw new CommodityException("您已经收藏过该商品", 501);
		}
		
		coll.setCollect_id(CommonUtils.uuid());
		System.out.println(coll.getCollect_id());
		coldao.add(coll);
	}

	public List<Commodity> getMyCommodity(String customer_id) {
		List<Commodity> result = dao.findCommodityByCustomerId(customer_id);
		return result;
	}
	
}
