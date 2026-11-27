package com.tasukarte.patient.dto;

import java.time.LocalDate;

public class PatientListDTO {
	private String name;
	private String gender;
	private Integer age;
	private LocalDate createdAt;
	private LocalDate followUpDate;
	private LocalDate  medicalRecordDate;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDate getFollowUpDate() {
		return followUpDate;
	}
	public void setFollowUpDate(LocalDate followUpDate) {
		this.followUpDate = followUpDate;
	}
	public LocalDate getMedicalRecordDate() {
		return medicalRecordDate;
	}
	public void setMedicalRecordDate(LocalDate medicalRecordDate) {
		this.medicalRecordDate = medicalRecordDate;
	}
}
