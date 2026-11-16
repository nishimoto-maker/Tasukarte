
package com.tasukarte.patient.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "allergies")
public class Allergie {

    // アレルギーID
    // 主キー、自動連番
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergy_id", nullable = false)
    private Integer allergyId;

    // 患者ID
    // 必須、Patientエンティティとの多対一の関連付け
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // アレルギー種別
    // 必須、100文字まで
    @NotBlank
    @Size(max = 100)
    @Column(name = "allergy_type", nullable = false, length = 100)
    private String allergyType;

    // アレルギー症状
    // 必須
    @NotBlank
    @Column(name = "allergy_symptom", nullable = false)
    private String allergySymptom;


    // getter / setter

    public Integer getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(Integer allergyId) {
        this.allergyId = allergyId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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