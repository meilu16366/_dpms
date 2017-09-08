package com.zs.frame.sys.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.frame.services.IBaseDao;
import com.zs.frame.sys.beans.Function;
import com.zs.frame.sys.beans.Modul;

/**
 * 用户功能
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Controller
@RequestMapping("/sys/fun")
public class FunctionController {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao daoSrv;
	
	@RequestMapping("/find")
	public String findFunction(Long fid,Model model,Long mid){
		if(fid != null){
			Function function = (Function) daoSrv.findOneRowByHql("from Function where fid="+fid);
			model.addAttribute("function",function);
		}else if(mid != null){
			model.addAttribute("mid",mid);
		}
		return "/sys/fun/editFun";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/save")
	public String saveFunction(Function function,Long mid,Model model){
		if(function.getFid()==null){
			model.addAttribute("issave", true);
		}
		Modul modul = (Modul) daoSrv.findOneRowByHql("from Modul where mid="+mid);
		function.setModul(modul);
		
		Function f = (Function) daoSrv.findOneRowByHql("from Function where fid="+function.getFid());
		if(f!=null)function.setRoles(f.getRoles());
		function = (Function) daoSrv.merge(function);
		model.addAttribute("function",function);
		
		Map<String,Object> node = new LinkedHashMap<String,Object>();
		node.put("id", Long.valueOf(System.currentTimeMillis()+""+function.getFid()));
		node.put("pid", mid);
		node.put("text", function.getName());
		node.put("url", "/sys/fun/find?fid="+function.getFid());
		node.put("type", "fun");
		node.put("icon", "/sys/js/ligerUI/skins/Aqua/images/tree/tree-level.gif");
		model.addAttribute("node", JSONObject.fromObject(node));
		return "/sys/fun/editFun";
	}
	
	@RequestMapping("/delete")
	public String deleteFunction(Long fid,Long mid,Model model){
		daoSrv.execSql("delete from sys_role_fun where fid="+fid);
		daoSrv.execSql("delete from sys_function where fid="+fid);
		model.addAttribute("mid",mid);
		model.addAttribute("msg", "删除成功！");
		return "forward:/sys/fun/find";
	}
}
