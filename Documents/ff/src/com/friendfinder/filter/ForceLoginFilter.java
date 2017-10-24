package com.friendfinder.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class ForceLoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//功能:判断用户的是否登录，没登陆直接转到登录页面
		HttpSession session = req.getSession(true);
		Object obj = session.getAttribute("id");
		if(obj == null){  //没登录,回登录页
			res.sendRedirect("/ff/html/login.jsp");
			return;
		}else{  //登录
			chain.doFilter(request, response);//让响应流和输出流继续向下传递
		}	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {};
	@Override
	public void destroy() {};

}
