package com.tasukarte.questionnaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.questionnaire.entity.CurrentMedication;

public interface CurrentMedicationRepository
        extends JpaRepository<CurrentMedication, Integer> {

    /* 問診票IDに紐づく服用薬一覧取得 */
    List<CurrentMedication>
        findByQuestionnaireQuestionnaireId(
            Integer questionnaireId);

}