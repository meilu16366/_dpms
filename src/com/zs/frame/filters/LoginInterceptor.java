package com.zs.frame.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zs.frame.controller.LoginController;


/**
 * 登录拦截
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute(LoginController.LOGIN_USER);
        if(user != null){
            return true;
        }
        //System.out.println(request.getRequestURL());
        //不符合条件的，跳转到登录界面
        response.sendRedirect("/login.jsp");
        //request.getRequestDispatcher("/login.jsp").forward(request, response);
        return false;
	}

}
