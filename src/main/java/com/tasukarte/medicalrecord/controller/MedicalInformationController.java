package com.tasukarte.medicalrecord.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 指定したURLにアクセスされた時に、
// 対応したメソッドを呼び出すクラスであることを指示
@Controller
// このControllerで共通するURL
@RequestMapping("/medical-information")
public class MedicalInformationController {
	// 【診療情報管理トップ】初回表示
	// 初回アクセス時はデータ取得しないため
	@GetMapping
	public String showMedicalInfomationTop() {
		return "medicalrecords/medical-information";
	}
	
	// 【トップ】から検索された患者を表示するとき
	// 空欄で検索を押されても検索しない
	@GetMapping("/search")
	public String searchPatients() {
		return "medicalrecords";
	}
	
	// トップから選択された患者の詳細情報と、各種登録へ遷移できるボタンが配置された
	// 【患者情報照会・登録内容選択】を表示
	// @PathVariableで患者ID取得、それで患者情報取得、渡す
	@GetMapping("/patients/{patientId}")
	public String showPatientRegistrationMenu() {
		return "medicalrecords/患者情報照会・登録内容選択";
	}
}
