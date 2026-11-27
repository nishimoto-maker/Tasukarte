package com.tasukarte.staff.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.staff.dto.MyPageDto;
import com.tasukarte.staff.service.StaffService;

@Controller
public class MyPageController {

	private final StaffService staffService;

	public MyPageController(StaffService staffService) {
		this.staffService = staffService;
	}

	// マイページ画面を表示する
	@GetMapping("/staff/mypage/{staffId}")
	public String myPage(
			@PathVariable Integer staffId,
			Model model) {
		// ログイン中の認証情報を取得
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// 認証情報からログイン中のユーザー情報を取得
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();

		// URLの職員IDとログイン中の職員IDが一致するか確認する
		if (!staffId.equals(loginUser.getStaffId())) {
			throw new AccessDeniedException(
					"他の職員のマイページは閲覧できません。");
		}

		// ログイン中の職員本人の情報を取得する
		MyPageDto myPage = staffService.getMyPage();

		// マイページに表示する情報を画面に渡す
		model.addAttribute("myPage", myPage);

		return "staff/my-page";
	}
}