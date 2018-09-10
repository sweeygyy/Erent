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
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");

		List<Commodity> result = null;
		try {
			result = service.adminGetCommodity(nowPage, pageSize);
//			response.setHeader( "Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
//			response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
//			response.addHeader( "Access-Control-Allow-Methods", "POST,OPTIONS,GET" ); //可以访问此域的脚本方法类型
//			response.addHeader( "Access-Control-Max-Age", "1000" );
			response.getWriter().print(TransformUtils.returnArrayString(result));
		} catch (CommodityException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
		}		
	}
}
