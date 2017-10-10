package com.kx.base.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kx.base.beans.EmiInfo;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.utils.Pager;

@Controller
@RequestMapping("/base/emi")
public class EmiController {
	@Autowired
	private IBaseDao<EmiInfo> daoSrv;

	@RequestMapping("/page")
	public String page() {
		
		return "/base/listEmi";
	}
	/**查找全部并网点信息*/
	@ResponseBody
	@RequestMapping("/list")
	public Object listBWInfo(Integer pageNo,Integer pageSize,String name) {
		
		String hql = "from EmiInfo ";
		String counthql = "select count(id) from EmiInfo ";
		if(StringUtils.isNotEmpty(name)) {
			hql += " where name like '%"+StringEscapeUtils.escapeSql(name)+"%' ";
			counthql += " where name like '%"+StringEscapeUtils.escapeSql(name)+"%' ";
		}
		Pager<EmiInfo> pager = daoSrv.findByHql(hql + " order by id", counthql, pageNo, pageSize);
		Map<String,Object> total = new HashMap<String,Object>();
		total.put("total", pager.getRowCount());
		total.put("rows", pager.getDatas());
		return total;
	}
}
