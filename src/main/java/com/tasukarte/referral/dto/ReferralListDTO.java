package com.tasukarte.referral.dto;

import java.time.LocalDateTime;

//紹介状一覧画面に表示する情報を保持するDTO
public class ReferralListDTO {

    //紹介状ID
    private Integer referralId;

    //患者名
    private String patientName;

    //宛先病院名
    private String recipientHospitalName;

    //初回登録日時
    private LocalDateTime createdAt;

    //紹介状の状態
    private String status;

    //コンストラクタ
    public ReferralListDTO(
            Integer referralId,
            String patientName,
            String recipientHospitalName,
            LocalDateTime createdAt,
            String status) {

        this.referralId = referralId;
        this.patientName = patientName;
        this.recipientHospitalName = recipientHospitalName;
        this.createdAt = createdAt;
        this.status = status;
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
}
