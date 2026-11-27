package com.tasukarte.hospital.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "medical_departments")
public class MedicalDepartment {
	//idを主キーにする。
	@Id
	//idを自動生成             //PostgreSQLのIDENTITYによる自動採番を利用
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medical_department_id")
	//診療科ID
	private Integer medicalDepartmentId;
	
	//診療科名
	@NotBlank
	@Column(name = "medical_department_name", nullable = false)
	private String medicalDepartmentName;
	
	public MedicalDepartment() {
	}
	
	public Integer getMedicalDepartmentId() {
		return medicalDepartmentId;
	}
	public void setMedicalDepartmentId(Integer medicalDepartmentId) {
		this.medicalDepartmentId = medicalDepartmentId;
	}
	
	public String getMedicalDepartmentName() {
		return medicalDepartmentName;
	}
	public void setMedicalDepartmentName(String medicalDepartmentName) {
		this.medicalDepartmentName = medicalDepartmentName;
	}
}