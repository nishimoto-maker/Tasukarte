package com.tasukarte.common.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws Exception {
		
		HttpSession session = request.getSession(false);
		
		//セッションがない、またはログイン情報がない場合
		if (session == null || session.getAttribute("staffId") == null) {
			response.sendRedirect("/auth/login");
			return false;
		}
		
		//ログイン済みの場合はアクセスを許可
		return true;
	}
}
