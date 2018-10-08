package com.erent.commodity.web.servlet.admin;



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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.erent.commodity.domain.Commodity;
import com.erent.commodity.service.CommodityException;
import com.erent.commodity.service.CommodityService;
import com.erent.utils.BaseServlet;
import com.erent.utils.JsonDateValueProcessor;
import com.erent.utils.TransformUtils;

/**
 * @author acer-pc
 *
 */

public class AdminCommodityServlet extends BaseServlet {
	private CommodityService service = new CommodityService();
	
	
	/**
	 * 管理员获取商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getCommodity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");

		List<Commodity> result = null;
		try {
			result = service.adminGetCommodity(nowPage, pageSize);
			response.getWriter().print(TransformUtils.returnArrayString(result));
		} catch (CommodityException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
		}		
	}
	
	/**
	 * 管理员删除商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteCommodity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String commodityId = request.getParameter("commodity_id");
		try {
			service.remove(commodityId);
			JSONObject object = new JSONObject();
			object.accumulate("flag", true);
			response.getWriter().print(object.toString());
		} catch (CommodityException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
		}		
	}
	
	/**
	 * 管理员搜索功能
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void search(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String keyword = request.getParameter("search");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		
		List<Commodity> result = null;
		try {
			result = service.adminSearch(keyword, nowPage, pageSize);
			response.getWriter().print(TransformUtils.returnArrayString(result));
		} catch (CommodityException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
		}
	}	
}
