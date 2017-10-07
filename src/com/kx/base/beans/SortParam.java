package com.kx.base.beans;

import org.apache.commons.lang.StringUtils;

/**
 */
public class SortParam {
	private String sort;
	private String order;
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	/**
	 * 是否排序
	 * @return
	 */
	public boolean isSort() {
		return StringUtils.isNotEmpty(sort);
	}
	/**
	 * 获得排序sql
	 * @return
	 */
	public String SortStr() {
		return " order by " + sort + " " + order;
	}
}
