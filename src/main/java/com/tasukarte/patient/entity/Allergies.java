
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergy_id", nullable = false)
    private Integer allergyId;

    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    @Column(name = "allergy_type", nullable = false, length = 100)
    private String allergyType;

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
