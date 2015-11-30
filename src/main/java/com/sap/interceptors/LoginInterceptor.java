package com.sap.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sap.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {

    	System.out.println(request.getRequestURI());
    	
		User currentUser = (User) request.getSession().getAttribute("currentUser");    	
    	if (currentUser == null){
    		response.sendRedirect("login");
    		return false;
    	}
		
        return true;
    }
}
