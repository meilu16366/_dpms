package com.zs.frame.sys.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.frame.services.IBaseDao;
import com.zs.frame.sys.beans.Parameter;

/**
 * 系统参数
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Controller
@RequestMapping("/sys/param")
public class ParameterController {

	@Autowired
	private IBaseDao<Parameter> daoSrv;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/list")
	public String listParam(Model model,@ModelAttribute("name") String name){
		String hql = "from Parameter ";
		List<Parameter> params = null;
		if(StringUtils.isNotEmpty(name)){
			hql += " where name like ? ";
			params = daoSrv.getHqlQuery(hql).setString(0, "%"+name+"%").list();
			model.addAttribute("name", name);
		}else{
			params = daoSrv.findByHql("from Parameter");
		}
		model.addAttribute("params", params);
		return "/sys/param/listParam";
	}
	
	@RequestMapping("/find")
	public String findParam(Long id,Model model){
		if(id!=null){
			String hql = "from Parameter where paramid="+id;
			Parameter parameter = daoSrv.findOneRowByHql(hql);
			model.addAttribute("parameter", parameter);
		}
		return "/sys/param/editParam";
	}
	
	
	@RequestMapping("/save")
	public String saveParam(Parameter parameter){
		daoSrv.saveOrUpdate(parameter);
		return "redirect:/sys/param/list";
	}
	
	@RequestMapping("/delete")
	public String deleteParam(String ids){
		if(StringUtils.isNotEmpty(ids)){
			String[] arrid = ids.split(",");
			for(String id : arrid){
				daoSrv.deleteById(Parameter.class, Long.valueOf(id));
			}
		}
		return "redirect:/sys/param/list";
	}
}
