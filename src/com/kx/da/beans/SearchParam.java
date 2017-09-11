package com.kx.da.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import com.kx.frame.def.CollectDef;
import com.kx.frame.utils.SysParamUtil;



public class SearchParam implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String ids;

	private String stime = DateFormatUtils.format(new Date(), "yyyy-MM-dd")+" "+SysParamUtil.getParameter(CollectDef.RADIATION_START).getValue();

	private String etime = DateFormatUtils.format(new Date(), "yyyy-MM-dd")+" "+SysParamUtil.getParameter(CollectDef.RADIATION_END).getValue();

	/**指标查询使用，默认当月*/
	private String month = DateFormatUtils.format(new Date(),"yyyy-MM");
	/**指标查询使用，默认当年*/
	private int year = Calendar.getInstance().get(Calendar.YEAR);
	/**指标查询使用，默认昨天*/
	private String date = DateFormatUtils.format(DateUtils.addDays(new Date(),-1),"yyyy-MM-dd");
	
	/**统计方式 day month year*/
	private String selWay = "day";
	
	private String name;
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getStime() {
		
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSelWay() {
		return selWay;
	}

	public void setSelWay(String selWay) {
		this.selWay = selWay;
	}


}
