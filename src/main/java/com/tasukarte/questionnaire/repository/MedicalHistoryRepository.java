package com.tasukarte.questionnaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.questionnaire.entity.MedicalHistory;

public interface MedicalHistoryRepository
        extends JpaRepository<MedicalHistory, Integer> {

    /* 問診票IDに紐づく既往歴一覧取得 */
    List<MedicalHistory>
        findByQuestionnaireQuestionnaireId(
            Integer questionnaireId);

}