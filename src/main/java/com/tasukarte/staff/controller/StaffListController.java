package com.tasukarte.staff.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.staff.dto.StaffListDto;
import com.tasukarte.staff.service.StaffService;

// 職員情報に関する画面制御を担当するController
@Controller
public class StaffListController {
	// StaffServiceを利用するための変数
	private final StaffService staffService;

	// コンストラクタでStaffServiceを受け取る
	public StaffListController(StaffService staffService) {
		this.staffService = staffService;
	}

	// 職員一覧画面を表示する
	// URLから検索条件を受け取り、検索結果を画面に渡す
	@GetMapping("/staff")
	public String staffList(
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "gender", required = false) Short gender,
			@RequestParam(name = "position", required = false) Integer positionId,
			@RequestParam(name = "role", required = false) Integer roleId,
			@RequestParam(name = "is_deleted", required = false) Boolean isDeleted,
			Model model) {

		// ログイン中の認証情報を取得
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// 認証情報からログイン中のユーザー情報を取得
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();

		// ログイン中の職員の権限IDを取得
		Integer loginRoleId = loginUser.getRoleId();

		// 院内システム管理者か判定
		boolean isAdmin = Integer.valueOf(2).equals(loginRoleId);

		// 検索結果を格納するリスト
		List<StaffListDto> staffList = new ArrayList<>();

		// 初期表示時は検索せず、氏名が入力されている場合だけ検索する
		if (name != null && !name.isBlank()) {

			List<StaffListDto> searchResult = staffService.searchStaff(
					name,
					gender,
					positionId,
					roleId,
					isDeleted);

			staffList = searchResult;
		}
		// 検索結果を画面に渡す
		model.addAttribute("staffList", staffList);

		// 院内システム管理者かどうかを画面に渡す
		model.addAttribute("isAdmin", isAdmin);

		return "staff/staff-list";
	}
}