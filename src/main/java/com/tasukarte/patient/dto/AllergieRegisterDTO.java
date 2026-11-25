package com.tasukarte.patient.dto;

public class AllergieRegisterDTO {
	
	private String allergyType;
	private String allergySymptom;
	public String getAllergyType() {
		return allergyType;
	}
	public void setAllergyType(String allergyType) {
		this.allergyType = allergyType;
	}
	public String getAllergySymptom() {
		return allergySymptom;
	}
	public void setAllergySymptom(String allergySymptom) {
		this.allergySymptom = allergySymptom;
	}
	
}
