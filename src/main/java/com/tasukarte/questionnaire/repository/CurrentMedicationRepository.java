package com.tasukarte.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.questionnaire.entity.CurrentMedication;

public interface CurrentMedicationRepository
        extends JpaRepository<CurrentMedication, Integer> {

}