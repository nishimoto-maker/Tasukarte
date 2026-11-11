package com.tasukarte.referral.entity;

import java.time.LocalDateTime;

import com.tasukarte.hospital.entity.HospitalManagement;
import com.tasukarte.patient.entity.Patient;
import com.tasukarte.staff.entity.Staff;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
//import lombok.Getter;
import lombok.NoArgsConstructor;
//import lombok.Setter;

// エンティティの宣言
@Entity
// 参照テーブル物理名称
@Table(name = "referrals")
// Getter自動生成
//@Getter
// Setter自動生成
//@Setter
// 引数なしのコンストラクタを自動生成
@NoArgsConstructor
public class Referral {

    // (主キー)
    @Id
    // DBの自動採番に対応
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // カラム名
    @Column(name = "referral_id")
    private Integer referralId;

    // 患者ID(必須)
    // nullableは非null制約の物に付ける
    // 外部キーの物には
    // @ManyToOne
    // @JoinColumn
    // private Patient patientId;
    // のように記述
    // Integer型のメリット：自動採番前はnullであるため、null利用が可能であること
    // デメリット：nullを許容するため、nullエラー時のハンドリングが必要
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name ="patient_id", nullable = false)
    private Patient patient;

    // 送信元病院ID（必須）
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Column(name = "sender_hospital_id", nullable = false)
    private HospitalManagement senderHospital;

    // 送信先病院ID（必須）
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipient_hospital_id", nullable = false)
    private HospitalManagement recipientHospital;

    // 作成者職員ID（必須）
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    // 紹介目的（一時保存時Null許容、本登録時必須）
    @Column(name = "referral_purpose",
            columnDefinition = "TEXT")
    private String referralPurpose;

    // 依頼内容（一時保存時Null許容、本登録時必須）
    @Column(name = "request_content",
            columnDefinition = "TEXT")
    private String requestContent;

    // 備考（Null許容）
    @Column(name = "remarks",
            columnDefinition = "TEXT")
    private String remarks;

    // 作成日（必須、内容変更時更新）
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // 申請日（Null許容）
    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    // 承認日(Null許容)
    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    // 状態(Null許容)
    @NotBlank
    @Column(name = "status", length = 20, nullable = false)
    private String status;

    // 削除フラグ
    @Column(name = "is_deleted")
    private boolean deleted;

    // 共有開始日（一時保存時Null許容、本登録時必須）
    @Column(name = "shared_start_at")
    private LocalDateTime sharedStartAt;

    // 共有終了日（一時保存時Null許容、本登録時必須）
    @Column(name = "shared_end_at")
    private LocalDateTime sharedEndAt;

	public Integer getReferralId() {
		return referralId;
	}

	public void setReferralId(Integer referralId) {
		this.referralId = referralId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public HospitalManagement getSenderHospital() {
		return senderHospital;
	}

	public void setSenderHospital(HospitalManagement senderHospital) {
		this.senderHospital = senderHospital;
	}

	public HospitalManagement getRecipientHospital() {
		return recipientHospital;
	}

	public void setRecipientHospital(HospitalManagement recipientHospital) {
		this.recipientHospital = recipientHospital;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getReferralPurpose() {
		return referralPurpose;
	}

	public void setReferralPurpose(String referralPurpose) {
		this.referralPurpose = referralPurpose;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(LocalDateTime requestedAt) {
		this.requestedAt = requestedAt;
	}

	public LocalDateTime getApprovedAt() {
		return approvedAt;
	}

	public void setApprovedAt(LocalDateTime approvedAt) {
		this.approvedAt = approvedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public LocalDateTime getSharedStartAt() {
		return sharedStartAt;
	}

	public void setSharedStartAt(LocalDateTime sharedStartAt) {
		this.sharedStartAt = sharedStartAt;
	}

	public LocalDateTime getSharedEndAt() {
		return sharedEndAt;
	}

	public void setSharedEndAt(LocalDateTime sharedEndAt) {
		this.sharedEndAt = sharedEndAt;
	}

	
}
