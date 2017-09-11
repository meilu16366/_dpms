package com.kx.frame.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kx.frame.services.IBaseDao;
import com.kx.frame.sys.beans.User;
import com.kx.frame.utils.MD5Util;



@Controller
public class LoginController {
	private static Logger logger = Logger.getLogger(LoginController.class);
	/**登录的用户*/
	public static final String LOGIN_USER = "LOGIN_USER";
	/**前台首页*/
	public static final String DPMS_INDEX = "redirect:index";
	/**后台首页*/
	public static final String SYS_INDEX = "redirect:index";
	/**跳转登录页*/ 
	public static final String LOGIN_PAGE = "forward:/login.jsp";
	
	@Autowired
	private IBaseDao daoSrv;
	
	@RequestMapping(value="/login",produces="text/html;charset=UTF-8")
	public String login(String username,String password,HttpServletRequest request,Model model,RedirectAttributes ratt) {
		String errorMsg = "";

		try{
			//验证非空
			checkRange(username,password);
			
			List<User> users = daoSrv.getHqlQuery("from User where username=? and disabled='N'").setString(0, username).list();
			//用户登录
			User user = users.size()>0?users.get(0):null;
			if(user != null){
				if(!MD5Util.encode(password).equals(user.getPassword())){
					errorMsg = "密码错误！";
				}else{
					request.getSession().setAttribute(LOGIN_USER, user);
					return DPMS_INDEX;
				}
			}else{
				errorMsg = "用户不存在！";
				
			}
		}catch(LoginException e){
			errorMsg = e.getMessage();
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("message", errorMsg);
		return LOGIN_PAGE;
	}
	
	
	
	private void checkRange(String username,String password) throws LoginException{
		if(StringUtils.isEmpty(username)){
			throw new LoginException("用户名不能为空！");
		}
		if(StringUtils.isEmpty(password)){
			throw new LoginException("密码不能为空！");
		}
	}
	class LoginException extends Exception{
		private static final long serialVersionUID = 1L;

		public LoginException(String msg){
			super(msg);
		}
	}
	@RequestMapping("/index")
	public String redirect() {
		return "/index";
	}

}
