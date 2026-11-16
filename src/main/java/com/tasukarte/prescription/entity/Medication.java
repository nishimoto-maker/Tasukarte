package com.tasukarte.prescription.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medications")
public class Medication {

    @Id
    @Column(name = "medication_id")
    private Integer medicationId;

    @Column(name = "medical_record_id", nullable = false)
    private Integer medicalRecordId;

    @Column(name = "medication_name", nullable = false)
    private String medicationName;

    @Column(name = "usage_dosage", nullable = false)
    private String usageDosage;

    @Column(name = "prescription_days", nullable = false)
    private Integer prescriptionDays;

	public Integer getMedicationId() {
		return medicationId;
	}

	public void setMedicationId(Integer medicationId) {
		this.medicationId = medicationId;
	}

	public Integer getMedicalRecordId() {
		return medicalRecordId;
	}

	public void setMedicalRecordId(Integer medicalRecordId) {
		this.medicalRecordId = medicalRecordId;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public String getUsageDosage() {
		return usageDosage;
	}

	public void setUsageDosage(String usageDosage) {
		this.usageDosage = usageDosage;
	}

	public Integer getPrescriptionDays() {
		return prescriptionDays;
	}

	public void setPrescriptionDays(Integer prescriptionDays) {
		this.prescriptionDays = prescriptionDays;
	}

    // Getter・Setterは後で追加
}
