package com.kx.frame.utils;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;

public class MapControllerUtil {

		/**
		 * 获取指定时间集合
		 * @param start 开始时间
		 * @param end   结束时间
		 * @param minute 间隔时间
		 * @param fmt  时间类型
		 * @return
		 */
	public static Map<String ,Object> getData(Date start, Date end, int minute,String fmt){
		Map<String , Object> map= new LinkedHashMap<String, Object>();
		while(!start.after(end)) {
			map.put(DateFormatUtils.format(start,fmt), null);
			start = new Date(start.getTime()+minute*60*1000);
		}
		return map;
	}
}
