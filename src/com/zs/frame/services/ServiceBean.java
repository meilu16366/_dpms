package com.zs.frame.services;

import javax.servlet.ServletContext;

import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * 获取spring bean
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
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
