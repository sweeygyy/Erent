package com.erent.customer.web.servlte.admin;

import com.erent.customer.domain.Customer;
import com.erent.customer.domain.admin.Administrator;
import com.erent.customer.service.CustomerException;
import com.erent.customer.service.admin.AdminException;
import com.erent.customer.service.admin.AdminService;
import com.erent.utils.BaseServlet;
import com.erent.utils.CommonUtils;
import com.erent.utils.TransformUtils;

import net.sf.json.JSONObject;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdministratorServlet extends BaseServlet{
	AdminService service = new AdminService();
	
	/**
	 * 管理员登录逻辑
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Administrator form = CommonUtils.toBean(request.getParameterMap(),
				Administrator.class);
		try {
			Administrator admin = service.login(form);
			JSONObject object = TransformUtils.packObject(admin);
			response.getWriter().write(object.toString());
		} catch (AdminException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().write(object.toString());
		}
		return "";

	}
	
	
	/**
	 * 管理员修改密码逻辑
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String alterPwd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Administrator form = CommonUtils.toBean(request.getParameterMap(),
				Administrator.class);
		try {
			Administrator administrator = service.alterPassword(form,
					request.getParameter("newPassword"));
			JSONObject object = TransformUtils.packObject(administrator);
			response.getWriter().write(object.toString());
		} catch (AdminException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().write(object.toString());
		}
		return null;
	}
	
}
