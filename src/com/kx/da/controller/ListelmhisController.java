package com.kx.da.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kx.base.beans.ElectricityMeter;
import com.kx.da.beans.Elmhis;
import com.kx.da.beans.SearchParam;
import com.kx.frame.def.CollectDef;
import com.kx.frame.services.ExportController;
import com.kx.frame.services.ExportHandler;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.utils.EntityUtil;
import com.kx.frame.utils.Pager;


/**
 *  电表历史数据查询
 */
@Controller
@RequestMapping("/da/elmhis")
public class ListelmhisController extends ExportController{

	@Autowired
	private IBaseDao daoSrv;
	@RequestMapping("/page")
	public String page(Model model) {
		String stime = DateFormatUtils.format(new Date(), "yyyy-MM-dd")+" "+CollectDef.RADIATION_START;
		String etime = DateFormatUtils.format(new Date(), "yyyy-MM-dd")+" "+CollectDef.RADIATION_END;
		model.addAttribute("stime", stime);
		model.addAttribute("etime", etime);
		return "/report/listelmhis";
	}
	@ResponseBody
	@RequestMapping("/list")
	public Object listDBhis(Integer pageNo,Integer pageSize,SearchParam searchParam) throws Exception {
		String[] sqld = EntityUtil.sqlStrByName(Elmhis.class, ElectricityMeter.class);
		String hql = sqld[0];
		String counthql = sqld[1];
		if(searchParam != null) {
			if(StringUtils.isNotEmpty(searchParam.getIds())) {
				hql += " and a.elmid in ("+searchParam.getIds()+")";
				counthql += " and a.elmid in ("+searchParam.getIds()+")";
			}
			if(StringUtils.isNotEmpty(searchParam.getStime())) {
				hql += " and a.collecttime>='"+searchParam.getStime()+"' ";
				counthql += " and a.collecttime>='"+searchParam.getStime()+"' ";
			}
			if(StringUtils.isNotEmpty(searchParam.getEtime())) {
				hql += " and a.collecttime<='"+searchParam.getEtime()+"' ";
				counthql += " and a.collecttime<='"+searchParam.getEtime()+"' ";
			}
			if(StringUtils.isNotEmpty(searchParam.getName())) {
				hql += " and b.name like '%"+searchParam.getName()+"%' ";
				counthql += " and b.name like '%"+searchParam.getName()+"%' ";
			}
		}
		hql += " order by a.elmid,a.collecttime";
		Pager<Object> pager = daoSrv.findBySql(hql, counthql, pageNo, pageSize);
		Map<String,Object> total = new HashMap<String,Object>();
		total.put("total", pager.getRowCount());
		total.put("rows", EntityUtil.toEnty(pager.getDatas(),Elmhis.class));
		return total;
	}
	
	@RequestMapping("/export")
	public void exp(SearchParam searchParam,HttpServletResponse response) throws Exception {
		String hql = EntityUtil.sqlStrByName(Elmhis.class, ElectricityMeter.class)[0];
		if(searchParam != null) {
			if(StringUtils.isNotEmpty(searchParam.getIds())) {
				hql += " and a.elmid in ("+searchParam.getIds()+") ";
			}
			if(StringUtils.isNotEmpty(searchParam.getStime())) {
				hql += " and a.collecttime>='"+searchParam.getStime()+"' ";
			}
			if(StringUtils.isNotEmpty(searchParam.getEtime())) {
				hql += " and a.collecttime<='"+searchParam.getEtime()+"' ";
			}
			if(StringUtils.isNotEmpty(searchParam.getName())) {
				hql += " and b.name like '%"+searchParam.getName()+"%' ";
			}
		}
		hql += " order by a.elmid,a.collecttime";
		List<Object> datd = daoSrv.findBySql(hql);
		List<Elmhis> DBs = EntityUtil.toEnty(datd,Elmhis.class);
		Workbook wb = this.loadModelFile("电表历史数据.xls");
		fillWorkbook(wb,DBs, new ExportHandler<Elmhis>() {
			@Override
			public void dealOneRow(Row row, Elmhis data, CellStyle[] styles) {
				CellStyle styleleft = styles[0];
				CellStyle stylecenter = styles[1];
				CellStyle styleright = styles[2];
				styleright.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
				Cell cell = row.createCell(0);
				cell.setCellStyle(styleleft);
				cell.setCellValue(data.getName());
				
				cell = row.createCell(1);
				cell.setCellStyle(stylecenter);
				cell.setCellValue(DateFormatUtils.format(data.getCollecttime(), "yyyy-MM-dd HH:mm:ss"));
				
				cell = row.createCell(2);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getForwardP());
				
				cell = row.createCell(3);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getForwardReaP());
				
				cell = row.createCell(4);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getBackwardP());
				
				cell = row.createCell(5);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getBackwardReaP());
			}
		});
		String name=searchParam.getStime().split(" ")[1]+"至"+searchParam.getEtime().split(" ")[1]+" 监控系统电表报表";
		setTitle(wb, name);
		this.outputExcel(wb, searchParam.getStime().split(" ")[0]+" 监控系统电表报表.xls", response);
	}


}
