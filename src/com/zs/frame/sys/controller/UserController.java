package com.zs.frame.sys.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.frame.services.IBaseDao;
import com.zs.frame.sys.beans.User;
import com.zs.frame.utils.MD5Util;

/**
 * 功能模块
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Controller
@RequestMapping("/sys/user")
@SuppressWarnings("unchecked")
public class UserController {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao daoSrv;
	
	@RequestMapping("/list")
	public String listUsers(Model model){
		List<User> users = daoSrv.findByHql("from User");
		model.addAttribute("users",users);
		return "/sys/user/listUsers";
	}
	
	@RequestMapping("/find")
	public String findUser(Long id,Model model){
		if(id != null){
			User user = (User) daoSrv.findOneRowByHql("from User where userid="+id);
			model.addAttribute("user",user);
		}
		return "/sys/user/editUser";
	}
	
	@RequestMapping("/delete")
	public String deleteUsers(String ids){
		if(StringUtils.isNotEmpty(ids)){
			String[] arrid = ids.split(",");
			for(String id: arrid){
				daoSrv.deleteById(User.class, Long.valueOf(id));
			}
		}
		return "redirect:/sys/user/list";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveUser(User user){
		User tu = (User) daoSrv.findOneRowByHql("from User where userid="+user.getUserid());
		if(tu != null) {
			user.setRoles(tu.getRoles());
		}
		daoSrv.merge(user);
		
		return "redirect:/sys/user/list";
	}
	
	@RequestMapping("/disable")
	public void disable(Long id,Boolean disable){
		String sql;
		if(disable){
			sql = "update sys_user set disabled='Y' where userid="+id;
		}else{
			sql = "update sys_user set disabled='N' where userid="+id;
		}
		daoSrv.execSql(sql);
	}
	
	@RequestMapping("/changepwd")//修改密码查询
	public String changepwd(Long id,Model model){
		User user = (User) daoSrv.findOneRowByHql("from User where userid="+id);
		model.addAttribute("user", user);
		return "/sys/user/editPassword";
	}
	@RequestMapping("/multipwd")//修改密码保存
	public String changepwd(Long userid,String password,Model model){
		User user = (User) daoSrv.findOneRowByHql("from User where userid="+userid);
		user.setPassword(MD5Util.encode(password));
		daoSrv.merge(user);
		model.addAttribute("saved",true);
		return "/sys/user/editPassword";
	}
	
	@ResponseBody
	@RequestMapping("/checkUserName")
	public Object[] checkUserName(String fieldValue,String fieldId,Model model){
		List<User> users = daoSrv.getHqlQuery("from User where username=? ").setString(0, fieldValue).list();
		Object[] msg = new Object[]{fieldId,users.size()==0};
		return msg;
	}
	
}
