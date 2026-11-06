package com.tasukarte.questionnaire.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "medical_histories")
@Getter
@Setter
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_history_id")
    private Integer medicalHistoryId;

    @Column(name = "questionnaire_id", nullable = false)
    private Integer questionnaireId;

    @Column(name = "disease_name", nullable = false)
    private String diseaseName;

    @Column(name = "medical_record_id")
    private Integer medicalRecordId;
}