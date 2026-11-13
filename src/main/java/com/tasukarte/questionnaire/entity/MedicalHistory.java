package com.tasukarte.questionnaire.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 既往歴情報を管理するEntityクラス
 *
 * medical_historiesテーブルとマッピングする。
 *
 * 患者の過去の疾病歴や治療歴を保持する。
 * 問診票1件に対して複数の既往歴を登録できる想定。
 * 
 */
@Entity
@Table(name = "medical_histories")
@Getter
@Setter
public class MedicalHistory {

    /**
     * 既往歴ID
     *
     * medical_histories.medical_history_id(PK)
     * 自動採番される主キー
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_history_id")
    private Integer medicalHistoryId;

    /**
     * 問診票情報
     *
     * questionnaires.questionnaire_id(FK)を参照する。
     *
     * 問診票と既往歴は
     * 「1対多」の関係を想定している。
     *
     * 例：
     * 問診票A
     * ├ 高血圧
     * ├ 糖尿病
     * └ 喘息
     */
    @ManyToOne
    @JoinColumn(name = "questionnaire_id", nullable = false)
    private Questionnaire questionnaire;

    /**
     * 病名
     *
     * 患者の既往歴として登録される病名を保持する。
     *
     * 例：
     * ・高血圧
     * ・糖尿病
     * ・喘息
     * ・心筋梗塞
     */
    @NotBlank
    @Column(name = "disease_name", nullable = false)
    private String diseaseName;

    /**
     * 診療記録ID
     *
     * medical_records.medical_record_id(FK)を参照する。
     *
     * 既往歴に関連する診療記録との紐付けに利用する。
     *
     * 現在はDDLを手動管理しているため、
     * IDのみ保持する。
     *
     * 将来的にMedicalRecordEntityとの関連付けを行う場合は
     * @ManyToOne + @JoinColumnで実装する。
     */
    @Column(name = "medical_record_id")
    private Integer medicalRecordId;
}
