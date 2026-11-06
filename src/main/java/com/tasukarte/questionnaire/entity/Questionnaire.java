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
@Table(name = "questionnaires")
@Getter
@Setter
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionnaire_id")
    private Integer questionnaireId;

    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    @Column(name = "visit_purpose", nullable = false)
    private String visitPurpose;

    @Column(name = "symptom", nullable = false)
    private String symptom;

    @Column(name = "symptom_onset", nullable = false)
    private String symptomOnset;
}