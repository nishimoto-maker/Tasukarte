
package com.tasukarte.patient.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "allergies")
public class Allergies {

	//アレルギーID
	//主キー、自動連番、カラム定義
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergy_id", nullable = false)
    private Integer allergyId;

    //患者ID
    //必須、カラム定義
    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    //アレルギー種別
    //必須、100文字まで、カラム定義
    @Column(name = "allergy_type", nullable = false, length = 100)
    private String allergyType;

    //アレルギー症状
    //必須、カラム定義
    @Column(name = "allergy_symptom", nullable = false)
    private String allergySymptom;
    
    
    //getter/setter
	public Integer getAllergyId() {
		return allergyId;
	}

	public void setAllergyId(Integer allergyId) {
		this.allergyId = allergyId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getAllergyType() {
		return allergyType;
	}

	public void setAllergyType(String allergyType) {
		this.allergyType = allergyType;
	}

	public String getAllergySymptom() {
		return allergySymptom;
	}

	public void setAllergySymptom(String allergySymptom) {
		this.allergySymptom = allergySymptom;
	}

}
