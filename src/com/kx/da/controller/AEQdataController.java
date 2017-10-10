package com.kx.da.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kx.da.beans.AEQdata;
import com.kx.da.beans.SearchParam;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.utils.Pager;


/**
 *  异常设备数据查询
 */
@Controller
@RequestMapping("/da/aequip")
public class AEQdataController {
	
	@Autowired
	private IBaseDao  daoSrv;
	
	@RequestMapping("/page")
	public String page() {
		return "/realdata/listErroreq";
	}
	/**
	 * 查询所有的异常设备
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list")
	private Object listAEQdata(SearchParam searchParam,Integer pageNo,Integer pageSize) {
		String hql="from AEQdata where 1=1 ";
		String counthql = "select count(id) from AEQdata where 1=1 ";
		if(StringUtils.isNotEmpty(searchParam.getName())) {
			hql += " and eqname like '%"+searchParam.getName()+"%'";
			counthql += " and eqname like '%"+searchParam.getName()+"%'";
		}
		hql += " order by ctime desc";
		
		Pager<AEQdata> pager =  daoSrv.findByHql(hql,counthql,pageNo,pageSize);

		Map<String,Object> total = new HashMap<String,Object>();
		total.put("total", pager.getRowCount());
		total.put("rows", pager.getDatas());
		return total;
	}
	
	@ResponseBody
	@RequestMapping("/count")
	public Object getCount() {
		String counthql = "select count(id) from AEQdata where 1=1 ";
		return new Object[] {daoSrv.getRowCountByHql(counthql)};
	}
}
