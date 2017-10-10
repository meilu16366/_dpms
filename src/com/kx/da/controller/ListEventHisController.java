package com.kx.da.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kx.da.beans.Eventhis;
import com.kx.frame.def.CollectDef;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.utils.Pager;

@Controller
@RequestMapping("/da/event")
public class ListEventHisController {
	@Autowired
	private IBaseDao daoSrv;
	@RequestMapping("/page")
	public String page(Model model) {
		String stime = DateFormatUtils.format(new Date(), "yyyy-MM-dd")+" "+CollectDef.RADIATION_START;
		String etime = DateFormatUtils.format(new Date(), "yyyy-MM-dd")+" "+CollectDef.RADIATION_END;
		model.addAttribute("stime", stime);
		model.addAttribute("etime", etime);
		return "/realdata/listevent";
	}
	@ResponseBody
	@RequestMapping("/list")
	public Object listEvent(String eqname,Integer elevel,Integer eventtype,
			String stime,String etime,Integer pageNo,Integer pageSize) {

		String hql = "from Eventhis where 1=1 ";
		String counthql = "select count(id) from Eventhis where 1=1 ";
		if (StringUtils.isNotEmpty(eqname)) {
			hql += " and eqname like '%" + StringEscapeUtils.escapeSql(eqname) + "%'";
			counthql += " and eqname like '%" + StringEscapeUtils.escapeSql(eqname) + "%'";
		}
		if (elevel != null ) {
			hql += " and elevel = " + elevel;
			counthql += " and elevel = " + elevel;
		}
		if (eventtype != null ) {
			hql += " and eventtype= " + eventtype;
			counthql += " and eventtype= " + eventtype;
		}
		if (StringUtils.isNotEmpty(stime)) {
			hql += " and ctime>='" + stime + "' ";
			counthql += " and ctime>='" + stime + "' ";
		}
		if (StringUtils.isNotEmpty(etime)) {
			hql += " and ctime<='" + etime + "' ";
			counthql += " and ctime<='" + etime + "' ";
		}
		hql += " order by ctime desc";
		Pager<Eventhis> pager = daoSrv.findByHql(hql,counthql,pageNo,pageSize);
		Map<String,Object> total = new HashMap<String,Object>();
		total.put("total", pager.getRowCount());
		total.put("rows", pager.getDatas());
		return total;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(Model model, String eid) {
		Long id = Long.valueOf(eid);
		String sql = "update da_event set sure = 'Y' where id= " + id;
		daoSrv.execSql(sql);
		return "true";
	}

	@RequestMapping("/sureAll")
	public String sureAll(Model model) {
		String sql = "update da_event set sure = 'Y' where sure = 'N' ";
		daoSrv.execSql(sql);
		return "redirect:/da/event/page";
	}
	
	@RequestMapping("/eventcount")
	@ResponseBody
	public Object eventCount() {
		String hql = "select count(id) from  Eventhis where sure='N'";
		Object count = daoSrv.findOneRowByHql(hql);
		return count;
	}

}
