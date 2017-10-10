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

import com.kx.base.beans.EmiInfo;
import com.kx.da.beans.Emihis;
import com.kx.da.beans.SearchParam;
import com.kx.frame.def.CollectDef;
import com.kx.frame.services.ExportController;
import com.kx.frame.services.ExportHandler;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.utils.EntityUtil;
import com.kx.frame.utils.Pager;


/**
 *  环境监测仪历史数据查询
 */
@Controller
@RequestMapping("/da/emihis")
public class ListEmihisController extends ExportController{
	
	@Autowired
	private IBaseDao daoSrv;
	@RequestMapping("/page")
	public String page(Model model) {
		String stime = DateFormatUtils.format(new Date(), "yyyy-MM-dd")+" "+CollectDef.RADIATION_START;
		String etime = DateFormatUtils.format(new Date(), "yyyy-MM-dd")+" "+CollectDef.RADIATION_END;
		model.addAttribute("stime", stime);
		model.addAttribute("etime", etime);
		return "/report/listemihis";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public Object listQXYhis(Integer pageNo,Integer pageSize,SearchParam searchParam) throws Exception {
		String[] sqld = EntityUtil.sqlStrByName(Emihis.class, EmiInfo.class);
		String hql = sqld[0];
		String counthql = sqld[1];
		if(searchParam != null) {
			if(StringUtils.isNotEmpty(searchParam.getIds())) {
				hql += " and a.emiid in ("+searchParam.getIds()+")";
				counthql += " and a.emiid in ("+searchParam.getIds()+")";
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
		hql += " order by a.emiid,a.ctime";
		Pager<Object> pager = daoSrv.findBySql(hql, counthql, pageNo, pageSize);
		Map<String,Object> total = new HashMap<String,Object>();
		total.put("total", pager.getRowCount());
		total.put("rows", EntityUtil.toEnty(pager.getDatas(),Emihis.class));
		return total;
	}
	
	@RequestMapping("/export")
	public void exp(SearchParam searchParam,HttpServletResponse response) throws Exception {
		String hql = EntityUtil.sqlStrByName(Emihis.class, EmiInfo.class)[0];
		if(searchParam != null) {
			if(StringUtils.isNotEmpty(searchParam.getIds())) {
				hql += " and a.emiid in ("+searchParam.getIds()+") ";
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
		hql += " order by a.emiid,a.ctime";
		List<Object> datd = daoSrv.findBySql(hql);
		List<Emihis> qxys = EntityUtil.toEnty(datd,Emihis.class);
		Workbook wb = this.loadModelFile("环境监测仪历史数据.xls");
		fillWorkbook(wb,qxys, new ExportHandler<Emihis>() {

			@Override
			public void dealOneRow(Row row, Emihis data, CellStyle[] styles) {
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
				fillCell(cell, data.getHumidity());
				
				cell = row.createCell(3);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getTemperature());
				
				
				cell = row.createCell(4);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getPanelTemp());
				
				cell = row.createCell(5);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getInstRadiationIevel());
				
				cell = row.createCell(6);
				cell.setCellStyle(styleright);
				cell.setCellValue(data.getInstRadiationTilt());
				fillCell(cell, data.getInstRadiationTilt());
				
				cell = row.createCell(7);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getAllRadiationIevel());
				
				cell = row.createCell(8);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getAllRadiationTilt());
				
				cell = row.createCell(9);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getWindSpeed());
				
				cell = row.createCell(10);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getWinDir());
				
				cell = row.createCell(11);
				cell.setCellStyle(styleright);
				fillCell(cell, data.getPressure());
			}
		});
		String name=searchParam.getStime().split(" ")[1]+"至"+searchParam.getEtime().split(" ")[1]+" 监控系统环境监测仪报表";
		setTitle(wb, name);
		this.outputExcel(wb, searchParam.getStime().split(" ")[0]+" 监控系统环境监测仪报表.xls", response);
		
	}

}
