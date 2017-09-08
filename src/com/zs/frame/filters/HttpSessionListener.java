package com.zs.frame.filters;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import com.zs.frame.controller.LoginController;

/**
 * 清除session
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
public class HttpSessionListener implements javax.servlet.http.HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		
		HttpSession session = sessionEvent.getSession();
		Enumeration<String> names = session.getAttributeNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			session.removeAttribute(name);
		}
		session.removeAttribute(LoginController.LOGIN_USER);
	}

}
