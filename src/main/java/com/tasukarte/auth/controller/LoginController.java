package com.tasukarte.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	// 最初のアクセス
	@GetMapping("/")
	public String index() {
		return "redirect:/auth/login";
	}
	
	//ログイン画面表示
	@GetMapping("/auth/login")
	public String loginpage() {
		return "login";
	}
	
}
