package com.kx.frame.utils;

import java.util.List;

/**
 * 分页
 */
public class Pager<T> {
	/**当前页码 */
	private int pageNo;
	/**每页显示数*/
	private int pageSize;
	/**总页数*/
	private int pageCount;
	/**总数*/
	private int rowCount;
	/**单页所有数据*/
	private List<T> datas;
	
	public Pager(int pageNo,int pageSize){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		if(pageSize < 1) pageCount = 0;
		if(rowCount % pageSize == 0){
			pageCount = (rowCount / pageSize);
		} else {
			pageCount = (rowCount / pageSize + 1);
		}
		this.rowCount = rowCount;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	
}
