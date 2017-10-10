package com.kx.frame.services;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

/**
 *  数据导出处理接口
 */
public interface ExportHandler<T> {
	/**
	 * 处理方法
	 * @param row 当前行
	 * @param data 当前行数据
	 * @param styles 单元格格式，[居左,居中,居右]
	 */
	void dealOneRow(Row row,T data,CellStyle[] styles);
}
