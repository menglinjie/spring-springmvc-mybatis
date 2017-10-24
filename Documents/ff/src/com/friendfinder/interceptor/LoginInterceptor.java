package com.friendfinder.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录拦截器(主要是对登录用户进行访问权限检查)，但暂不实现
 * 
 * @author leaf
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse resp, Object o, Exception e)
			throws Exception {
		System.out.println("登录权限校验结束");
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp,
			Object o, ModelAndView mv) throws Exception {
		System.out.println("登录权限校验进行中");
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object o) throws Exception {
		System.out.println("登录权限校验开始");
		
		Object id = req.getSession(true).getAttribute("id");	
		if (id == null) {
			System.out.println("用户校验未通过，正在跳转登录页面...");
			resp.sendRedirect("/ff/html/login.jsp");
			return false;
		}
		System.out.println("对用户" + id + "访问进行拦截处理!");
			
		return true;
	}

}
