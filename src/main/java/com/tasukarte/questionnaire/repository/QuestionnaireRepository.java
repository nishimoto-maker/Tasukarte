package com.tasukarte.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.questionnaire.entity.Questionnaire;

public interface QuestionnaireRepository
        extends JpaRepository<Questionnaire, Integer> {

}