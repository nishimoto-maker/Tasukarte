package com.tasukarte.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.questionnaire.entity.MedicalHistory;

public interface MedicalHistoryRepository
        extends JpaRepository<MedicalHistory, Integer> {

}