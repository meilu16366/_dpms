package com.zs.frame.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 退出
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Controller
public class LogoutController {
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		Enumeration<String> names = session.getAttributeNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			session.removeAttribute(name);
		}
		return LoginController.LOGIN_PAGE;
	}
	
	
}
