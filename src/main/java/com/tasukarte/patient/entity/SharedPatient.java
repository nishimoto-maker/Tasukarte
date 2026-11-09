
package com.tasukarte.patient.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


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
    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    //共有元病院ID
    //必須、カラム定義
    @Column(name = "shared_by_hospital_id", nullable = false)
    private Integer sharedByHospitalId;

    //共有先病院ID
    //必須、カラム定義
    @Column(name = "share_with_hospital_id", nullable = false)
    private Integer shareWithHospitalId;

    //紹介状ID
    //必須、カラム定義
    @Column(name = "referral_id", nullable = false)
    private Integer referralId;

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
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;
    
    
    //getter/setter
	public Integer getSharePatientId() {
		return sharePatientId;
	}

	public void setSharePatientId(Integer sharePatientId) {
		this.sharePatientId = sharePatientId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getSharedByHospitalId() {
		return sharedByHospitalId;
	}

	public void setSharedByHospitalId(Integer sharedByHospitalId) {
		this.sharedByHospitalId = sharedByHospitalId;
	}

	public Integer getShareWithHospitalId() {
		return shareWithHospitalId;
	}

	public void setShareWithHospitalId(Integer shareWithHospitalId) {
		this.shareWithHospitalId = shareWithHospitalId;
	}

	public Integer getReferralId() {
		return referralId;
	}

	public void setReferralId(Integer referralId) {
		this.referralId = referralId;
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

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
