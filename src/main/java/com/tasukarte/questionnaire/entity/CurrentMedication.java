package com.tasukarte.questionnaire.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 現在服用薬情報を管理するEntityクラス
 *
 * current_medicationsテーブルとマッピングする。
 *
 * 患者が現在服用している薬を保持する。
 * 問診票1件に対して複数の服用薬を登録できる想定。
 */
@Entity
@Table(name = "current_medications")
@Getter
@Setter
public class CurrentMedication {

    /**
     * 現在服用薬ID
     *
     * current_medications.current_medication_id(PK)
     * 自動採番される主キー
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "current_medication_id")
    private Integer currentMedicationId;

    /**
     * 問診票情報
     *
     * questionnaires.questionnaire_id(FK)を参照する。
     *
     * 問診票と現在服用薬は
     * 「1対多」の関係を想定している。
     *
     * 例：
     * 問診票A
     * ├ ロキソニン
     * ├ ムコスタ
     * └ カロナール
     */
    @ManyToOne
    @JoinColumn(name = "questionnaire_id", nullable = false)
    private Questionnaire questionnaire;

    /**
     * 薬剤名
     *
     * 現在患者が服用している薬剤名を保持する。
     *
     * 例：
     * ・ロキソニン錠60mg
     * ・ムコスタ錠100mg
     * ・カロナール錠200mg
     */
    @Column(name = "medicine_name", nullable = false)
    private String medicineName;
}
