package com.tasukarte.patient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
	@GetMapping("/patients/search")
	public String patientsearch() {
		return "patientsSearch";
	}
}
