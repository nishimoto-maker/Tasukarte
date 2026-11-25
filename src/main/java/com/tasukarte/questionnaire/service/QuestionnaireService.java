package com.tasukarte.questionnaire.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasukarte.questionnaire.dto.QuestionnaireForm;
import com.tasukarte.questionnaire.entity.CurrentMedication;
import com.tasukarte.questionnaire.entity.MedicalHistory;
import com.tasukarte.questionnaire.entity.Questionnaire;
import com.tasukarte.questionnaire.repository.CurrentMedicationRepository;
import com.tasukarte.questionnaire.repository.MedicalHistoryRepository;
import com.tasukarte.questionnaire.repository.QuestionnaireRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionnaireService {

    private final QuestionnaireRepository questionnaireRepository;

    private final MedicalHistoryRepository medicalHistoryRepository;

    private final CurrentMedicationRepository currentMedicationRepository;

    /* 問診票登録 */
    @Transactional
    public void register(QuestionnaireForm form) {

        /* questionnaires登録 */
        Questionnaire questionnaire =
                new Questionnaire();

        questionnaire.setPatientId(
                form.getPatientId());

        questionnaire.setVisitPurpose(
                form.getVisitPurpose());

        questionnaire.setSymptom(
                form.getSymptom());

        questionnaire.setSymptomOnset(
                form.getSymptomOnset());

        Questionnaire savedQuestionnaire =
                questionnaireRepository.save(
                        questionnaire);

        /*
         * medical_histories登録
         *
         * テキストエリアで
         * 改行区切り入力を想定
         */
        if (form.getMedicalHistory() != null
                && !form.getMedicalHistory().isBlank()) {

            String[] histories =
                    form.getMedicalHistory()
                            .split("\\r?\\n");

            for (String diseaseName : histories) {

                if (diseaseName.isBlank()) {
                    continue;
                }

                MedicalHistory history =
                        new MedicalHistory();

                history.setQuestionnaire(
                        savedQuestionnaire);

                history.setDiseaseName(
                        diseaseName.trim());

                medicalHistoryRepository
                        .save(history);
            }
        }

        /*
         * current_medications登録
         *
         * テキストエリアで
         * 改行区切り入力を想定
         */
        if (form.getCurrentMedication() != null
                && !form.getCurrentMedication().isBlank()) {

            String[] medicines =
                    form.getCurrentMedication()
                            .split("\\r?\\n");

            for (String medicineName : medicines) {

                if (medicineName.isBlank()) {
                    continue;
                }

                CurrentMedication medication =
                        new CurrentMedication();

                medication.setQuestionnaire(
                        savedQuestionnaire);

                medication.setMedicineName(
                        medicineName.trim());

                currentMedicationRepository
                        .save(medication);
            }
        }
    }
}