package com.tasukarte.medicalrecord.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tasukarte.patient.entity.Patient;

// 指定したURLにアクセスされた時に、
// 対応したメソッドを呼び出すクラスであることを指示
@Controller
// このControllerで共通するURL
@RequestMapping("/medical-information")
public class MedicalInformationController {
	
	// このContrllerで使うService(データ処理担当クラス)を
	// 保持するフィールドを定義
	private final PatientService patientService;
	
	// コンストラクタで注入
	public MedicalInformationController (
			PatientService patientService
			) {
		this.patientService = patientService;
	}
	
	// 【診療情報管理トップ】初回表示
	// 初回アクセス時はデータ取得しないため
	// そもそもアクセス権があるかどうかのチェックは、どっか別でやってくれてる？
	@GetMapping
	public String showMedicalInfomationTop() {
		return "medical-records/medical-information";
	}
	
	// 【トップ】から検索された患者を表示するとき
	// 空欄で検索を押されても検索しない
	// 患者氏名で検索
	// いずれはページネーションもやる
	// 対象データが0件だった時の表示はHTML側のth:if
	@GetMapping("/search")
	public String searchPatients(
			// URLの直接入力によるアクセスがあったとしても
			// エラーで止めずelse句に回せるようにrequired=falseをつける
			@RequestParam(required=false) String searchPatientName,
			Model model
			) {
		if (searchPatientName != null && !searchPatientName.isBlank()) {
			// 検索欄に正しく入力があった場合は検索を実行
			List<Patient> patients = patientService.searchPatients(searchPatientName);
			model.addAttribute("patients", patients);
		} else {
			// 空欄でボタンが押された場合、エラーメッセージを渡す
			model.addAttribute("errormessage", "患者名を入力して下さい。");
		}
		
		return "medical-records/medical-information";
	}
	
	// トップから選択された患者の詳細情報と、各種登録へ遷移できるボタンが配置された
	// 【患者情報照会・登録内容選択】を表示
	// @PathVariableで患者ID取得、それで患者情報取得、渡す
	@GetMapping("/patients/{patientId}")
	public String showPatientRegistrationMenu(
			@PathVariable Integer id,
			@RequestParam String searchPatientName,
			Model model
			) {
		// URLに含められたIDで患者を特定し情報を取得、渡す
		Patient patient = patientService.findPatientById(id);
		model.addAttribute("patient", patient);
		// トップに戻ったとき検索状態を復元するために渡す
		model.addAttribute("searchPatientName", searchPatientName);
		return "medical-records/patient-detail";
	}
}
