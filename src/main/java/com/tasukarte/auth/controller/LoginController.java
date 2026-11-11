package com.tasukarte.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tasukarte.auth.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;
	
	// 最初のアクセス
	@GetMapping("/")
	public String index() {
		return "redirect:/login";
	}
	
	//ログイン画面表示
	@GetMapping("/auth/login")
	public String loginpage() {
		return "login";
	}
	
}
