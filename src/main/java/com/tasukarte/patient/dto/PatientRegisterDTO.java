package com.tasukarte.patient.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PatientRegisterDTO {
	private String name;
	private String kata;
	private LocalDate birthDay;
	private Short gender;
	private String phoneNumber;
	private String emailAddress;
	private String address;
	private String postCode;
	private String bloodType;
	private Boolean rh;
	private Boolean allergy;
	private BigDecimal weight;
	private BigDecimal height;
	private List<AllergieRegisterDTO> allergies;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKata() {
		return kata;
	}
	public void setKata(String kata) {
		this.kata = kata;
	}
	public LocalDate getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}
	public Short getGender() {
		return gender;
	}
	public void setGender(Short gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public Boolean getRh() {
		return rh;
	}
	public void setRh(Boolean rh) {
		this.rh = rh;
	}
	public Boolean getAllergy() {
		return allergy;
	}
	public void setAllergy(Boolean allergy) {
		this.allergy = allergy;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public BigDecimal getHeight() {
		return height;
	}
	public void setHeight(BigDecimal height) {
		this.height = height;
	}
	public List<AllergieRegisterDTO> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<AllergieRegisterDTO> allergies) {
		this.allergies = allergies;
	}
	

}
