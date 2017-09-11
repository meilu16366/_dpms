package com.kx.frame.services;

import javax.servlet.ServletContext;

import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * 获取spring bean
 */
public class ServiceBean<T> {
	
	
	private static ServletContext context;
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String id){
		return (T) WebApplicationContextUtils.getWebApplicationContext(context).getBean(id);
	}
	
	public static void setServletContext(ServletContext sc){
		context = sc;
	}
	
	public static ServletContext getServletContext() {
		return context;
	}
}
