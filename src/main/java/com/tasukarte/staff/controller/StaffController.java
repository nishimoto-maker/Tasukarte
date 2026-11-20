package com.tasukarte.staff.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.staff.dto.MyPageDto;
import com.tasukarte.staff.dto.StaffListDto;
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
	// URLから検索条件を受け取り、検索結果を画面に渡す
	@GetMapping("/staff/list")
	public String staffList(
			@RequestParam String name,
	        @RequestParam(required = false) Short gender,
	        @RequestParam(required = false) Integer positionId,
	        @RequestParam(required = false) Integer roleId,
	        @RequestParam(required = false) Boolean isDeleted,
	        Model model) {
		
		// ログイン中の認証情報を取得
		Authentication authentication =
		        SecurityContextHolder.getContext().getAuthentication();

		// 認証情報からログイン中のユーザー情報を取得
		LoginUser loginUser =
		        (LoginUser) authentication.getPrincipal();

		// ログイン中の職員の権限IDを取得
		Integer loginRoleId = loginUser.getRoleId();

		// 院内システム管理者か判定
		boolean isAdmin = Integer.valueOf(2).equals(loginRoleId);
		
		// Serviceを呼び出して職員情報を検索する
		List<StaffListDto> staffList = staffService.searchStaff(
		        name,
		        gender,
		        positionId,
		        roleId,
		        isDeleted
		);
		// 検索結果を画面に渡す
		model.addAttribute("staffList", staffList);
		
		// 院内システム管理者かどうかを画面に渡す
		model.addAttribute("isAdmin", isAdmin);
		
		return "staff/staff-list";
	}
	// マイページ画面を表示する
	@GetMapping("/staff/mypage")
	public String myPage(Model model) {

	    // ログイン中の職員本人の情報を取得する
	    MyPageDto myPage = staffService.getMyPage();

	    // マイページに表示する情報を画面に渡す
	    model.addAttribute("myPage", myPage);

	    return "staff/my-page";
	}
}