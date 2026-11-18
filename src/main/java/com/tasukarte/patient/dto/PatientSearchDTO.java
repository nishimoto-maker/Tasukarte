package com.tasukarte.patient.dto;

import java.time.LocalDate;

//患者の検索結果をやり取りするDTO(紹介状)
public class PatientSearchDTO {
	
	private Integer patientId;
	
	private String name;
	
	private LocalDate birthDay;
	
	public PatientSearchDTO(
			Integer patientId,
			String name,
			LocalDate birthDay) {
		this.patientId = patientId;
		this.name = name;
		this.birthDay = birthDay;
	}
	
	public Integer getPatientId() {
		return patientId;
	}
	
	public String getName() {
		return name;
	}
	
	public LocalDate getBirthDay() {
		return birthDay;
	}
}
