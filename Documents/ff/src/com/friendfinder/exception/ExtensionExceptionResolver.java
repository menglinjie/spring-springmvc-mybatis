package com.friendfinder.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 扩展异常解析配置页面
 *
 */
public class ExtensionExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest req,
			HttpServletResponse resp, Object handler, Exception ex) {
		
		ex.printStackTrace();
		
		//系统自定义全局异常
		ExtensionException customException = null;
		
		//异常类型判断
		if(ex instanceof ExtensionException){
			customException = (ExtensionException)ex;
		}else{
			//重新实例化异常
			customException = new ExtensionException("出现系统未知错误，请联系管理员！！！");
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", customException.getMessage());
		modelAndView.setViewName("error");

		return modelAndView;
	}
}
