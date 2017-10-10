package com.kx.da.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kx.base.beans.Inverter;
import com.kx.da.beans.InverterQuotaDay;
import com.kx.da.beans.SearchParam;
import com.kx.da.services.InverterDayQuota;
import com.kx.frame.def.KXFrameDef;
import com.kx.frame.services.ExportController;
import com.kx.frame.services.ExportHandler;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.utils.DateUtil;
import com.kx.frame.utils.Pager;
import com.kx.frame.utils.SysParamUtil;


@Controller
@RequestMapping("/da/inverterquota")
public class InverterQuotaController extends ExportController {
	
	@Autowired
	private IBaseDao daoSrv;
	@RequestMapping("/page")
	public String page() {
		
		return "/report/inverterReport";
	}
	/**
	 * 逆变器月指标列表
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Object listMonth(Model model,Integer pageNo,Integer pageSize,@ModelAttribute("searchParam") SearchParam searchParam) throws Exception {
		if(pageNo == null) {
			pageNo = 1;
		}
		if(pageSize == null){
			pageSize = NumberUtils.toInt(SysParamUtil.getParameter(KXFrameDef.PAGE_SIZE).getValue());
		}
		String sql = "";
		String hql = "from Inverter where 1=1 ";
		String counthql = "select count(id) from Inverter where 1=1 ";
		if(searchParam != null) {
			if(searchParam.getSelWay().equals("day")) {
				sql = "select a.inverterdayid,b.name,a.ctime,a.daycap,"
					+ " a.efficiency,a.hour,a.maxdcpower,a.maxacpower,a.avgtemperature,a.inverterid "
					+ " from da_inverterday a left join f_inverter b on a.inverterid=b.collectid where 1=1 ";
				if(StringUtils.isNotEmpty(searchParam.getIds())) {
					sql += " and a.inverterid in ("+searchParam.getIds()+") ";
					hql += " and id in ("+searchParam.getIds()+") ";
					counthql += " and id in ("+searchParam.getIds()+") ";
				}
				if(StringUtils.isNotEmpty(searchParam.getDate())) {
					sql += " and a.ctime='"+searchParam.getDate()+"' ";
				}
				if(StringUtils.isNotEmpty(searchParam.getName())) {
					
				}
			}else if(searchParam.getSelWay().equals("month")) {
				sql = "select 'a' as d,b.name,1 as cty,sum(a.daycap),"
						+ " avg(a.efficiency),sum(a.hour),max(a.maxdcpower),max(a.maxacpower),"
						+ " avg(a.avgtemperature),a.inverterid "
						+ " from da_inverterday a left join f_inverter b on a.inverterid=b.collectid where 1=1 ";
				if(StringUtils.isNotEmpty(searchParam.getIds())) {
					sql += " and a.inverterid in ("+searchParam.getIds()+") ";
					hql += " and id in ("+searchParam.getIds()+") ";
					counthql += " and id in ("+searchParam.getIds()+") ";
				}
				if(StringUtils.isNotEmpty(searchParam.getMonth())) {
					sql += " and a.ctime>='"+searchParam.getMonth()+"-01' ";
					sql += " and a.ctime<='"+searchParam.getMonth()+"-"+DateUtil.getMonthLastDay(searchParam.getMonth())+"' ";
				}
				sql += " group by a.inverterid";
			}else {
				sql = "select 'a' as da,b.name,1 as cty,sum(a.daycap),"
						+ " avg(a.efficiency),sum(a.hour),max(a.maxdcpower),max(a.maxacpower),"
						+ " avg(a.avgtemperature),a.inverterid "
						+ " from da_inverterday a left join f_inverter b on a.inverterid=b.collectid where 1=1 ";
				if(StringUtils.isNotEmpty(searchParam.getIds())) {
					sql += " and a.inverterid in ("+searchParam.getIds()+") ";
					hql += " and id in ("+searchParam.getIds()+") ";
					counthql += " and id in ("+searchParam.getIds()+") ";
				}
				
				sql += " and a.ctime>='"+searchParam.getYear()+"-01-01' ";
				sql += " and a.ctime<='"+searchParam.getYear()+"-12-31' ";
				
				sql += " group by a.inverterid";
			}
		}
		hql += "  order by id";
		List<Object[]> quotas = daoSrv.findBySql(sql);
		Map<Integer,Object[]> datas = new HashMap<Integer,Object[]>();
		for(Object[] os : quotas) {
			datas.put((Integer)os[9], os);
		}

		Pager<Object> pager = daoSrv.findByHql(hql, counthql, pageNo, pageSize);
		List<Map<String,Object>> pagerdata = new ArrayList<Map<String,Object>>();
		for(Object info:pager.getDatas()) {
			Long cid = ((Inverter)info).getCollectid();
			Object[] os = datas.get(cid.intValue());
			if(os==null) {
				os = new Object[10];
				os[1] = ((Inverter)info).getName();
				os[2] = searchParam.getDate();
				os[9] = ((Inverter)info).getCollectid();
			}
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("nbqid", os[9]);
			data.put("_0", os[0]);
			data.put("_1", os[1]);
			data.put("_2", os[2]);
			data.put("_3", os[3]);
			data.put("_4", os[4]);
			data.put("_5", os[5]);
			data.put("_6", os[6]);
			data.put("_7", os[7]);
			data.put("_8", os[8]);
			pagerdata.add(data);
		}
		Map<String,Object> total = new HashMap<String,Object>();
		total.put("total", pager.getRowCount());
		total.put("rows", pagerdata);
		
		return total;
	}
	
	@RequestMapping("/find")
	public String findMonthQuota(InverterQuotaDay quotaDay,Model model) {
		Query query = daoSrv.getHqlQuery("from NBQquotaDay where inverterid=? and ctime=?");
		InverterQuotaDay quota = (InverterQuotaDay) query.setLong(0, quotaDay.getInverterid()).setDate(1, quotaDay.getCtime()).uniqueResult();
		if(quota == null) {
			quota = quotaDay;
		}
		model.addAttribute("quotaDay",quota);
		return "/user/data/editNBQquota";
	}
	
	@RequestMapping("/save")
	public String saveQuotaMonth(Model model,InverterQuotaDay quotaDay) {
		daoSrv.saveOrUpdate(quotaDay);
		return "redirect:/user/nbqquota/list";
	}
	
	@RequestMapping("/export")
	public void exp(SearchParam searchParam,HttpServletResponse response) throws Exception {
		String name = "";
		String sql = "";
		String hql = "from Inverter where 1=1 ";
		String counthql = "select count(id) from Inverter where 1=1 ";
		if(searchParam != null) {
			if(searchParam.getSelWay().equals("day")) {
				name = searchParam.getDate();
				sql = "select a.inverterdayid,b.name,a.ctime,a.daycap,"
					+ " a.efficiency,a.hour,a.maxdcpower,a.maxacpower,a.avgtemperature,a.inverterid "
					+ " from da_inverterday a left join f_inverter b on a.inverterid=b.collectid where 1=1 ";
				if(StringUtils.isNotEmpty(searchParam.getIds())) {
					sql += " and a.inverterid in ("+searchParam.getIds()+") ";
					hql += " and id in ("+searchParam.getIds()+") ";
					counthql += " and id in ("+searchParam.getIds()+") ";
				}
				if(StringUtils.isNotEmpty(searchParam.getDate())) {
					sql += " and a.ctime='"+searchParam.getDate()+"' ";
				}
			}else if(searchParam.getSelWay().equals("month")) {
				name = searchParam.getMonth();
				sql = "select 'a' as d,b.name,1 as cty,sum(a.daycap),"
						+ " avg(a.efficiency),sum(a.hour),max(a.maxdcpower),max(a.maxacpower),"
						+ " avg(a.avgtemperature),a.inverterid "
						+ " from da_inverterday a left join f_inverter b on a.inverterid=b.collectid where 1=1 ";
				if(StringUtils.isNotEmpty(searchParam.getIds())) {
					sql += " and a.inverterid in ("+searchParam.getIds()+") ";
					hql += " and id in ("+searchParam.getIds()+") ";
					counthql += " and id in ("+searchParam.getIds()+") ";
				}
				if(StringUtils.isNotEmpty(searchParam.getMonth())) {
					sql += " and a.ctime>='"+searchParam.getMonth()+"-01' ";
					sql += " and a.ctime<='"+searchParam.getMonth()+"-"+DateUtil.getMonthLastDay(searchParam.getMonth())+"' ";
				}
				sql += " group by a.inverterid";
			}else {
				name = searchParam.getYear()+"";
				sql = "select 'a' as da,b.name,1 as cty,sum(a.daycap),"
						+ " avg(a.efficiency),sum(a.hour),max(a.maxdcpower),max(a.maxacpower),"
						+ " avg(a.avgtemperature),a.inverterid "
						+ " from da_inverterday a left join f_inverter b on a.inverterid=b.collectid where 1=1 ";
				if(StringUtils.isNotEmpty(searchParam.getIds())) {
					sql += " and a.inverterid in ("+searchParam.getIds()+") ";
					hql += " and id in ("+searchParam.getIds()+") ";
					counthql += " and id in ("+searchParam.getIds()+") ";
				}
				
				sql += " and a.ctime>='"+searchParam.getYear()+"-01-01' ";
				sql += " and a.ctime<='"+searchParam.getYear()+"-12-31' ";
				
				sql += " group by a.inverterid";
			}
		}
		hql += "  order by id";
		List<Object[]> quotas = daoSrv.findBySql(sql);
		Map<Integer,Object[]> datas = new HashMap<Integer,Object[]>();
		for(Object[] os : quotas) {
			datas.put((Integer)os[9], os);
		}
		List<Object> alldata = daoSrv.findByHql(hql);
		List<Object> pagerdata = new ArrayList<Object>();
		for(Object info:alldata) {
			Long cid = ((Inverter)info).getCollectid();
			Object[] os = datas.get(cid.intValue());
			if(os==null) {
				os = new Object[10];
				os[1] = ((Inverter)info).getName();
				os[2] = searchParam.getDate();
				os[9] = ((Inverter)info).getCollectid();
			}
			pagerdata.add(os);
		}
	
		Workbook wb = this.loadModelFile("逆变器报表.xls");
		
		final Double[] total = {0d,0d,0d,0d,0d,0d};//1总发电量,2总转换效率,3总满发小时,4最大支流功率,5最大交流功率,6总温度
		final CellStyle[] style = {null,null,null};
		fillWorkbook(wb,pagerdata,new ExportHandler<Object>() {
			@Override
			public void dealOneRow(Row row, Object datas, CellStyle[] styles) {
				Object[] data = (Object[]) datas;
				total[0] += ((BigDecimal)(data[3]==null?new BigDecimal(0):data[3])).doubleValue();
				total[1] += ((BigDecimal)(data[4]==null?new BigDecimal(0):data[4])).doubleValue();
				total[2] += ((BigDecimal)(data[5]==null?new BigDecimal(0):data[5])).doubleValue();
				total[3] = Math.max(((BigDecimal)(data[6]==null?new BigDecimal(0):data[6])).doubleValue(),total[3]);
				total[4] = Math.max(((BigDecimal)(data[7]==null?new BigDecimal(0):data[7])).doubleValue(),total[4]);
				total[5] += ((BigDecimal)(data[8]==null?new BigDecimal(0):data[8])).doubleValue();
				CellStyle styleleft = style [0] = styles[0];
				CellStyle stylecenter = style [1] = styles[1];
				CellStyle styleright = style [2] = styles[2];
				
				styleright.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
				
				Cell cell = row.createCell(0);
				cell.setCellStyle(styleleft);
				fillCell(cell,data[1]);
				
				cell = row.createCell(1);
				cell.setCellStyle(styleright);
				fillCell(cell,data[3]);
				
				cell = row.createCell(2);
				cell.setCellStyle(styleright);
				fillCell(cell,data[4]);
				
				cell = row.createCell(3);
				cell.setCellStyle(styleright);
				fillCell(cell,data[5]);
				
				cell = row.createCell(4);
				cell.setCellStyle(styleright);
				fillCell(cell,data[6]);
				
				cell = row.createCell(5);
				cell.setCellStyle(styleright);
				fillCell(cell,data[7]);
				
				cell = row.createCell(6);
				cell.setCellStyle(styleright);
				fillCell(cell,data[8]);
			}
		});
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.createRow(sheet.getLastRowNum()+1);
		Row r0 = sheet.getRow(0);
		r0.getCell(0).setCellValue(name+" 监控系统逆变器指标报表");
		if(quotas.size()>0) {
			//合计
			Cell cell = row.createCell(0);
			cell.setCellStyle(style[0]);
			cell.setCellValue("合计");
			
			cell = row.createCell(1);
			cell.setCellStyle(style[2]);
			cell.setCellValue(total[0]);
			
			cell = row.createCell(2);
			cell.setCellStyle(style[2]);
			cell.setCellValue(total[1]/quotas.size());
			
			cell = row.createCell(3);
			cell.setCellStyle(style[2]);
			cell.setCellValue(total[2]);
			
			cell = row.createCell(4);
			cell.setCellStyle(style[2]);
			cell.setCellValue(total[3]);
			
			cell = row.createCell(5);
			cell.setCellStyle(style[2]);
			cell.setCellValue(total[4]);
			
			cell = row.createCell(6);
			cell.setCellStyle(style[2]);
			cell.setCellValue(total[5]/quotas.size());
		}
		this.outputExcel(wb, name+" 监控系统逆变器指标报表" + ".xls", response);
	}
	@Autowired
	private InverterDayQuota inverterDayQuota;
	@RequestMapping("/calc")
	public String calc(String date) throws ParseException {
		inverterDayQuota.calc(null, DateUtils.parseDate(date, new String[] {"yyyy-MM-dd"}));
		return "redirect:/da/inverterquota/page";
	}
}
