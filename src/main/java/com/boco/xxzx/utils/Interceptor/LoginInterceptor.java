package com.boco.xxzx.utils.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.boco.xxzx.model.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	/**
	 * Handler执行完成之后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
	
	/**
	 * Handler执行之后，ModelAndView返回之前调用
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	/**
	 * Handler执行之前调用
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取请求的URL  
        String url = request.getRequestURI();
        //URL:login.jsp是公开的;除了login.jsp是可以公开访问的，其它的URL都进行拦截控制 
        if (url.indexOf("login.jsp")>=0||url.indexOf("login.do")>=0||url.indexOf("toLogin.do")>=0) {
			return true;
		}
        //获取Session 
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        if (user!=null) {
			return true;
		}
        response.sendRedirect(request.getContextPath()+"/login/toLogin.do");
		return false;
	}
	
}
