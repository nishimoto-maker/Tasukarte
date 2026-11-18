package com.tasukarte.patient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PatientController {
	
	
	//【患者情報管理TOP】
	@GetMapping("/patients")
	public String patients() {
		return "patient/patientTop";
	}
	
	//【患者情報登録】
	@GetMapping("/patients/new")
	public String patientsnew() {
		return "patient/patientNew";
	}
	
	//【患者情報検索】
	// 患者情報検索の条件を取得
	@GetMapping("/patients/search")
	public String patientsearch(
			@ModelAttribute PatientSearchDto searchDto,
			Model model) {
		
	    return "patient/patientSearch";
	}
}
