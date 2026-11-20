package com.tasukarte.medicalrecord.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.medicalrecord.dto.MedicalRecordRegisterDto;
import com.tasukarte.medicalrecord.service.MedicalRecordService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/medical-records")
public class MedicalRecordController {
	// 必要なServiceを持つためのフィールドを定義
	private final MedicalRecordService medicalRecordService;
	private final PatientService patientService;
	
	// コンストラクタで注入
	public MedicalRecordController(
			MedicalRecordService medicalRecordService,
			PatientService patientService
			) {
		this.medicalRecordService = medicalRecordService;
		this.patientService = patientService;
	}
	
	// 【診療記録登録】表示
	@GetMapping("/register/{patientId}")
	public String showRegisterForm(
			@PathVariable Integer patientId,
			Model model
			) {
		// HTML側でth:object/th:fieldで対応させるために、
		// 空の診療記録登録DTOを渡しておく
		model.addAttribute("medicalRecordRegisterDto", new MedicalRecordRegisterDto());
		model.addAttribute("patient", patientService.findPatientById(patientId));
		return "medicalrecords/register";
	}
	
	// 【登録内容確認】表示
	// POSTにすることで不正アクセス(URL直アクセスによるエラー)を防ぐ一手になる
	// @ModelAttributeでDTOに入れて【登録】の内容を保持、DB保存にも修正で戻る用にも
	@PostMapping("/register/{patientId}/confirm")
	public String confirmRegister(
			@PathVariable Integer patientId,
			@Valid @ModelAttribute MedicalRecordRegisterDto medicalRecordRegisterDto,
			BindingResult result,
			Model model
			) {
		model.addAttribute("patient", patientService.findPatientById(patientId));
		
		if (result.hasErrors()) {
			return "medicalrecords/register";
		} else {
			return "medicalrecords/confirm";
		}
	}
	
	// 【登録内容確認】から修正するために【登録】へ戻るためのController
	@PostMapping("register/{patientId}/back")
	public String backToRegisterForm(
			@PathVariable Integer patientId,
			@ModelAttribute MedicalRecordRegisterDto medicalRecordRegisterDto,
			Model model
			) {
		model.addAttribute("patient", patientService.findPatientById(patientId));
		return "medicalrecords/register";
	}
	
	// DBに登録
	// エラーになったら【内容確認】へ、入力内容を渡す
	// 登録出来たら「処方箋有無」に応じて遷移先を変える
	@PostMapping("/register/{patientId}")
	public String registerMedicalRecord(
			@AuthenticationPrincipal LoginUser loginUser,
			@PathVariable Integer patientId,
			@ModelAttribute MedicalRecordRegisterDto medicalRecordRegisterDto,
			Model model
			) {
		if (medicalRecordRegisterDto.isHasPrescription()) {
			medicalRecordService.registerMedicalRecord(
					loginUser,
					medicalRecordRegisterDto,
					patientId
					);
			model.addAttribute("patient", patientService.findPatientById(patientId));
			
			return "処方箋作成画面";
		} else {
			return "medicalrecords/complete";
		}
	}
	
	// 【登録完了】の表示
	// 【トップ】へ戻れるリンク有り、戻ったとき検索状況はリセット
	@GetMapping("/register/{patientId}/complete")
	public String showRegistrationComplete() {
		return "【登録完了】";
	}
	
	// 【一覧】初回アクセス
	// 最初は検索結果を表示しない
	@GetMapping()
	public String showMedicalRecordList() {
		return "【一覧・検索】";
	}
	
	// 診療記録の検索
	// 当院で作成したもののみ取得可能、【一覧】に渡す
	@GetMapping("/search")
	public String searchMedicalRecords() {
		return "【一覧・検索】";
	}
	
	// 1件選んで詳細を表示
	@GetMapping("/{recordId}")
	public String showMedicalRecord() {
		return "【詳細】";
	}
	
	// 【編集】の表示
	@GetMapping("/{recordId}/edit")
	public String showMedicalRecordEdit() {
		return "【登録】";
	}
	
	// 編集内容の確認
	@PostMapping("/{recordId}/edit/confirm")
	public String showMedicalRecordUpdateConfirmation() {
		return "【編集内容確認】";
	}
	
	// DBを更新
	@PostMapping("/{recordId}/edit")
	public String updateMedicalRecord() {
		return "更新完了にリダイレクト";
	}
	
	// DB更新後、完了ページを表示する
	// 送るものも受け取るものもない
	@GetMapping("/{recordId}/edit/complete")
	public String showUpdateComplete() {
		return "【更新完了】";
	}
}
