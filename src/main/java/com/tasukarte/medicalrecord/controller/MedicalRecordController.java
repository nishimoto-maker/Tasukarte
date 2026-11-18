package com.tasukarte.medicalrecord.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tasukarte.medicalrecord.dto.MedicalRecordRegisterDto;
import com.tasukarte.medicalrecord.service.MedicalRecordService;

@Controller
@RequestMapping("/medical-records")
public class MedicalRecordController {
	private final MedicalRecordService medicalRecordService;
	private final PatientService patientService;
	
	public MedicalRecordController(
			MedicalRecordService medicalRecordService,
			PatientService patientService
			) {
		this.medicalRecordService = medicalRecordService;
		this.patientService = patientService;
	}
	
	// 【診療記録登録】の表示
	// 患者情報渡す、【内容確認】から戻ってきたときに、元々入力していた内容を反映させる、
	// (HTML側の話だけど)th:object, th:fieldで対応させるために、
	// 空の診療記録登録DTOを渡しておく
	@GetMapping("/register/{patientId}")
	public String showRegisterForm(
			@PathVariable Integer patientId,
			Model model
			) {
		model.addAttribute("medicalRecordRegisterDto", new MedicalRecordRegisterDto());
		model.addAttribute("patient", patientService.findPatientById(patientId));
		return "medicalrecords/診療記録登録フォーム";
	}
	
	// 【登録内容確認】表示
	// POSTにすることで不正アクセス(URL直アクセスによるエラー)を防ぐ一手になる
	// @ModelAttributeでDTOに入れて【登録】の内容を保持、DB保存にも修正で戻る用にも
	@PostMapping("/register/{patientId}/confirm")
	public String showMedicalRecordRegistrationConfirmation() {
		return "medicalrecords/登録内容確認";
	}
	
	// DBに登録
	// エラーになったら【内容確認】へ、入力内容を渡す
	// 登録出来たら【完了】へリダイレクト
	@PostMapping("/register/{patientId}")
	public String registerMedicalRecord() {
		return "【登録完了】にリダイレクト";
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
