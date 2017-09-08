package com.zs.frame.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zs.frame.services.IBaseDao;
import com.zs.frame.sys.beans.Manager;
import com.zs.frame.sys.beans.User;
import com.zs.frame.utils.MD5Util;

/**
 * 登录
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Controller
public class LoginController {
	private static Logger logger = Logger.getLogger(LoginController.class);
	/**登录的用户*/
	public static final String LOGIN_USER = "LOGIN_USER";
	/**前台首页*/
	public static final String MNT_INDEX = "user/index";
	/**后台首页*/
	public static final String SYS_INDEX = "redirect:sys/index";
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
			
			List<User> users = daoSrv.getHqlQuery("from User where username=? and disabled='Y'").setString(0, username).list();
			//用户登录
			User user = users.size()>0?users.get(0):null;
			if(user != null){
				if(!MD5Util.encode(password).equals(user.getPassword())){
					errorMsg = "密码错误！";
				}else{
					request.getSession().setAttribute(LOGIN_USER, user);
					return MNT_INDEX;
				}
			}else{//管理员登录
				List<Manager> managers = daoSrv.getHqlQuery("from Manager where mnname=? ").setString(0, username).list();
				if(managers.size()==1){
					Manager manager = managers.get(0);
					if(!MD5Util.encode(password).equals(manager.getPassword())){
						errorMsg = "密码错误！";
					}else{
						request.getSession().setAttribute(LOGIN_USER, managers.get(0));
						return SYS_INDEX;
					}
				}else{
					errorMsg = "用户不存在！";
				}
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

}
