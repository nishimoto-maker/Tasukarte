package com.tasukarte.patient.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	// 患者情報検索の条件を取得（未入力可）
	@GetMapping("/patients/search")
	public String patientsearch(
	        @RequestParam(required = false) String name,
	        @RequestParam(required = false) String gender,
	        @RequestParam(required = false) Integer age,
	        @RequestParam(required = false) LocalDate startDate,
	        @RequestParam(required = false) LocalDate endDate,
	        @RequestParam(required = false) LocalDate startNextVisitDate,
	        @RequestParam(required = false) LocalDate endNextVisitDate,
	        @RequestParam(required = false) LocalDate startLastVisitDate,
	        @RequestParam(required = false) LocalDate endLastVisitDate,
	        Model model) {

	    return "patient/patientSearch";
	}
}
