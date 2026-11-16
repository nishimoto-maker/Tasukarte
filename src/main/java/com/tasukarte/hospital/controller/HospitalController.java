package com.tasukarte.hospital.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tasukarte.hospital.entity.Hospital;
import com.tasukarte.hospital.service.HospitalService;

@Controller
public class HospitalController {
	// HospitalServiceを利用するための変数
	private final HospitalService hospitalService;
	
	@GetMapping("/admin/hospital")
	public String showHospitalList(Model model) {

	    // 病院情報を全件取得する
	    List<Hospital> hospitals = hospitalService.getAllHospitals();

	    // 取得した病院情報を画面に渡す
	    model.addAttribute("hospitals", hospitals);

	    return "hospital/hospital-list";
	}
	
	// HospitalServiceを受け取るコンストラクタ
	public HospitalController(HospitalService hospitalService) {
	    this.hospitalService = hospitalService;
	    

	}
}
