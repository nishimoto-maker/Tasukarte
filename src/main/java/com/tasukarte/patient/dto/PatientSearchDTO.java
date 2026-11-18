package com.tasukarte.patient.dto;

import java.time.LocalDate;

public class PatientSearchDTO {
	private String name;
	private String gender;
	private Integer age;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate startNextVisitDate;
	private LocalDate endNextVisitDate;
	private LocalDate startLastVisitDate;
	private LocalDate endLastVisitDate;
	
	
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
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public LocalDate getStartNextVisitDate() {
		return startNextVisitDate;
	}
	public void setStartNextVisitDate(LocalDate startNextVisitDate) {
		this.startNextVisitDate = startNextVisitDate;
	}
	public LocalDate getEndNextVisitDate() {
		return endNextVisitDate;
	}
	public void setEndNextVisitDate(LocalDate endNextVisitDate) {
		this.endNextVisitDate = endNextVisitDate;
	}
	public LocalDate getStartLastVisitDate() {
		return startLastVisitDate;
	}
	public void setStartLastVisitDate(LocalDate startLastVisitDate) {
		this.startLastVisitDate = startLastVisitDate;
	}
	public LocalDate getEndLastVisitDate() {
		return endLastVisitDate;
	}
	public void setEndLastVisitDate(LocalDate endLastVisitDate) {
		this.endLastVisitDate = endLastVisitDate;
	}

}
