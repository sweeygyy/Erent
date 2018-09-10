package com.erent.commodity.web.servlet;

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

import net.sf.json.JSONObject;

import com.erent.commodity.domain.Collection;
import com.erent.commodity.domain.Commodity;
import com.erent.commodity.service.CommodityException;
import com.erent.commodity.service.CommodityService;
import com.erent.customer.service.CustomerException;
import com.erent.utils.BaseServlet;
import com.erent.utils.CommonUtils;
import com.erent.utils.TransformUtils;

public class CommodityServlet extends BaseServlet {
	private CommodityService service = new CommodityService();

	/**
	 * Constructor of the object.
	 */
	public CommodityServlet() {
		super();
	}

	public void destroy() {
		super.destroy();

	}
	
	/**
	 * 添加到收藏
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void collect(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String customer_id = request.getParameter("customer_id");
		String commodity_id = request.getParameter("commodity_id");
		try {
			service.collect(customer_id, commodity_id);
			JSONObject object = new JSONObject();
			object.accumulate("flag", true);
			response.getWriter().print(object.toString());
		} catch(CommodityException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
		} catch(CustomerException ex) {
			JSONObject object = TransformUtils.packException(ex);
			response.getWriter().print(object.toString());
		}
	
	}
	
	/**
	 * 获取我的收藏
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getCollection(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String customer_id = request.getParameter("customer_id");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		List<Collection> collections = null;
		try {
			collections = service
					.getCollections(customer_id, nowPage, pageSize);
			JSONObject object = TransformUtils.packArray(collections);
			response.getWriter().print(object.toString());
		} catch (CommodityException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
		}

	}

	/**
	 * 推荐
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void recommend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * 获取商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getCommodity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");

		List<Commodity> result = null;
		try {
			result = service.getCommodity(nowPage, pageSize);
			JSONObject object = TransformUtils.packArray(result);
			response.getWriter().print(object.toString());
		} catch (CommodityException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
		}		
	}

	/**
	 * 搜索商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String keyWord = request.getParameter("search");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		List<Commodity> result;
		try {
			result = service.search(keyWord, nowPage, pageSize);
			JSONObject object = TransformUtils.packArray(result);
			response.getWriter().print(object.toString());
		} catch (CommodityException e) {
			// TODO Auto-generated catch block
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
		}
	}

	/**
	 * 获取我发布的商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getMyCommodity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		String customer_id = request.getParameter("customer_id");
		List<Commodity> result = null;
		result = service.getMyCommodity(customer_id);
		

		JSONObject object = TransformUtils.packArray(result);
		response.getWriter().print(object.toString());
	}
}
