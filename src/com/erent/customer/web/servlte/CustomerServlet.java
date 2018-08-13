package com.erent.customer.web.servlte;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.erent.customer.domain.Customer;
import com.erent.customer.service.CustomerException;
import com.erent.customer.service.CustomerService;
import com.erent.utils.BaseServlet;
import com.erent.utils.CommonUtils;
import com.erent.utils.TransformUtils;

public class CustomerServlet extends BaseServlet {
	CustomerService service = new CustomerService();

	/**
	 * 注册方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String regist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Customer form = CommonUtils.toBean(request.getParameterMap(),
				Customer.class);

		System.out.println(form);
		// 输入校验
		if (form.getCustomer_id() == null
				|| "".equals(form.getCustomer_id().trim())
				|| form.getTel() == null || "".equals(form.getTel().trim())
				|| form.getPassword() == null
				|| "".equals(form.getPassword().trim())) {
			JSONObject object = new JSONObject();
			object.accumulate("flag", false);
			response.getWriter().print(object.toString());
			return "";
		}
		try {
			Customer customer = service.regist(form);
			JSONObject object = TransformUtils.packObject(customer);
			response.getWriter().write(object.toString());
		} catch (CustomerException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().write(object.toString());
			System.out.println(object.toString());
		}
		return "";
	}

	/**
	 * 登录方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Customer form = CommonUtils.toBean(request.getParameterMap(),
				Customer.class);
		try {
			Customer customer = service.login(form);
			JSONObject object = TransformUtils.packObject(customer);
			response.getWriter().write(object.toString());
		} catch (CustomerException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().write(object.toString());
		}
		return "";
	}

	/**
	 * 通过学号获取用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getCustomerInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Customer form = CommonUtils.toBean(request.getParameterMap(),
				Customer.class);
		try {
			Customer customer = service.getCustomerInfo(form);
			request.setAttribute("customer", customer);
			JSONObject object = TransformUtils.packObject(customer);
			System.out.println(object);
			response.getWriter().write(object.toString());
		} catch (CustomerException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().write(object.toString());
		}
		return "forward:/modify.jsp";
	}

	/**
	 * 修改用户普通信息方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String modify(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Customer form = CommonUtils.toBean(request.getParameterMap(),
				Customer.class);
		try {
			Customer customer = service.modify(form);
			// JSONObject object = new JSONObject();
			// object.accumulate("flag", true);
			JSONObject object = TransformUtils.packObject(customer);
			response.getWriter().write(object.toString());
		} catch (CustomerException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().write(object.toString());
		}
		return null;
	}

	/**
	 * 修改用户手机方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String alterTel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Customer form = CommonUtils.toBean(request.getParameterMap(),
				Customer.class);
		try {
			Customer customer = service.alterTel(form,
					request.getParameter("newTel"));
			JSONObject object = TransformUtils.packObject(customer);
			response.getWriter().write(object.toString());
		} catch (CustomerException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().write(object.toString());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改用户密码方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String alterPassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Customer form = CommonUtils.toBean(request.getParameterMap(),
				Customer.class);
		try {
			Customer customer = service.alterPassword(form,
					request.getParameter("newPassword"));
			JSONObject object = TransformUtils.packObject(customer);
			response.getWriter().write(object.toString());
		} catch (CustomerException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().write(object.toString());
			e.printStackTrace();
		}
		return null;
	}

	public CustomerServlet() {
		super();
	}

	public void destroy() {
		super.destroy();

	}

	public void init() throws ServletException {
		// 注册日期转换器
		ConvertUtils.register(new Converter() {
			public Object convert(Class arg0, Object arg1) {
				if (arg1 == null) {
					return null;
				}
				if(arg1 instanceof Date) {
					return arg1;
				}
				if (!(arg1 instanceof String)) {
					throw new ConversionException("只支持字符串转换 !");
				}
				String str = (String) arg1;
				if (str.trim().equals("")) {
					return null;
				}
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				try {
					System.out.println(str);
					System.out.println(sd.parse(str));
					return sd.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}

			}

		}, Date.class);
	}

	// private void fillInfo(Customer customer) {
	// for()
	// }

}
