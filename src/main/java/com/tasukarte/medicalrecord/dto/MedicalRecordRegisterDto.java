package com.tasukarte.medicalrecord.dto;

import java.time.LocalDate;

public class MedicalRecordRegisterDto {
	private String deseaseName;
	private String outcome;
	private LocalDate outcomeDate;
	private String progressS;
	private String progressO;
	private String progressA;
	private String progressP;
	private String procedure;
	private LocalDate followUpDate;
	private boolean hasPrescription;
	
	public String getDeseaseName() {
		return deseaseName;
	}
	public void setDeseaseName(String deseaseName) {
		this.deseaseName = deseaseName;
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