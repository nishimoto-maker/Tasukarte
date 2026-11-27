package com.tasukarte.medicalrecord.controller;

import java.util.Collections;
import java.util.List;

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
import com.tasukarte.medicalrecord.dto.MedicalRecordSearchDto;
import com.tasukarte.medicalrecord.entity.MedicalRecord;
import com.tasukarte.medicalrecord.service.MedicalRecordService;
import com.tasukarte.patient.entity.Patient;
import com.tasukarte.patient.service.PatientService;

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
	@PostMapping("/register/{patientId}/confirm")
	public String confirmRegister(
			@PathVariable Integer patientId,
			// 入力内容をDTOに格納し、バリデーションチェックをし、Modelにaddする
			@Valid @ModelAttribute MedicalRecordRegisterDto medicalRecordRegisterDto,
			BindingResult result,
			Model model
			) {
		model.addAttribute("patient", patientService.findPatientById(patientId));
		
		if (result.hasErrors()) {
			// バリデーションエラーがあれば戻す
			return "medicalrecords/register";
		} else {
			// 無ければ【登録内容確認】へ
			return "medicalrecords/confirm";
		}
	}
	
	// 【登録内容確認】から修正するために【登録】へ戻るためのController
	@PostMapping("register/{patientId}/back")
	public String backToRegisterForm(
			@PathVariable Integer patientId,
			// 入力内容をDTOに格納し、Modelにaddする
			@ModelAttribute MedicalRecordRegisterDto medicalRecordRegisterDto,
			Model model
			) {
		model.addAttribute("patient", patientService.findPatientById(patientId));
		return "medicalrecords/register";
	}
	
	// DBに登録
	// エラーになったら【内容確認】へ、入力内容を渡す
	@PostMapping("/register/{patientId}")
	public String registerMedicalRecord(
			@AuthenticationPrincipal LoginUser loginUser,
			@PathVariable Integer patientId,
			@ModelAttribute MedicalRecordRegisterDto medicalRecordRegisterDto,
			Model model
			) {
		if (medicalRecordRegisterDto.isHasPrescription()) {
			// 処方箋有なら、DB登録後、患者情報とともに【処方箋作成】へ
			medicalRecordService.registerMedicalRecord(
					loginUser,
					medicalRecordRegisterDto,
					patientId
					);
			model.addAttribute("patient", patientService.findPatientById(patientId));
			
			return "処方箋作成画面";
		} else {
			// 処方箋無なら【完了】へ
			return "redirect:/medical-records/register/{patientId}/complete";
		}
	}
	
	// DB登録後、【完了】を表示
	@GetMapping("/register/{patientId}/complete")
	public String showRegisterComplete() {
		return "medical-records/complete";
	}
	
	// 【一覧】初回アクセス 検索結果を表示しない
	// 検索条件を入れるために、空のDTOを渡しておく
	@GetMapping()
	public String showMedicalRecordsList(Model model) {
		model.addAttribute("medicalRecordSearchDto", new MedicalRecordSearchDto());
		return "medicalrecords/list";
	}
	
	// 診療記録の検索
	// 当院で作成したもののみ取得可能、【一覧】に渡す
	// 並び順は作成日の降順固定、すべて空欄なら作成日降順の全件取得
	@GetMapping("/search")
	public String searchMedicalRecords(
			@Valid @ModelAttribute MedicalRecordSearchDto searchDto,
			BindingResult result,
			Model model
			) {
		List<MedicalRecord> medicalRecordsList;
		
		// 各入力欄に不備がある場合、空のリストを返してメッセージを表示する
		if (result.hasErrors()) {
			medicalRecordsList = Collections.emptyList();
			model.addAttribute("medicalRecords", medicalRecordsList);
		}
		
		medicalRecordsList = medicalRecordService.searchMedicalRecords(searchDto);
		model.addAttribute("medicalRecordsList", medicalRecordsList);
		
		return "medicalrecords/list";
	}
	
	// 1件選んで詳細を表示
	@GetMapping("/{recordId}")
	public String showMedicalRecord(
			@PathVariable Integer recordId,
			Model model
			) {
		// IDで診療記録を1件取得
		MedicalRecord medicalRecord = medicalRecordService.findMedicalRecordById(recordId);
		
		// ↑で取得した診療記録から患者を取り出す
		// 設定によってはDBへの追加アクセスが発生するらしい、要検証
		// ヤバそうなら画面表示用のDTOを別途作ってそれを渡すようにする
		Patient patient = medicalRecord.getPatient();
		
		// ↑の患者に関連する診療記録を一覧取得
		List<MedicalRecord> patientRecords = medicalRecordService.findPatientRecords(p.getPatientId());
		
		model.addAttribute("medicalRecord", medicalRecord);
		model.addAttribute("patient", patient);
		model.addAttribute("patientRecords", patientRecords);
		return "medicalrecords/medicalrecord-detail";
	}
	
	// 【編集】の表示
	@GetMapping("/{recordId}/edit")
	public String showMedicalRecordEdit() {
		return "【登録(編集)】";
	}
	
	// 編集内容の確認
	@PostMapping("/{recordId}/edit/confirm")
	public String showMedicalRecordUpdateConfirmation() {
		return "【登録内容確認(編集内容確認)】";
	}
	
	// 【編集確認】から【編集】に戻る用
	@PostMapping("/{recordId}/edit/back")
	public String backToEditForm() {
		return "【登録(編集)】";
	}
	
	// DBを更新
	@PostMapping("/{recordId}/edit")
	public String updateMedicalRecord() {
		return "redirect:/medical-records/{recordId}/edit/complete";
	}
	
	// DB更新後、【完了】を表示
	@GetMapping("/{recordId}/edit/complete")
	public String showUpdateComplete() {
		return "medical-records/complete";
	}
}
