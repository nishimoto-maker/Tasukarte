package com.tasukarte.staff.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.staff.dto.StaffRegisterDto;
import com.tasukarte.staff.entity.Staff;
import com.tasukarte.staff.service.StaffService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

// 職員登録に関する画面制御を担当するController
@Controller
public class StaffRegisterController {

	// StaffServiceを利用するための変数
	private final StaffService staffService;

	// コンストラクタでStaffServiceを受け取る
	public StaffRegisterController(StaffService staffService) {
		this.staffService = staffService;
	}

	// 職員登録画面を表示する
	@GetMapping("/staff/new")
	public String staffRegisterForm(
			Model model,
			HttpSession session) {

		// ログイン中の認証情報を取得
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// ログイン中のユーザー情報を取得
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();

		// ログイン中の職員の権限IDを取得
		Integer loginRoleId = loginUser.getRoleId();

		// 病院管理者かどうかを確認
		boolean isAdmin = Integer.valueOf(2).equals(loginRoleId);

		// 管理者以外はアクセスできない
		if (!isAdmin) {
			throw new AccessDeniedException("職員登録画面へのアクセス権限がありません。");
		}

		// セッションから入力内容を取得
		StaffRegisterDto dto = (StaffRegisterDto) session.getAttribute("staffRegisterDto");

		// セッションに入力内容がなければ新しいDTOを作成
		if (dto == null) {
			dto = new StaffRegisterDto();
		}

		// セッションにDTOを保存する
		session.setAttribute("staffRegisterDto", dto);

		// 入力フォームにDTOを渡す
		model.addAttribute("staffRegisterDto", dto);

		// 役職一覧を画面に渡す
		model.addAttribute("positionList", staffService.getPositionList());

		// 権限一覧を画面に渡す
		model.addAttribute("roleList", staffService.getRoleList());

		return "staff/staff-register";
	}

	// 職員登録の入力内容を確認する
	@PostMapping("/staff/confirm")
	public String staffRegisterConfirm(
			@Valid @ModelAttribute("staffRegisterDto") StaffRegisterDto dto,
			BindingResult result,
			Model model,
			HttpSession session) {

		// ログイン中の認証情報を取得
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// ログイン中のユーザー情報を取得
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();

		// ログイン中の職員の権限IDを取得
		Integer loginRoleId = loginUser.getRoleId();

		// 病院管理者かどうかを確認
		boolean isAdmin = Integer.valueOf(2).equals(loginRoleId);

		// 管理者以外はアクセスできない
		if (!isAdmin) {
			throw new AccessDeniedException(
					"職員登録確認画面へのアクセス権限がありません。");
		}

		// メールアドレスが既に登録されているか確認する
		if (staffService.isEmailAddressRegistered(dto.getEmailAddress())) {
			result.rejectValue(
					"emailAddress",
					"duplicate",
					"このメールアドレスは既に登録されています。");
		}

		// 入力エラーがある場合
		if (result.hasErrors()) {

			// 入力内容をセッションに保存する
			session.setAttribute("staffRegisterDto", dto);

			// 役職一覧を画面に渡す
			model.addAttribute("positionList", staffService.getPositionList());

			// 権限一覧を画面に渡す
			model.addAttribute("roleList", staffService.getRoleList());

			// 入力画面に戻る
			return "staff/staff-register";
		}

		// 入力内容をセッションに保存する
		session.setAttribute("staffRegisterDto", dto);

		// 確認画面に表示する役職名を取得
		String positionName = staffService.getPositionList().stream()
				.filter(position -> position.getPositionId().equals(dto.getPositionId()))
				.map(position -> position.getPositionName())
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("役職が見つかりません。"));

		// 確認画面に表示する権限名を取得
		String roleName = staffService.getRoleList().stream()
				.filter(role -> role.getRoleId().equals(dto.getRoleId()))
				.map(role -> role.getRoleName())
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("権限が見つかりません。"));

		// DTOを確認画面に渡す
		model.addAttribute("staffRegisterDto", dto);

		// 役職名を確認画面に渡す
		model.addAttribute("positionName", positionName);

		// 権限名を確認画面に渡す
		model.addAttribute("roleName", roleName);

		return "staff/staff-register-confirm";
	}

	// 職員登録を確定する
	@PostMapping("/staff/register")
	public String staffRegister(
			HttpSession session,
			Model model) {

		// ログイン中の認証情報を取得
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// ログイン中のユーザー情報を取得
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();

		// ログイン中の職員の権限IDを取得
		Integer loginRoleId = loginUser.getRoleId();

		// 病院管理者かどうかを確認
		boolean isAdmin = Integer.valueOf(2).equals(loginRoleId);

		// 管理者以外はアクセスできない
		if (!isAdmin) {
			throw new AccessDeniedException(
					"職員登録を実行する権限がありません。");
		}

		// セッションから入力内容を取得
		StaffRegisterDto dto = (StaffRegisterDto) session.getAttribute("staffRegisterDto");

		// セッションに入力内容がなければ入力画面に戻す
		if (dto == null) {
			return "redirect:/staff/new";
		}

		// 職員情報を作成する
		Staff staff = staffService.createStaff(
				dto,
				loginUser.getHospitalId());

		// 登録完了画面へ進む
		return "staff/staff-register-complete";
	}
}