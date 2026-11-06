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
@Table(name = "current_medications")
@Getter
@Setter
public class CurrentMedication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "current_medication_id")
    private Integer currentMedicationId;

    @Column(name = "questionnaire_id", nullable = false)
    private Integer questionnaireId;

    @Column(name = "medicine_name", nullable = false)
    private String medicineName;
}