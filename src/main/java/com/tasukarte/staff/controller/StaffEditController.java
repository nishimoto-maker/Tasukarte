
package com.tasukarte.staff.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.staff.dto.StaffEditDto;
import com.tasukarte.staff.service.StaffService;

import jakarta.validation.Valid;

@Controller
public class StaffEditController {

	private final StaffService staffService;

	public StaffEditController(StaffService staffService) {
		this.staffService = staffService;
	}

	// 職員情報編集画面を表示する
	@GetMapping("/staff/{staffId}/edit")
	public String staffEdit(
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
					"職員情報編集画面を操作する権限がありません。");
		}

		// 職員IDを使って編集対象の職員情報を取得
		StaffEditDto staffEdit = staffService.getStaffEdit(staffId);

		// 役職一覧を取得
		model.addAttribute("positionList", staffService.getPositionList());

		// 権限一覧を取得
		model.addAttribute("roleList", staffService.getRoleList());

		// 編集対象の職員情報を画面に渡す
		model.addAttribute("staffEditDto", staffEdit);

		return "staff/staff-edit";
	}

	// 職員情報を更新する
	@PostMapping("/staff/{staffId}/edit")
	public String staffEditUpdate(
			@PathVariable Integer staffId,
			@Valid @ModelAttribute("staffEditDto") StaffEditDto dto,
			BindingResult result,
			Model model) {

		// ログイン中の認証情報を取得
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// 認証情報からログイン中のユーザー情報を取得
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();

		// ログイン中の職員の権限IDを取得
		Integer loginRoleId = loginUser.getRoleId();

		// 院内システム管理者か判定
		boolean isAdmin = Integer.valueOf(2).equals(loginRoleId);

		// 院内システム管理者以外は更新できない
		if (!isAdmin) {
			throw new AccessDeniedException(
					"職員情報を更新する権限がありません。");
		}

		// 入力内容にエラーがある場合は編集画面に戻す
		if (result.hasErrors()) {
			model.addAttribute("positionList", staffService.getPositionList());
			model.addAttribute("roleList", staffService.getRoleList());
			return "staff/staff-edit";
		}

		try {
			// 職員情報を更新
			staffService.updateStaff(staffId, dto);

		} catch (IllegalArgumentException e) {
			// 更新時にエラーが発生した場合は編集画面に戻す
			model.addAttribute("positionList", staffService.getPositionList());
			model.addAttribute("roleList", staffService.getRoleList());
			model.addAttribute("editError", e.getMessage());
			return "staff/staff-edit";
		}

		// 更新完了後、職員一覧画面へ戻る
		return "redirect:/staff";
	}
}
