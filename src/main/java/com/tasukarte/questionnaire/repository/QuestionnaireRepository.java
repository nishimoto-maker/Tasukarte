package com.tasukarte.questionnaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.questionnaire.entity.Questionnaire;

public interface QuestionnaireRepository
        extends JpaRepository<Questionnaire, Integer> {

    /* 患者IDから問診票一覧取得 */
    List<Questionnaire> findByPatientId(Integer patientId);
}