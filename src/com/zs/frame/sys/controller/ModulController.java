package com.zs.frame.sys.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.frame.services.IBaseDao;
import com.zs.frame.sys.beans.Modul;

/**
 * 功能模块
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Controller
@RequestMapping("/sys/modul")
public class ModulController {

	@Autowired
	private IBaseDao daoSrv;
	
	@RequestMapping("/find")
	public String findModul(Long mid,Long pid,Model model){
		if(mid != null){
			String hql = "from Modul where mid="+mid;
			Modul modul = (Modul) daoSrv.findOneRowByHql(hql);
			model.addAttribute("modul",modul);
		}else if(pid != null){
			model.addAttribute("pid",pid);
		}
		return "/sys/modul/editModul";
	}
	
	@RequestMapping("/save")
	public String saveModul(Modul modul,Model model){
		if(modul.getMid() == null){
			model.addAttribute("issave", true);
		}
		modul = (Modul) daoSrv.merge(modul);
		Map<String,Object> node = new LinkedHashMap<String,Object>();
		modul = (Modul) daoSrv.findOneRowByHql("from Modul where mid="+modul.getMid());
		model.addAttribute("modul",modul);
		node.put("id", modul.getMid());
		node.put("pid", modul.getPid());
		node.put("text", modul.getName());
		node.put("url", "/sys/modul/find?mid="+modul.getMid());
		node.put("type", "mod");
		node.put("icon", "/sys/js/ligerUI/skins/Aqua/images/tree/folder.gif");
		model.addAttribute("node",JSONObject.fromObject(node));
		return "/sys/modul/editModul";
	}
	
	@RequestMapping("/delete")
	public String deleteModul(Long mid,Long pid,Model model){
		daoSrv.execSql("delete from sys_modul where mid="+mid);
		model.addAttribute("pid",pid);
		model.addAttribute("msg","删除成功！");
		return "forward:/sys/modul/find";
	}
}
