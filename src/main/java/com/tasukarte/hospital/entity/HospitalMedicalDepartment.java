package com.tasukarte.hospital.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "hospital_medical_department")
public class HospitalMedicalDepartment {
	// 複合主キーを管理する
    @EmbeddedId
    private HospitalMedicalDepartmentId id;

    // 病院Entityとの関連付け
    // 複合主キーのhospitalIdと対応させる
    @MapsId("hospitalId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalManagement hospital;

    // 診療科Entityとの関連付け
    // 複合主キーのmedicalDepartmentIdと対応させる
    @MapsId("medicalDepartmentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_department_id", nullable = false)
    private MedicalDepartment medicalDepartment;
	
	public HospitalMedicalDepartment() {
	}

	public HospitalMedicalDepartmentId getId() {
		return id;
	}

	public void setId(HospitalMedicalDepartmentId id) {
		this.id = id;
	}

	public HospitalManagement getHospital() {
		return hospital;
	}

	public void setHospital(HospitalManagement hospital) {
		this.hospital = hospital;
	}

	public MedicalDepartment getMedicalDepartment() {
		return medicalDepartment;
	}

	public void setMedicalDepartment(MedicalDepartment medicalDepartment) {
		this.medicalDepartment = medicalDepartment;
	}
}