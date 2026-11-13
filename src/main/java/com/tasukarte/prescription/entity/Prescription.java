
package com.tasukarte.prescription.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @Column(name = "medical_record_id")
    private Integer medicalRecordId;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "issue_at", nullable = false)
    private LocalDateTime issueAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

	public Integer getMedicalRecordId() {
		return medicalRecordId;
	}

	public void setMedicalRecordId(Integer medicalRecordId) {
		this.medicalRecordId = medicalRecordId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDateTime getIssueAt() {
		return issueAt;
	}

	public void setIssueAt(LocalDateTime issueAt) {
		this.issueAt = issueAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

    // Getter・Setterは後で追加
}
