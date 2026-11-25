package com.tasukarte.medicalrecord.dto;

public class MedicalRecordSearchDto {
	private String patientName;
	private Boolean continuing;
	private String diseaseName;
	private Integer outcome;
	
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	public Boolean getMedicalTreatmentStatus() {
		return continuing;
	}
	public void setMedicalTreatmentStatus(Boolean continuing) {
		this.continuing = continuing;
	}
	
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	
	public Integer getOutcome() {
		return outcome;
	}
	public void setOutcome(Integer outcome) {
		this.outcome = outcome;
	}
}