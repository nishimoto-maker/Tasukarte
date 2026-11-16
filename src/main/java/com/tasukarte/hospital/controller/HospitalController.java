package com.tasukarte.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tasukarte.hospital.service.HospitalService;

@Controller
public class HospitalController {
	// HospitalServiceを利用するための変数
	private final HospitalService hospitalService;
	
	// 病院一覧画面を表示する
	@GetMapping("/admin/hospital")
	public String showHospitalList() {

	    return "hospital/list";
	}
	
	// HospitalServiceを受け取るコンストラクタ
	public HospitalController(HospitalService hospitalService) {
	    this.hospitalService = hospitalService;
	    

	}
}
