package com.tasukarte.questionnaire.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 問診票登録画面用DTO
 *
 * 1画面で
 * ・問診票
 * ・既往歴
 * ・現在服用薬
 * をまとめて受け取る。
 */
@Getter
@Setter
public class QuestionnaireForm {

    /* 患者ID */
    @NotNull
    private Integer patientId;

    /* 来院目的 */
    @NotBlank
    private String visitPurpose;

    /* 現在の症状 */
    @NotBlank
    private String symptom;

    /* 症状発症時期 */
    @NotBlank
    private String symptomOnset;

    /**
     * 既往歴
     *
     * テキストエリアで入力された値を保持する。
     * 改行区切りで複数登録を想定。
     *
     * 例:
     * 高血圧
     * 糖尿病
     * 喘息
     */
    @NotBlank
    private String medicalHistory;

    /**
     * 現在服用薬
     *
     * テキストエリアで入力された値を保持する。
     * 改行区切りで複数登録を想定。
     *
     * 例:
     * ロキソニン
     * ムコスタ
     * カロナール
     */
    @NotBlank
    private String currentMedication;
}