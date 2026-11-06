
package com.tasukarte.referral.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class Referrals {

    // (主キー)
    @Id
    // DBの自動採番に対応
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // カラム名
    @Column(name = "referral_id")
    private Integer referralId;

    // 患者ID
    // nullableは非null制約の物に付ける
    // 外部キーの物には
    // @ManyToOne
    // @JoinColumn
    // private Patient patientId;
    // のように記述
    // Integer型のメリット：自動採番前はnullであるため、null利用が可能であること
    // デメリット：nullを許容するため、nullエラー時のハンドリングが必要
    @Column(name ="patient_id", nullable = false)
    private Integer patientId;

    // 送信元病院ID
    @Column(name = "sender_hospital_id", nullable = false)
    private Integer senderHospitalId;

    // 送信先病院ID
    @Column(name = "recipient_hospital_id", nullable = false)
    private Integer recipientHospitalId;

    // 職員ID
    @Column(name = "staff_id", nullable = false)
    private Integer staffId;

    // 紹介目的
    @Column(name = "referral_purpose", nullable = false, columnDefinition = "TEXT")
    private String referralPurpose;

    // 依頼内容
    @Column(name = "request_content", nullable = false, columnDefinition = "TEXT")
    private String requestContent;

    // 備考
    @Column(name = "remarks", nullable = false, columnDefinition = "TEXT")
    private String remarks;

    // 作成日
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // 申請日
    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    // 承認日
    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    // 状態
    @Column(name = "status", length = 20, nullable = false)
    private String status;

    // 削除フラグ
    @Column(name = "is_deleted")
    private Boolean deleted;

    // 共有開始日
    @Column(name = "shared_start_at")
    private LocalDateTime sharedStartAt;

    // 共有終了日
    @Column(name = "shared_end_at")
    private LocalDateTime sharedEndAt;

	public Integer getReferralId() {
		return referralId;
	}

	public void setReferralId(Integer referralId) {
		this.referralId = referralId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getSenderHospitalId() {
		return senderHospitalId;
	}

	public void setSenderHospitalId(Integer senderHospitalId) {
		this.senderHospitalId = senderHospitalId;
	}

	public Integer getRecipientHospitalId() {
		return recipientHospitalId;
	}

	public void setRecipientHospitalId(Integer recipientHospitalId) {
		this.recipientHospitalId = recipientHospitalId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
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
