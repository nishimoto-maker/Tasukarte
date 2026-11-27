package com.tasukarte.patient.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tasukarte.patient.dto.PatientListDTO;
import com.tasukarte.patient.dto.PatientRegisterDTO;
import com.tasukarte.patient.dto.PatientSearchDTO;
import com.tasukarte.patient.service.PatientService;

@Controller
//共通のURL
@RequestMapping("/patients")
public class PatientController {
	
	// このContrllerで使うService(データ処理担当クラス)を
	// 保持するフィールドを定義
	private final PatientService patientService;
	
	// コンストラクタで注入
	public PatientController (
			PatientService patientService
			) {
		this.patientService = patientService;
	}	
	
	//【患者情報管理TOP】
	@GetMapping("")
	public String patients() {
		return "patient/patientTop";
	}
	
	//【患者情報登録】
	//	登録画面を表示
	@GetMapping("/new")
	public String patientsnew(Model model) {
		
		model.addAttribute("patientRegisterDTO",new PatientRegisterDTO());
		
		return "patient/patientNew";
		
	}
	
	//【患者情報確認】
	//登録画面から送信された情報を受け取り、確認画面を表示
	@PostMapping("/confirm")
	public String registerPatient(
			@ModelAttribute PatientRegisterDTO patientRegisterDTO) {
		
		patientService.registerPatient(patientRegisterDTO);
		
		return "patient/patient";
	}
	
	
	//【患者情報登録完了】
	//完了画面を表示
	@GetMapping("/complete"){
		
	}
	
	//【患者情報検索TOP】
	//検索条件が未入力の状態で検索画面を表示
	@GetMapping("/search-top")
	public String showPatientSearchTop(Model model) {

	    model.addAttribute("patients", new ArrayList<PatientListDTO>());

	    return "patient/patientSearch";
	}
	
	
	//【患者情報検索】
	// 患者情報検索の条件を取得
	@GetMapping("/search")
	public String patientsearch(
			//HTMLの値をPatientSearchDTOのフィールドに自動的に格納
			@ModelAttribute PatientSearchDTO searchDto,
	        Model model) {
		
		//サービスを呼び出し、serchDtoに渡す
		List<PatientListDTO> patients = 
				patientService.searchPatients(searchDto);
		
		// 該当する患者情報をHTMLに渡す
		model.addAttribute("patients",patients);

	    return "patient/patientSearch";
	}
}
