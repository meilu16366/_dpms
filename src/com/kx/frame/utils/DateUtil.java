package com.kx.frame.utils;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;


public class DateUtil {
	
	/**
	 * 获得某月的天数
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public static int getMonthLastDay(String month) throws Exception {
		Date date = DateUtils.parseDate(month, new String[] {"yyyy-MM"});
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 格式化时间字符串
	 * @param str
	 * @param oldfmt 字符串的时间格式
	 * @param newfmt 转成的格式
	 * @return
	 * @throws Exception
	 */
	public static String formatForStr(String str,String oldfmt,String newfmt) throws Exception {
		return DateFormatUtils.format(DateUtils.parseDate(str, new String[] {oldfmt}),newfmt);
	}
	
	
	public static void main(String[] args) {
		try {
			System.out.println(getMonthLastDay("2017-02"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
