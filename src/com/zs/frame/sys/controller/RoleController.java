package com.zs.frame.sys.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zs.frame.services.IBaseDao;
import com.zs.frame.sys.beans.Function;
import com.zs.frame.sys.beans.Modul;
import com.zs.frame.sys.beans.Role;
import com.zs.frame.sys.beans.User;

/**
 * 角色操作
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController {
	
	@Autowired
	private IBaseDao daoSrv;
	
	@RequestMapping("/find")
	public String findRole(Long id,Model model){
		if(id != null){
			Role role = (Role) daoSrv.findOneRowByHql("from Role where rid="+id);
			model.addAttribute("role",role);
		}
		return "sys/role/editRole";
	}

	@RequestMapping("/list")
	public String listRoles(Model model){
		List<Role> roles =  daoSrv.findByHql("from Role");
		model.addAttribute("roles", roles);
		return "sys/role/listRoles";
	}
	
	@RequestMapping("/save")
	public String saveRole(Role role){
		daoSrv.saveOrUpdate(role);
		return "redirect:/sys/role/list";
	}
	
	@RequestMapping("/delete")
	public String deleteRoles(String ids){
		if(StringUtils.isNotEmpty(ids)){
			String[] arrid = ids.split(",");
			for(String id:arrid){
				daoSrv.deleteById(Role.class, Long.valueOf(id));
			}
		}
		return "redirect:/sys/role/list";
	}
	
	@RequestMapping(value="/editUsers",method=RequestMethod.GET)
	public String editUsers(Long id,Model model){
		Role role = (Role) daoSrv.findOneRowByHql("from Role where rid="+id);
		List<User> users = daoSrv.findByHql("from User");
		Iterator<User> its = users.iterator();
		while(its.hasNext()){
			User u = its.next();
			Set<User> roleUsers = role.getUsers();
			if(roleUsers.contains(u)){
				its.remove();
			}
		}
		model.addAttribute("users", users);
		model.addAttribute("role", role);
		return "sys/role/editUsers";
	}
	
	@RequestMapping(value="/editUsers",method=RequestMethod.POST)
	public String editUsers(Long rid,/**角色用户*/ String hasids){
		String delSql = "delete from sys_role_user where rid="+rid;
		daoSrv.execSql(delSql);
		if(StringUtils.isNotEmpty(hasids)){
			String sqls = "";
			String [] userids = hasids.split(",");
			for(String id : userids){
				sqls = "insert into sys_role_user(rid,userid) values("+rid+","+id+");";
				daoSrv.execSql(sqls);
			}
		}
		return "redirect:/sys/role/list";
	}
	
	@RequestMapping(value="/addFun",method=RequestMethod.GET)
	public String addFun(Long rid,Model model){
		List<Object> trees = new ArrayList<Object>();
		Role role = (Role) daoSrv.findOneRowByHql("from Role where rid="+rid);
		makeTree(daoSrv,trees,role);
		List<Long> fids = new ArrayList<Long>();
		if(role.getFunctions()!=null){
			for(Function fun : role.getFunctions()){
				fids.add(fun.getFid());
			}
		}
		model.addAttribute("moduls", JSONArray.fromObject(trees)+"");
		model.addAttribute("rid", rid);
		model.addAttribute("fids", JSONArray.fromObject(fids));
		return "/sys/role/editFuns";
	}
	
	@RequestMapping(value="/addFun",method=RequestMethod.POST)
	public String addFun(String fids,Long rid ,Model model){
		daoSrv.execSql("delete from sys_role_fun where rid="+rid);
		if(StringUtils.isNotEmpty(fids)){
			String[] arrfid = fids.split(",");
			for(String fid : arrfid){
				String sql = "insert into sys_role_fun (rid,fid) values("+rid+","+fid+")";
				daoSrv.execSql(sql);
			}
		}
		model.addAttribute("msg", "保存成功！");
		return addFun(rid,model);
	}
	
	/**
	 * 初始化模块功能树
	 * @param daoSrv
	 * @param trees
	 */
	public static void makeTree(IBaseDao daoSrv,List<Object> trees, Role role){
		List<Modul> moduls = daoSrv.findByHql("from Modul where pid is null or pid=0 order by orderno");
		for(Modul m : moduls){
			Map<String,Object> node = new LinkedHashMap<String,Object>();
			node.put("id", m.getMid());
			node.put("pid", 0);
			node.put("text", m.getName());
			node.put("url", "/sys/modul/find?mid="+m.getMid());
			node.put("isExpand", true);
			node.put("type", "mod");
			node.put("icon", "/sys/js/ligerUI/skins/Aqua/images/tree/folder.gif");
			trees.add(node);
			deepTree(m,trees,role);
		}
	}
	private  static void deepTree(Modul m,List<Object> trees,Role role){
		if(m.getModuls()!=null){
			for(Modul md : m.getModuls()){
				Map<String,Object> node = new LinkedHashMap<String,Object>();
				node.put("id", md.getMid());
				node.put("pid", m.getMid());
				node.put("text", md.getName());
				node.put("url", "/sys/modul/find?mid="+md.getMid());
				node.put("type", "mod");
				node.put("icon", "/sys/js/ligerUI/skins/Aqua/images/tree/folder.gif");
				trees.add(node);
				deepTree(md,trees,role);
			}
		}
		if(m.getFunctions()!=null){
			for(Function fun : m.getFunctions()){
				Map<String,Object> node = new LinkedHashMap<String,Object>();
				node.put("id", Long.valueOf(System.currentTimeMillis()+""+fun.getFid()));
				node.put("pid", m.getMid());
				node.put("text", fun.getName());
				node.put("url", "/sys/fun/find?fid="+fun.getFid());
				node.put("type", "fun");
				node.put("fid", fun.getFid());
				if(role!=null){
					node.put("ischecked", role.getFunctions().contains(fun));
				}
				
				node.put("icon", "/sys/js/ligerUI/skins/Aqua/images/tree/tree-level.gif");
				trees.add(node);
			}
		}
	}
}
