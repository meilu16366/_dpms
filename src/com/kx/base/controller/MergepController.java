package com.kx.base.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kx.base.beans.MergePoint;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.utils.Pager;

/**
 * 并网点配置
 */
@Controller
@RequestMapping("/base/mergep")
public class MergepController {
	@Autowired
	private IBaseDao<MergePoint> daoSrv;

	@RequestMapping("/page")
	public String page() {
		
		return "/base/listMergePoint";
	}
	/**查找全部并网点信息*/
	@ResponseBody
	@RequestMapping("/list")
	public Object listBWInfo(Integer pageNo,Integer pageSize,String name) {
		
		String hql = "from MergePoint ";
		String counthql = "select count(id) from MergePoint ";
		if(StringUtils.isNotEmpty(name)) {
			hql += " where name like '%"+StringEscapeUtils.escapeSql(name)+"%' ";
			counthql += " where name like '%"+StringEscapeUtils.escapeSql(name)+"%' ";
		}
		Pager<MergePoint> pager = daoSrv.findByHql(hql + " order by id", counthql, pageNo, pageSize);
		Map<String,Object> total = new HashMap<String,Object>();
		total.put("total", pager.getRowCount());
		total.put("rows", pager.getDatas());
		return total;
	}
}
