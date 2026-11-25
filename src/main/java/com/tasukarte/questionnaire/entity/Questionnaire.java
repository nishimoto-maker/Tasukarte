package com.tasukarte.questionnaire.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * 問診票情報を管理するEntityクラス
 *
 * questionnairesテーブルとマッピングする。
 *
 * 患者が来院した際の問診内容を保持する。
 * 既往歴および現在服用薬については別テーブルで管理する。
 */
@Entity
@Table(name = "questionnaires")
//@Getter
//@Setter
public class Questionnaire {

    /**
     * 問診票ID
     *
     * questionnaires.questionnaire_id(PK)
     * 自動採番される主キー
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionnaire_id")
    private Integer questionnaireId;

    /**
     * 患者ID
     *
     * patients.patient_id(FK)を参照する。
     *
     * 現在はDDLを手動管理しているため、
     * リレーションを定義せずIDのみ保持する。
     *
     * 将来的にPatientEntityとの関連付けを行う場合は
     * @ManyToOne + @JoinColumnで実装する。
     */
    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    /**
     * 来院目的
     *
     * 例：
     * ・定期受診
     * ・健康診断
     * ・体調不良
     */
    @NotBlank
    @Column(name = "visit_purpose", nullable = false)
    private String visitPurpose;

    /**
     * 現在の症状
     * 患者が申告した症状を保持する。
     */
    @NotBlank
    @Column(name = "symptom", nullable = false)
    private String symptom;
    
    /**
     * 症状発症時期
     *
     * 例：
     * ・3日前から
     * ・昨日の夜から
     * ・今朝から
     */
    @NotBlank
    @Column(name = "symptom_onset", nullable = false)
    private String symptomOnset;

    // 以下、getter・setter
    
	public Integer getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(Integer questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getVisitPurpose() {
		return visitPurpose;
	}

	public void setVisitPurpose(String visitPurpose) {
		this.visitPurpose = visitPurpose;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getSymptomOnset() {
		return symptomOnset;
	}

	public void setSymptomOnset(String symptomOnset) {
		this.symptomOnset = symptomOnset;
	}
}