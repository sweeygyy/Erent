package com.erent.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class TomcatListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO 自动生成的方法存根
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// 注册日期转换器
		System.out.println("注册日期转换器");
		ConvertUtils.register(new Converter() {
			public Object convert(Class arg0, Object arg1) {
				if (arg1 == null) {
					return null;
				}
				if (!(arg1 instanceof String)) {
					if (!(arg1 instanceof Date)) {
						throw new ConversionException("非法类型，无法转换!");
					} else {
						return arg1;
					}
				}
				String str = (String) arg1;
				if (str.trim().equals("")) {
					return null;
				}
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return sd.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}

			}

		}, Date.class);
	}

}
