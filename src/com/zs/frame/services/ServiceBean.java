package com.zs.frame.services;


import org.springframework.context.ApplicationContext;

/**
 * 获取spring bean
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
public class ServiceBean<T> {
	
	private static ApplicationContext applicationContext;
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String id){
		return (T) applicationContext.getBean(id);
	}
	
	public static void setApplicationContext(ApplicationContext appContext){
		applicationContext = appContext;
	}
}
