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

import com.kx.base.beans.MergePoint;
import com.kx.da.beans.Mergephis;
import com.kx.da.beans.SearchParam;
import com.kx.frame.def.CollectDef;
import com.kx.frame.services.ExportController;
import com.kx.frame.services.ExportHandler;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.utils.EntityUtil;
import com.kx.frame.utils.Pager;


/**
 *  并网点历史数据查询
 */
@Controller
@RequestMapping("/da/mergephis")
public class MergepDataController extends ExportController {
	
	@Autowired
	private IBaseDao<Mergephis> daoSrv;
	
	@RequestMapping("/page")
	public String page(Model model) {
		String stime = DateFormatUtils.format(new Date(), "yyyy-MM-dd")+" "+CollectDef.RADIATION_START;
		String etime = DateFormatUtils.format(new Date(), "yyyy-MM-dd")+" "+CollectDef.RADIATION_END;
		model.addAttribute("stime", stime);
		model.addAttribute("etime", etime);
		return "/report/listMergerPoint";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public Object listMergephis(Integer pageNo,Integer pageSize,SearchParam searchParam) throws Exception {
	
		String[] sqld = EntityUtil.sqlStrByName(Mergephis.class,MergePoint.class);
		
		String hql = sqld[0];
		String counthql = sqld[1];
		if(searchParam != null) {
			if(StringUtils.isNotEmpty(searchParam.getIds())) {
				hql += " and a.mpid in ("+searchParam.getIds()+")";
				counthql += " and a.mpid in ("+searchParam.getIds()+")";
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
		hql += " order by a.mpid,a.ctime";
		Pager<Object> pager = daoSrv.findBySql(hql, counthql, pageNo, pageSize);
		Map<String,Object> total = new HashMap<String,Object>();
		total.put("total", pager.getRowCount());
		total.put("rows", EntityUtil.toEnty(pager.getDatas(),Mergephis.class));
		return total;
	}
	
	@RequestMapping("/export")
	public void exp(SearchParam searchParam,HttpServletResponse response) throws Exception {
		String sql = EntityUtil.sqlStrByName(Mergephis.class,MergePoint.class)[0];
		if(searchParam != null) {
			if(StringUtils.isNotEmpty(searchParam.getIds())) {
				sql += " and a.mpid in ("+searchParam.getIds()+") ";
			}
			if(StringUtils.isNotEmpty(searchParam.getStime())) {
				sql += " and a.ctime>='"+searchParam.getStime()+"' ";
			}
			if(StringUtils.isNotEmpty(searchParam.getEtime())) {
				sql += " and a.ctime<='"+searchParam.getEtime()+"' ";
			}
			if(StringUtils.isNotEmpty(searchParam.getName())) {
				sql += " and b.name like '%"+searchParam.getName()+"%' ";
			}
		}
		sql += " order by a.mpid,a.ctime";
		List<Object> datas = daoSrv.findBySql(sql);
		List<Mergephis> bws = EntityUtil.toEnty(datas,Mergephis.class);
		Workbook wb = this.loadModelFile("并网点历史数据.xls");
		
		fillWorkbook(wb,bws, new ExportHandler<Mergephis>() {
			@Override
			public void dealOneRow(Row row, Mergephis data, CellStyle[] styles) {
				CellStyle styleleft = styles[0];
				CellStyle stylecenter = styles[1];
				CellStyle styleright = styles[2];
				styleright.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
				Cell cell = row.createCell(0);
				cell.setCellStyle(styleleft);
				fillCell(cell,data.getName());
				
				cell = row.createCell(1);
				cell.setCellStyle(stylecenter);
				cell.setCellValue(DateFormatUtils.format(data.getCtime(), "yyyy-MM-dd HH:mm:ss"));
				
				cell = row.createCell(2);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getActivePower());
				cell = row.createCell(3);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getReactivePower());
				cell = row.createCell(4);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getCos());
				cell = row.createCell(5);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getUab());
				cell = row.createCell(6);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getUbc());
				cell = row.createCell(7);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getUca());
				cell = row.createCell(8);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getUa());
				cell = row.createCell(9);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getUb());
				cell = row.createCell(10);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getUc());
				cell = row.createCell(11);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getIa());
				cell = row.createCell(12);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getIb());
				cell = row.createCell(13);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getIc());
				cell = row.createCell(14);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getPa());
				cell = row.createCell(15);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getPb());
				cell = row.createCell(16);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getPc());
				cell = row.createCell(17);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getQa());
				cell = row.createCell(18);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getQb());
				cell = row.createCell(19);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getQc());
				cell = row.createCell(20);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getSa());
				cell = row.createCell(21);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getSb());
				cell = row.createCell(22);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getSc());
				cell = row.createCell(23);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getS());
				cell = row.createCell(24);
				cell.setCellStyle(styleright);
				fillCell(cell,data.getGridFrequency());
			}
		});
		String name=searchParam.getStime().split(" ")[1]+"至"+searchParam.getEtime().split(" ")[1]+" 监控系统并网点报表";
		setTitle(wb, name);
		this.outputExcel(wb, searchParam.getStime().split(" ")[0]+" 监控系统并网点报表.xls", response);
	}
}
