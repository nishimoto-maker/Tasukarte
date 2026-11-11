
package com.tasukarte.patient.entity;

import java.time.LocalDate;

import com.tasukarte.hospital.entity.HospitalManagement;
import com.tasukarte.referral.entity.Referral;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "shared_patients")
public class SharedPatient {

	//共有ID
	//主キー、自動連番、カラム定義
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "share_patient_id", nullable = false)
    private Integer sharePatientId;

    //患者ID
    //必須、カラム定義
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    //共有元病院ID
    //必須、カラム定義
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shared_by_hospital_id", nullable = false)
    private HospitalManagement sharedByHospital;

    //共有先病院ID
    //必須、カラム定義
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "share_with_hospital_id", nullable = false)
    private HospitalManagement shareWithHospital;

    //紹介状ID
    //必須、カラム定義
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "referral_id", nullable = false)
    private Referral referral;

    //共有開始日
    //必須、カラム定義
    @Column(name = "shared_start_at", nullable = false)
    private LocalDate sharedStartAt;

    //共有終了日
    //必須、カラム定義
    @Column(name = "shared_end_at")
    private LocalDate sharedEndAt;

    //削除フラグ
    //必須、カラム定義
    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    //getter/setter
	public Integer getSharePatientId() {
		return sharePatientId;
	}

	public void setSharePatientId(Integer sharePatientId) {
		this.sharePatientId = sharePatientId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public HospitalManagement getSharedByHospital() {
		return sharedByHospital;
	}

	public void setSharedByHospital(HospitalManagement sharedByHospital) {
		this.sharedByHospital = sharedByHospital;
	}

	public HospitalManagement getShareWithHospital() {
		return shareWithHospital;
	}

	public void setShareWithHospital(HospitalManagement shareWithHospital) {
		this.shareWithHospital = shareWithHospital;
	}

	public Referral getReferral() {
		return referral;
	}

	public void setReferral(Referral referral) {
		this.referral = referral;
	}

	public LocalDate getSharedStartAt() {
		return sharedStartAt;
	}

	public void setSharedStartAt(LocalDate sharedStartAt) {
		this.sharedStartAt = sharedStartAt;
	}

	public LocalDate getSharedEndAt() {
		return sharedEndAt;
	}

	public void setSharedEndAt(LocalDate sharedEndAt) {
		this.sharedEndAt = sharedEndAt;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
    
}
