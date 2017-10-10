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

import com.kx.base.beans.Inverter;
import com.kx.da.beans.Inverterhis;
import com.kx.da.beans.SearchParam;
import com.kx.frame.def.CollectDef;
import com.kx.frame.services.ExportController;
import com.kx.frame.services.ExportHandler;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.utils.EntityUtil;
import com.kx.frame.utils.Pager;
import com.kx.frame.utils.SysParamUtil;


/**
 *  逆变器历史数据查询
 */
@Controller
@RequestMapping("/da/inverterhis")
public class ListInverterhisController extends ExportController{

	@Autowired
	private IBaseDao<Inverterhis> daoSrv;
	
	@RequestMapping("/page")
	public String page(Model model) {
		String stime = DateFormatUtils.format(new Date(), "yyyy-MM-dd")+" "+CollectDef.RADIATION_START;
		String etime = DateFormatUtils.format(new Date(), "yyyy-MM-dd")+" "+CollectDef.RADIATION_END;
		model.addAttribute("stime", stime);
		model.addAttribute("etime", etime);
		return "/report/listinverterhis";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public Object listNBQhis(Integer pageNo,Integer pageSize, SearchParam searchParam) throws Exception {
		String[] sqld = EntityUtil.sqlStrByName(Inverterhis.class, Inverter.class);
		String hql = sqld[0];
		String counthql = sqld[1];
		if(searchParam != null) {
			if(StringUtils.isNotEmpty(searchParam.getIds())) {
				hql += " and a.inverterid in ("+searchParam.getIds()+")";
				counthql += " and a.inverterid in ("+searchParam.getIds()+")";
			}
			if(StringUtils.isNotEmpty(searchParam.getStime())) {
				hql += " and a.ctime>='"+searchParam.getStime()+"' ";
				counthql += " and a.ctime>='"+searchParam.getStime()+"' ";
			}
			if(StringUtils.isNotEmpty(searchParam.getEtime())) {
				hql += " and a.ctime<='"+searchParam.getEtime()+"' ";
				counthql += " and a.ctime<='"+searchParam.getEtime()+"' ";
			}
			if(StringUtils.isNotEmpty(searchParam.getName())) {
				hql += " and b.name like '%"+searchParam.getName()+"%' ";
				counthql += " and b.name like '%"+searchParam.getName()+"%' ";
			}
		}
		hql += " order by a.inverterid,a.ctime";
		Pager<Object> pager = daoSrv.findBySql(hql, counthql, pageNo, pageSize);
		Map<String,Object> total = new HashMap<String,Object>();
		total.put("total", pager.getRowCount());
		total.put("rows", EntityUtil.toEnty(pager.getDatas(),Inverterhis.class));
		return total;
	}
	
	@RequestMapping("/export")
	public void exp(SearchParam searchParam,HttpServletResponse response) throws Exception {
		String hql = EntityUtil.sqlStrByName(Inverterhis.class, Inverter.class)[0];
		if(searchParam != null) {
			if(StringUtils.isNotEmpty(searchParam.getIds())) {
				hql += " and a.inverterid in ("+searchParam.getIds()+") ";
			}
			if(StringUtils.isNotEmpty(searchParam.getStime())) {
				hql += " and a.ctime>='"+searchParam.getStime()+"' ";
			}
			if(StringUtils.isNotEmpty(searchParam.getEtime())) {
				hql += " and a.ctime<='"+searchParam.getEtime()+"' ";
			}
			if(StringUtils.isNotEmpty(searchParam.getName())) {
				hql += " and b.name like '%"+searchParam.getName()+"%' ";
			}
		}
		hql += " order by a.inverterid,a.ctime";
		
		List<Object> datasd = daoSrv.findBySql(hql);
		List<Inverterhis> nbqs = EntityUtil.toEnty(datasd, Inverterhis.class);
		Workbook wb = this.loadModelFile("逆变器历史数据.xls");
		fillWorkbook(wb,nbqs, new ExportHandler<Inverterhis>() {
			@Override
			public void dealOneRow(Row row, Inverterhis data, CellStyle[] styles) {
				
				CellStyle styleleft = styles[0];
				CellStyle stylecenter = styles[1];
				CellStyle styleright = styles[2];
				styleright.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
				Cell cell = row.createCell(0);
				cell.setCellStyle(styleleft);
				cell.setCellValue(data.getName());
				
				cell = row.createCell(1);
				cell.setCellStyle(stylecenter);
				cell.setCellValue(DateFormatUtils.format(data.getCtime(), "yyyy-MM-dd HH:mm:ss"));
				cell = row.createCell(2);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getTotalCapacity());
				
				cell = row.createCell(3);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getDayCapacity());
				
				cell = row.createCell(4);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getDcPower());
				
				cell = row.createCell(5);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getAcPower());
				cell = row.createCell(6);
				cell.setCellStyle(styleright);
		
				fillCell(cell, data.getUab());
				
				cell = row.createCell(7);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getUbc());
				
				cell = row.createCell(8);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getUca());
				
				cell = row.createCell(9);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getIa());
				
				cell = row.createCell(10);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getIb());
				
				cell = row.createCell(11);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getIc());
				
				cell = row.createCell(12);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getGridFrequency());
				
				cell = row.createCell(13);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getPowerFactor());
				
				cell = row.createCell(14);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getInvertorTemp());
				
				cell = row.createCell(15);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getReactivePower());
				
				cell = row.createCell(16);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDcu1());
				
				cell = row.createCell(17);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDcu2());
				
				cell = row.createCell(18);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDcu3());
				
				cell = row.createCell(19);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDcu4());
				
				cell = row.createCell(20);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDcu5());
				
				cell = row.createCell(21);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDcu6());
				
				cell = row.createCell(22);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDcu7());
				
				cell = row.createCell(23);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDcu8());
				
				cell = row.createCell(24);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDci1());
				
				cell = row.createCell(25);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDci2());
				
				cell = row.createCell(26);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDci3());
				
				cell = row.createCell(27);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDci4());
				
				cell = row.createCell(28);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDci5());
				
				cell = row.createCell(29);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDci6());
				
				cell = row.createCell(30);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDci7());
				
				cell = row.createCell(31);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getDci8());
				
				cell = row.createCell(32);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getEfficient());
			}
		});
		String name=searchParam.getStime().split(" ")[1]+"至"+searchParam.getEtime().split(" ")[1]+" 监控系统逆变器报表";
		setTitle(wb, name);
		this.outputExcel(wb, searchParam.getEtime().split(" ")[0]+" 监控系统逆变器报表.xls", response);
	}
	
}
