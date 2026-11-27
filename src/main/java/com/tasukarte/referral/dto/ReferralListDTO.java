
package com.tasukarte.referral.dto;

import java.time.LocalDateTime;

// 紹介状一覧画面に表示する情報を保持するDTO
public class ReferralListDTO {

    // 紹介状ID
    private Integer referralId;

    // 患者名
    private String patientName;

    // 宛先病院名
    private String recipientHospitalName;

    // 初回登録日時
    private LocalDateTime createdAt;

    // 紹介状の状態
    private String status;

    // 編集可能か
    private boolean canEdit;

    // 削除可能か
    private boolean canDelete;

    // 送信可能か
    private boolean canSend;

    // 承認可能か
    private boolean canApprove;

    // 拒否可能か
    private boolean canReject;


    // コンストラクタ
    public ReferralListDTO(
            Integer referralId,
            String patientName,
            String recipientHospitalName,
            LocalDateTime createdAt,
            String status,
            boolean canEdit,
            boolean canDelete,
            boolean canSend,
            boolean canApprove,
            boolean canReject) {

        this.referralId = referralId;
        this.patientName = patientName;
        this.recipientHospitalName = recipientHospitalName;
        this.createdAt = createdAt;
        this.status = status;
        this.canEdit = canEdit;
        this.canDelete = canDelete;
        this.canSend = canSend;
        this.canApprove = canApprove;
        this.canReject = canReject;
    }


    public Integer getReferralId() {
        return referralId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getRecipientHospitalName() {
        return recipientHospitalName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public boolean isCanSend() {
        return canSend;
    }

    public boolean isCanApprove() {
        return canApprove;
    }

    public boolean isCanReject() {
        return canReject;
    }
}
