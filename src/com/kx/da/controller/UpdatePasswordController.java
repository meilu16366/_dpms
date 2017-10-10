package com.kx.da.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kx.frame.controller.LoginController;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.sys.beans.User;
import com.kx.frame.utils.MD5Util;


/**
 * 修改密码
 */
@Controller
@RequestMapping("/da/password")
@SuppressWarnings("unchecked")
public class UpdatePasswordController {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao daoSrv;

	@RequestMapping("/changepwd")
	public String changepwd(){
		
		return "/user/info/editUserPassword";
	}
	@RequestMapping("/multipwd")//修改密码保存
	public String changepwd(Long userid,String password,Model model ,HttpServletRequest request){
		//System.out.println(userid);
		User user = (User) request.getSession().getAttribute(LoginController.LOGIN_USER);
		user.setPassword(MD5Util.encode(password));
		daoSrv.merge(user);
		request.getSession().setAttribute(LoginController.LOGIN_USER, user);
		return "redirect:/logout";
	}
	//验证密码
	@ResponseBody
	@RequestMapping("/checkpass")
	public Object[] checkPass(String fieldValue, String fieldId, Model model , HttpSession session ) {
		
		 User user=(User)session.getAttribute(LoginController.LOGIN_USER);
		return new Object[] {fieldId,user.getPassword().equals(MD5Util.encode(fieldValue))};
	}
	
	
}
