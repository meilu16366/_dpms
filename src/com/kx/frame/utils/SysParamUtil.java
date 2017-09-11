package com.kx.frame.utils;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.time.DateFormatUtils;

import com.kx.frame.sys.beans.ListValue;
import com.kx.frame.sys.beans.Parameter;

/**
 * 系统参数值列表数据获取
 */
@SuppressWarnings("unchecked")
public class SysParamUtil {
	public static final String SYS_PARAM = "SYS_PARAM";
	public static final String SYS_LISTVALUE = "SYS_LISTVALUE";
	private static ServletContext context;
	
	public static void setServletContext(ServletContext cont) {
		context = cont;
	}
	/**
	 * 获得系统参数
	 * @param name 参数名
	 * @return
	 */
	public static Parameter getParameter(String name) {
		Map<String,Parameter> paramMap = (Map<String, Parameter>) context.getAttribute(SYS_PARAM);
		Parameter param = paramMap.get(name);
		if(param == null) {
			param = new Parameter();
		}
		return param;
	}
	
	/**
	 * 获得值列表数据
	 * @param code 代码类型
	 * @return
	 */
	public static Map<String,ListValue> getListValue(String code) {
		Map<String,Map<String,ListValue>> listValMap = (Map<String, Map<String, ListValue>>) context.getAttribute(SYS_LISTVALUE);
		return listValMap.get(code);
	}
	
	public static Map<String ,Object> getData(Date start, Date end, int minute,String fmt){
		Map<String , Object> map= new LinkedHashMap<String, Object>();
		while(!start.after(end)) {
			map.put(DateFormatUtils.format(start,fmt), null);
			start = new Date(start.getTime()+minute*60*1000);
		}
		return map;
	}
}
