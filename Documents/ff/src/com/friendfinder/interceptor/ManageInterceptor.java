package com.friendfinder.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ManageInterceptor implements HandlerInterceptor {
	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse resp, Object o, Exception e)
			throws Exception {
		System.out.println("管理员登录权限校验结束");
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp,
			Object o, ModelAndView mv) throws Exception {
		System.out.println("管理员登录权限校验进行中");
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object o) throws Exception {
		System.out.println("管理员登录权限校验开始");
		
		Object id = req.getSession(true).getAttribute("admin");	
		if (id == null) {
			System.out.println("管理员校验未通过，正在跳转登录页面...");
			resp.sendRedirect("/ff/bg/backgroundLogin.html");
			return false;
		}
		System.out.println("对管理员" + id.toString() + "访问进行拦截处理!");
		
		return true;
	}
}
