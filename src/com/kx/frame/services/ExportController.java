package com.kx.frame.services;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.kx.frame.utils.NumberFormatUtil;


public abstract class ExportController {
	
	/**
	 * 加载模板文件
	 * 
	 * @return
	 * @throws Exception
	 */
	protected final Workbook loadModelFile(String filePath) throws Exception {
		InputStream in = new FileInputStream(ServiceBean.getServletContext().getRealPath("/user/report")+"/" + filePath);
		Workbook wb = new HSSFWorkbook(in);
		return wb;
	}
	
	/**
	 * 导出
	 * @param wb
	 * @param name
	 * @param response
	 * @throws Exception
	 */
	protected final void outputExcel(Workbook wb,String name,HttpServletResponse response) throws Exception {
		long s = System.currentTimeMillis();
		response.setContentType("application/vnd.ms-excel");
		String  fileName=new String(name.getBytes("UTF-8"),"UTF-8");
		System.out.println(fileName);
		response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(name.getBytes("UTF-8"),"UTF-8")+"\"");
		OutputStream out = response.getOutputStream();
		wb.write(out);
		out.flush();
		out.close();
		System.out.println("导出耗时:"+(new Date().getTime()-s));
	}
	
	/**
	 * fill Workbook
	 * @param wb
	 * @param ls
	 * @throws Exception
	 */
	protected final <T> void fillWorkbook(Workbook wb,Collection<T> ls,ExportHandler<T> handler) throws Exception {
		fillWorkbook(wb,ls,2,handler);
	}
	/**
	 * fill Workbook
	 * @param wb
	 * @param ls
	 * @param dataStart
	 * @param rowStart
	 * @throws Exception
	 */
	protected final <T> void fillWorkbook(Workbook wb,Collection<T> ls,int rowStart,ExportHandler<T> handler) throws Exception {
		int index = rowStart;
		Sheet sheet = wb.getSheetAt(0);
		CellStyle style = wb.createCellStyle();
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(HSSFColorPredefined.BLACK.getIndex());
		style.setRightBorderColor(HSSFColorPredefined.BLACK.getIndex());
		style.setLeftBorderColor(HSSFColorPredefined.BLACK.getIndex());
		style.setTopBorderColor(HSSFColorPredefined.BLACK.getIndex());
		//左对齐
		CellStyle styleleft = wb.createCellStyle();
		styleleft.cloneStyleFrom(style);
		styleleft.setAlignment(HorizontalAlignment.LEFT);
		//居中
		CellStyle stylecenter = wb.createCellStyle();
		stylecenter.cloneStyleFrom(style);
		stylecenter.setAlignment(HorizontalAlignment.CENTER);
		//右对齐
		CellStyle styleright = wb.createCellStyle();
		styleright.cloneStyleFrom(style);
		
		styleright.setAlignment(HorizontalAlignment.RIGHT);
		for(T t:ls) {
			Row row = sheet.createRow(index);
			handler.dealOneRow(row, t, new CellStyle[]{styleleft,stylecenter,styleright});
			index++;
		}
	}
	
	/**
	 * fill Workbook
	 * @param wb
	 * @param ls
	 * @throws Exception
	 */
	protected final <T> void fillWorkbook(Workbook wb,List<T> ls) throws Exception {
		int index = 2;
		Sheet sheet = wb.getSheetAt(0);
		CellStyle style = wb.createCellStyle();
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setRightBorderColor(HSSFColorPredefined.BLACK.getIndex());
		style.setLeftBorderColor(HSSFColorPredefined.BLACK.getIndex());
		style.setTopBorderColor(HSSFColorPredefined.BLACK.getIndex());
		style.setBottomBorderColor(HSSFColorPredefined.BLACK.getIndex());
		
		//左对齐
		CellStyle styleleft = wb.createCellStyle();
		styleleft.cloneStyleFrom(style);
		styleleft.setAlignment(HorizontalAlignment.LEFT);
		//居中
		CellStyle stylecenter = wb.createCellStyle();
		stylecenter.cloneStyleFrom(style);
		stylecenter.setAlignment(HorizontalAlignment.CENTER);
		//右对齐
		CellStyle styleright = wb.createCellStyle();
		styleright.cloneStyleFrom(style);
		styleright.setAlignment(HorizontalAlignment.RIGHT);
		Field[] fs = null;
		for(T t:ls) {
			Row row = sheet.createRow(index);
			if(fs==null)fs = t.getClass().getDeclaredFields();
			for (int i = 2; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true);
				Object val = f.get(t);
				Cell cell = row.createCell(i-2);
				if (val instanceof Date) {
					cell.setCellStyle(stylecenter);
					cell.setCellValue(DateFormatUtils.format((Date)val, "yyyy-MM-dd HH:mm:ss"));
				}else if(val instanceof String){
					cell.setCellStyle(styleleft);
					cell.setCellValue(val+"");
				}else if(val instanceof Double){
					cell.setCellStyle(styleright);
					cell.setCellValue(NumberFormatUtil.format((Double)val, 2));
				}else if(val instanceof Long){
					cell.setCellStyle(styleright);
					cell.setCellValue(NumberFormatUtil.format((Long)val, 2));
				}
			}
			index++;
		}
	}
	/**
	 * 填充单元格
	 * @param cell
	 * @param data
	 */
	protected final void fillCell(Cell cell, Object data) {
		if(data==null) {
			cell.setCellValue("");
		}else if(data instanceof Integer) {
			cell.setCellValue((Integer)data);
		}else if(data instanceof Double){
			cell.setCellValue((Double)data);
		}else if(data instanceof BigDecimal){
			cell.setCellValue(((BigDecimal)data).doubleValue());
		}else if(data instanceof String){
			cell.setCellValue((String)data);
		}
	}
	/**
	 * 填充单元格
	 * @param cell
	 * @param data
	 */
	protected final void fillCell(Cell cell,Date date,String fmt) {
		if(date == null) {
			cell.setCellValue("");
		}else {
			cell.setCellValue(DateFormatUtils.format(date, fmt));
		}
	}
	/**
	 * 添加报表标题
	 * @param wb
	 * @param name
	 */
	protected  final void setTitle(Workbook wb, String name) {
		Sheet sheet = wb.getSheetAt(0);
		Row r0 = sheet.getRow(0);
		r0.getCell(0).setCellValue(name);
	}
}
