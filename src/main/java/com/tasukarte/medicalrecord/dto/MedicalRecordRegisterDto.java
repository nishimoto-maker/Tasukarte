package com.tasukarte.medicalrecord.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public class MedicalRecordRegisterDto {
	@NotBlank(message="入力必須です。")
	@Max(value=100, message="100字以内で入力して下さい。")
	private String diseaseName;
	
	private String outcome;
	private LocalDate outcomeDate;
	
	@NotBlank(message="入力必須です。")
	@Max(value=1000, message="1000字以内で入力して下さい。")
	private String progressS;
	
	@NotBlank(message="入力必須です。")
	@Max(value=1000, message="1000字以内で入力して下さい。")
	private String progressO;
	
	@NotBlank(message="入力必須です。")
	@Max(value=1000, message="1000字以内で入力して下さい。")
	private String progressA;
	
	@NotBlank(message="入力必須です。")
	@Max(value=1000, message="1000字以内で入力して下さい。")
	private String progressP;
	
	@NotBlank(message="入力必須です。")
	@Max(value=1000, message="1000字以内で入力して下さい。")
	private String procedure;
	
	private LocalDate followUpDate;
	
	@NotBlank(message="選択必須です。")
	private boolean hasPrescription;
	
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
	public LocalDate getOutcomeDate() {
		return outcomeDate;
	}
	public void setOutcomeDate(LocalDate outcomeDate) {
		this.outcomeDate = outcomeDate;
	}
	
	public String getProgressS() {
		return progressS;
	}
	public void setProgressS(String progressS) {
		this.progressS = progressS;
	}
	
	public String getProgressO() {
		return progressO;
	}
	public void setProgressO(String progressO) {
		this.progressO = progressO;
	}
	
	public String getProgressA() {
		return progressA;
	}
	public void setProgressA(String progressA) {
		this.progressA = progressA;
	}
	
	public String getProgressP() {
		return progressP;
	}
	public void setProgressP(String progressP) {
		this.progressP = progressP;
	}
	
	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	
	public LocalDate getFollowUpDate() {
		return followUpDate;
	}
	public void setFollowUpDate(LocalDate followUpDate) {
		this.followUpDate = followUpDate;
	}
	
	public boolean isHasPrescription() {
		return hasPrescription;
	}
	public void setHasPrescription(boolean hasPrescription) {
		this.hasPrescription = hasPrescription;
	}
}