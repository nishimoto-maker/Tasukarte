package com.tasukarte.staff.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.staff.entity.Staff;
import com.tasukarte.staff.service.StaffService;

// 職員情報に関する画面制御を担当するController
@Controller
public class StaffController {
	// StaffServiceを利用するための変数
	private final StaffService staffService;

	// コンストラクタでStaffServiceを受け取る
	public StaffController(StaffService staffService) {
	    this.staffService = staffService;
	}
	
	// 職員一覧画面を表示する
	@GetMapping("/staff/list")
	public String staffList(
			@RequestParam String name,
	        @RequestParam(required = false) Short gender,
	        @RequestParam(required = false) Integer positionId,
	        @RequestParam(required = false) Integer roleId,
	        @RequestParam(required = false) Boolean isDeleted,
	        Model model) {
		
		// ログイン中の職員情報を取得
		Authentication authentication =
		        SecurityContextHolder.getContext().getAuthentication();

		LoginUser loginUser =
		        (LoginUser) authentication.getPrincipal();

		// ログイン中の職員の権限IDを取得
		Integer loginRoleId = loginUser.getRoleId();

		// 院内システム管理者か判定
		boolean isAdmin = (loginRoleId == 2);
		
		// 職員情報を検索する
		List<Staff> staffList = staffService.searchStaff(
		        name,
		        gender,
		        positionId,
		        roleId,
		        isDeleted
		);
		// 検索結果を画面に渡す
		model.addAttribute("staffList", staffList);
		
		// 管理者かどうかを画面に渡す
		model.addAttribute("isAdmin", isAdmin);
		
		return "staff/staff-list";
	}
}