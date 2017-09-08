package com.zs.frame.sys.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.zs.frame.services.IBaseDao;
import com.zs.frame.sys.beans.ListValue;

/**
 * 值列表控制层
 * @author ctt
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Controller
@RequestMapping("/sys/listValue")
@SuppressWarnings("unchecked")
public class ListValueController {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao daoSrv;
	
	@RequestMapping("/list")
	public String listUsers(Model model){
		List<ListValue> listValues = daoSrv.findByHql("from ListValue");
		model.addAttribute("listValues",listValues);
		return "/sys/listValue/listListValue";
	}
	
	@RequestMapping("/find")
	public String findUser(Long id,Model model){
		if(id != null){
			ListValue listValue = (ListValue) daoSrv.findOneRowByHql("from ListValue where listid="+id);
			model.addAttribute("listValue",listValue);
		}
		return "/sys/listValue/editListValue";
	}
	
	@RequestMapping("/delete")
	public String deleteUsers(String ids){
		if(StringUtils.isNotEmpty(ids)){
			String[] arrid = ids.split(",");
			for(String id: arrid){
				daoSrv.deleteById(ListValue.class, Long.valueOf(id));
			}
		}
		return "redirect:/sys/listValue/list";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveUser(ListValue listValue){
		daoSrv.saveOrUpdate(listValue);
		return "redirect:/sys/listValue/list";
	}
	


	

	
}
