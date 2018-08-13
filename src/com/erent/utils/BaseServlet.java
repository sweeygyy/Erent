package com.erent.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
		protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String methodName = request.getParameter("method");
		if(methodName == null || methodName.trim().isEmpty()) {
			throw new RuntimeException("没有给定参数method,无法确定您想要调用的方法！");
		}
		Class clazz = this.getClass();
		Method method = null;
		try {
			method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("您要使用的方法:" + methodName + "不存在");
		}
		String result = null;
		try {
			result = (String)method.invoke(this, request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("你调用的方法：" + methodName + "内部出现了异常");
		}
		
		if(result == null || result.trim().isEmpty()) {
			return;
		}
		if(result.contains(":")) {
			int index = result.indexOf(":");
			String s = result.substring(0, index);
			String path = result.substring(index+1);
			if(s.equals("forward")) {
				request.getRequestDispatcher(path).forward(request, response);
			} else if(s.equals("redirect")) {
				response.sendRedirect(request.getContextPath() + path);
			} else {
				throw new RuntimeException("您指定的操作:" + s + "，当前版本不支持");
			}
		} else {
			request.getRequestDispatcher(result).forward(request, response);
		}
//		super.service(request, response);
	}
}
