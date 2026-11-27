package com.tasukarte.staff.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.staff.dto.StaffDetailDto;
import com.tasukarte.staff.service.StaffService;

@Controller
public class StaffDetailController {

	private final StaffService staffService;

	public StaffDetailController(StaffService staffService) {
		this.staffService = staffService;
	}

	// 職員情報確認画面を表示する
	@GetMapping("/staff/{staffId}")
	public String staffDetail(
			@PathVariable Integer staffId,
			Model model) {

		// ログイン中の認証情報を取得
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// 認証情報からログイン中のユーザー情報を取得
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();

		// ログイン中の職員の権限IDを取得
		Integer loginRoleId = loginUser.getRoleId();

		// 院内システム管理者か判定
		boolean isAdmin = Integer.valueOf(2).equals(loginRoleId);

		// 院内システム管理者以外はアクセスできない
		if (!isAdmin) {
			throw new AccessDeniedException(
					"職員情報確認画面を閲覧する権限がありません。");
		}

		// 職員IDを使って職員詳細情報を取得する
		StaffDetailDto staffDetail = staffService.getStaffDetail(staffId);

		// 職員詳細情報を画面に渡す
		model.addAttribute("staffDetail", staffDetail);

		return "staff/staff-detail";
	}
}