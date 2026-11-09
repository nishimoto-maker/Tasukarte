package com.tasukarte.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tasukarte.auth.dto.LoginRequest;
import com.tasukarte.auth.service.LoginService;
import com.tasukarte.staff.entity.Staff;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
	
	// ログイン認証
	@PostMapping("/auth/login")
	public String login(
			@ModelAttribute LoginRequest request,
			HttpServletRequest httpRequest,
			Model model) {
		//ログイン認証
		Staff staff = loginService.login(
				request.getStaffId(),
				request.getPassword()
				);
		//認証失敗
		if (staff == null) {
			model.addAttribute(
					"errorMessage",
					"IDまたはパスワードが正しくありません"
					);
			
			return "login";
		}
		
		// セッションを再取得・再生成
		HttpSession session = httpRequest.getSession(true);
		httpRequest.changeSessionId();
		
		//ログイン情報をセッションに保存
		session.setAttribute("staffId", staff.getStaffId());
		session.setAttribute("staffName", staff.getNameKanji());
		
		return "redirect:/top";
		
	}
	
	//ログアウト
	@PostMapping("/auth/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/login";
		
	}
}
