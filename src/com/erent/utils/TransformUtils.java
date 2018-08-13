package com.erent.utils;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.erent.category.service.CategoryException;
import com.erent.commodity.service.CommodityException;
import com.erent.customer.service.CustomerException;
import com.erent.orders.service.OrdersException;

public class TransformUtils {

	/**
	 * 包装用户异常信息为响应json对象
	 * @param e
	 * @return
	 */
	public static JSONObject packException(CustomerException e) {
		JSONObject object = new JSONObject();
		object.accumulate("flag", false);
		object.accumulate("errorCode", e.getErrorCode());
		object.accumulate("errorString", e.getMessage());
		return object;
	}
	
	
	/**
	 * 包装用户异常信息为响应json对象
	 * @param e
	 * @return
	 */
	public static JSONObject packException(CategoryException e) {
		JSONObject object = new JSONObject();
		object.accumulate("flag", false);
		object.accumulate("errorCode", e.getErrorCode());
		object.accumulate("errorString", e.getMessage());
		return object;
	}
	
	/**
	 * 包装商品异常信息为响应json对象
	 * @param e
	 * @return
	 */
	public static JSONObject packException(CommodityException e) {
		JSONObject object = new JSONObject();
		object.accumulate("flag", false);
		object.accumulate("errorCode", e.getErrorCode());
		object.accumulate("errorString", e.getMessage());
		return object;
	}
	
	/**
	 * 包装用户异常信息为响应json对象
	 * @param e
	 * @return
	 */
	public static JSONObject packException(OrdersException e) {
		JSONObject object = new JSONObject();
		object.accumulate("flag", false);
		object.accumulate("errorCode", e.getErrorCode());
		object.accumulate("errorString", e.getMessage());
		return object;
	}
	
	/**
	 * 包装Customer为响应json对象
	 * @param customer
	 * @return
	 */
//	public static JSONObject packCustomer(Customer customer) {
//		String json = JSONObject.fromObject(customer).toString();
//		JSONObject object = new JSONObject();
//		object.accumulate("flag", true);
//		object.accumulate("data", json);
//		return object;
//	}
	
	/**
	 * 将成功响应数据封装为json对象
	 * @param object
	 * @return
	 */
	public static JSONObject packObject(Object object) {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		String json = JSONObject.fromObject(object, config).toString();
		JSONObject packed = new JSONObject();
		packed.accumulate("flag", true);
		packed.accumulate("data", json);
		return packed;
	}
	
	/**
	 * 将成功响应数据集合封装成json对象
	 * @param list
	 * @return
	 */
	public static JSONObject packArray(List list) {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		String json = JSONArray.fromObject(list, config).toString();
		JSONObject packed = new JSONObject();
		packed.accumulate("flag", true);
		packed.accumulate("data", json);
		return packed;
	}

	/**
	 * 将相应成功的Commodity装换成JSONObject
	 */
//	public static JSONObject packCommodity(Commodity commodity) {
//		String json = JSONObject.fromObject(commodity).toString();
//		JSONObject object = new JSONObject();
//		object.accumulate("flag", true);
//		object.accumulate("data", json);
//		return object;
//	}

	
}
