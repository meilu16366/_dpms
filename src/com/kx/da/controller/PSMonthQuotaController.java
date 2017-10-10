package com.kx.da.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kx.base.beans.SortParam;
import com.kx.da.beans.PSquotaDay;
import com.kx.da.services.PSDayQuota;
import com.kx.frame.services.ExportController;
import com.kx.frame.services.ExportHandler;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.utils.DateUtil;



@Controller
@RequestMapping("/da/psmonthquota")
public class PSMonthQuotaController extends ExportController {
	@Autowired
	private IBaseDao daoSrv;
	
	@RequestMapping("/page")
	public String page() {
		return "/report/psmonthReport";
	}
	@ResponseBody
	@RequestMapping("/list")
	public Object quota(String month,SortParam sortParam) throws Exception {

		String hql = "from PSquotaDay where 1=1 ";
		hql += " and ctime>='"+month+"-01' ";
		hql += " and ctime<='"+month+"-"+DateUtil.getMonthLastDay(month)+"' ";
		if(sortParam.isSort()) {
			hql += sortParam.SortStr();
		}else {
			hql += " order by ctime ";
		}
		List<PSquotaDay> ls = daoSrv.findByHql(hql);
		Date start = DateUtils.parseDate(month+"-01", new String[] {"yyyy-MM-dd"});
		Map<String,PSquotaDay> map = getDayMap(start,DateUtil.getMonthLastDay(month));
		for(PSquotaDay one :ls) {
			map.put(DateFormatUtils.format(one.getCtime(), "yyyy-MM-dd"), one);
		}
		return map.values();
	}
	
	/**
	 * 获得日期map
	 * @param s
	 * @param monthcount
	 * @return
	 */
	private Map<String,PSquotaDay> getDayMap(Date s,int monthcount){
		int i = 0;
		Map<String,PSquotaDay> map = new LinkedHashMap<String, PSquotaDay>();
		while(i < monthcount) {
			map.put(DateFormatUtils.format(s, "yyyy-MM-dd"), new PSquotaDay(s));
			s = new Date(s.getTime()+24*60*60*1000);
			i++;
		}
		return map;
	}
	
	@RequestMapping("/exp")
	public void exp(String month,SortParam sortParam,HttpServletResponse response) throws Exception {
		String hql = "from PSquotaDay where 1=1 ";
		hql += " and ctime>='"+month+"-01' ";
		hql += " and ctime<='"+month+"-"+DateUtil.getMonthLastDay(month)+"' ";
		if(sortParam.isSort()) {
			hql += sortParam.SortStr();
		}else {
			hql += " order by ctime ";
		}
		List<PSquotaDay> ls = daoSrv.findByHql(hql);
		Date start = DateUtils.parseDate(month+"-01", new String[] {"yyyy-MM-dd"});
		Map<String,PSquotaDay> map = getDayMap(start,DateUtil.getMonthLastDay(month));
		for(PSquotaDay one :ls) {
			map.put(DateFormatUtils.format(one.getCtime(), "yyyy-MM-dd"), one);
		}
		final CellStyle[] style = {null,null,null};
		final Double[] totals = {0d,0d,0d,0d,
								0d,0d,0d,0d,
								0d,0d,0d,0d};
		final Date nbqMax = new Date();
		final Date bwdMax = new Date();
		final boolean[] hasMax = {false,false};
		Workbook wb = this.loadModelFile("电站生产报表.xls");
		fillWorkbook(wb,map.values(),new ExportHandler<PSquotaDay>() {
			@Override
			public void dealOneRow(Row row, PSquotaDay data, CellStyle[] styles) {
				CellStyle styleleft = style [0] = styles[0];
				CellStyle stylecenter = style [1] = styles[1];
				CellStyle styleright = style [2] = styles[2];
				styleright.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
				totals[0] += data.getNbqdaycap()==null?0:data.getNbqdaycap();
				totals[1] += data.getDbdaycap()==null?0:data.getDbdaycap();
				totals[2] += data.getBwdaycap()==null?0:data.getBwdaycap();
				totals[3] += data.getHours()==null?0:data.getHours();
				if(data.getMaxnbqpower() != null && data.getMaxnbqpower() > totals[4]) {
					totals[4] = data.getMaxnbqpower();
					nbqMax.setTime(data.getMaxnbqtime().getTime());
					hasMax[0] = true;
				}
				if(data.getMaxbwpower() != null && data.getMaxbwpower()>totals[5]) {
					totals[5] = data.getMaxbwpower();
					bwdMax.setTime(data.getMaxbwtime().getTime());
					hasMax[1] = true;
				}
				totals[6] += data.getAvgnbqefficiency()==null?0:data.getAvgnbqefficiency();
				totals[7] += data.getCo2()==null?0:data.getCo2();
				totals[8] += data.getCoal()==null?0:data.getCoal();
				totals[9] += data.getTotalradia()==null?0:data.getTotalradia();
				
				Cell cell = row.createCell(0);
				cell.setCellStyle(stylecenter);
				fillCell(cell,data.getCtime(), "yyyy-MM-dd");
				
				cell = row.createCell(1);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getNbqdaycap());
				
				cell = row.createCell(2);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDbdaycap());
				
				cell = row.createCell(3);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getBwdaycap());
				
				cell = row.createCell(4);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getHours());
				
				cell = row.createCell(5);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getMaxnbqpower());
				
				cell = row.createCell(6);
				cell.setCellStyle(stylecenter);
				fillCell(cell,data.getMaxnbqtime(), "yyyy-MM-dd HH:mm:ss");

				cell = row.createCell(7);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getMaxbwpower());
				
				cell = row.createCell(8);
				cell.setCellStyle(stylecenter);
				fillCell(cell,data.getMaxbwtime(),"yyyy-MM-dd HH:mm:ss");
				
				cell = row.createCell(9);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getAvgnbqefficiency());
				
				cell = row.createCell(10);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getCo2());
				
				cell = row.createCell(11);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getCoal());
				
				cell = row.createCell(12);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getTotalradia());
		}});
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.createRow(sheet.getLastRowNum()+1);
		Row r0 = sheet.getRow(0);
		r0.getCell(0).setCellValue(month+" 监控系统生产月报表");
		Cell cell = row.createCell(0);
		cell.setCellStyle(style[1]);
		cell.setCellValue("合计");
		
		cell = row.createCell(1);
		cell.setCellStyle(style[2]);
		fillCell(cell, totals[0]);
		
		cell = row.createCell(2);
		cell.setCellStyle(style[2]);
		fillCell(cell, totals[1]);
		
		cell = row.createCell(3);
		cell.setCellStyle(style[2]);
		fillCell(cell, totals[2]);
		
		cell = row.createCell(4);
		cell.setCellStyle(style[2]);
		fillCell(cell, totals[3]);
		
		cell = row.createCell(5);
		cell.setCellStyle(style[2]);
		fillCell(cell, totals[4]);
		
		cell = row.createCell(6);
		cell.setCellStyle(style[1]);
		if(hasMax[0]) {
			fillCell(cell, nbqMax, "yyyy-MM-dd HH:mm:ss");
		}else {
			fillCell(cell, null);
		}
		
		cell = row.createCell(7);
		cell.setCellStyle(style[2]);
		fillCell(cell, totals[5]);
		
		cell = row.createCell(8);
		cell.setCellStyle(style[1]);
		if(hasMax[0]) {
			fillCell(cell, bwdMax, "yyyy-MM-dd HH:mm:ss");
		}else {
			fillCell(cell, null);
		}
		
		cell = row.createCell(9);
		cell.setCellStyle(style[2]);
		fillCell(cell, totals[6]);
		
		cell = row.createCell(10);
		cell.setCellStyle(style[2]);
		fillCell(cell, totals[7]);
		
		cell = row.createCell(11);
		cell.setCellStyle(style[2]);
		fillCell(cell, totals[8]);
		
		cell = row.createCell(12);
		cell.setCellStyle(style[2]);
		fillCell(cell, totals[9]);
		
		this.outputExcel(wb, month +" 监控系统生产月报表.xls", response);
	}
	@Autowired
	private PSDayQuota psDayQuota;
	@RequestMapping("/calc")
	public Object calc(String date) throws ParseException {
		psDayQuota.calc(DateUtils.parseDate(date, new String[] {"yyyy-MM-dd"}));
		return "redirect:/da/psmonthquota/page";
	}
	
}
