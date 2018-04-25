package com.boco.xxzx.utils.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.boco.xxzx.model.User;

public class SessionContext {
	/**
	 * 全局user
	 */
	public static final String GLOBLE_USER_SESSION = "user";
	public static HttpServletRequest getRequest(){
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		return requestAttributes==null? null : requestAttributes.getRequest();
	}
	
	public static HttpSession getSession(){
		return getRequest().getSession(false);
	}
	
	public static String getRealRootPath(){
		return getRequest().getServletContext().getRealPath("/");
	}
	
	public static String getIp() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes!=null){
			HttpServletRequest request = servletRequestAttributes.getRequest();
			return request.getRemoteAddr();
		}
		return null;
	}
	
	public static User getCurrentUser(){
		HttpServletRequest request = getRequest();
		return (User) (request == null?null:request.getSession().getAttribute(GLOBLE_USER_SESSION));
	}
	
	public static Object getSessionAttribute(String name){
		HttpServletRequest request = getRequest();
		return request == null?null:request.getSession().getAttribute(name);
	}
	
	public static void setSessionAttribute(String name,Object value){
		HttpServletRequest request = getRequest();
		if(request!=null){
			request.getSession().setAttribute(name, value);	
		}
	}
	
	public static Object getRequestAttribute(String name){
		HttpServletRequest request = getRequest();
		return request == null?null:request.getAttribute(name);
	}
	public static void setRequestAttribute(String name,Object value){
		HttpServletRequest request = getRequest();
		if(request!=null){
			request.setAttribute(name, value);	
		}
	}

	public static String getContextPath() {
		return getRequest().getContextPath();
	}

	public static void removeSessionAttribute(String name) {
		getRequest().getSession().removeAttribute(name);
	}
	
}


