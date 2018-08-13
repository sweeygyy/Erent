package com.erent.commodity.web.servlet.admin;



import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.erent.commodity.domain.Commodity;
import com.erent.commodity.service.CommodityException;
import com.erent.commodity.service.CommodityService;
import com.erent.utils.BaseServlet;
import com.erent.utils.TransformUtils;

public class AdminCommodityServlet extends BaseServlet {
	private CommodityService service = new CommodityService();
	
	public void getCommodity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");

		List<Commodity> result = null;
		try {
			result = service.adminGetCommodity(nowPage, pageSize);
		} catch (CommodityException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
		}
		response.setHeader( "Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
		response.addHeader( "Access-Control-Allow-Methods", "POST,OPTIONS,GET" ); //可以访问此域的脚本方法类型
		response.addHeader( "Access-Control-Max-Age", "1000" );
		JSONObject object = TransformUtils.packArray(result);
		response.getWriter().print(object.toString());
	}
}
