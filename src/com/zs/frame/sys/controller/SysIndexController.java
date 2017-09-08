package com.zs.frame.sys.controller;


import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.frame.services.IBaseDao;

/**
 * 系统首页
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Controller
@RequestMapping("/sys")
public class SysIndexController {
	
	@Autowired
	private IBaseDao daoSrv;
	
	@RequestMapping("/index")
	public String handleIndexData(Model model){
		//初始化功能树
		List<Object> trees = new ArrayList<Object>();
		RoleController.makeTree(daoSrv, trees, null);
		model.addAttribute("moduls",JSONArray.fromObject(trees)+"");
		return "sys/index";
	}
	
}
