package com.tasukarte.referral.dto;

import java.time.LocalDate;

//紹介状編集画面に表示する情報を保持するDTO
public class ReferralEditDTO {

    //紹介状ID
    private Integer referralId;

    //患者情報
    private Integer patientId;
    private String patientName;

    //宛先病院情報
    private Integer recipientHospitalId;
    private String recipientHospitalName;

    //紹介状の入力内容
    private String referralPurpose;
    private String requestContent;
    private String remarks;

    //共有期間
    private LocalDate sharedStartAt;
    private LocalDate sharedEndAt;

    public ReferralEditDTO(
            Integer referralId,
            Integer patientId,
            String patientName,
            Integer recipientHospitalId,
            String recipientHospitalName,
            String referralPurpose,
            String requestContent,
            String remarks,
            LocalDate sharedStartAt,
            LocalDate sharedEndAt) {

        this.referralId = referralId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.recipientHospitalId = recipientHospitalId;
        this.recipientHospitalName = recipientHospitalName;
        this.referralPurpose = referralPurpose;
        this.requestContent = requestContent;
        this.remarks = remarks;
        this.sharedStartAt = sharedStartAt;
        this.sharedEndAt = sharedEndAt;
    }

    public Integer getReferralId() {
        return referralId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public Integer getRecipientHospitalId() {
        return recipientHospitalId;
    }

    public String getRecipientHospitalName() {
        return recipientHospitalName;
    }

    public String getReferralPurpose() {
        return referralPurpose;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public String getRemarks() {
        return remarks;
    }

    public LocalDate getSharedStartAt() {
        return sharedStartAt;
    }

    public LocalDate getSharedEndAt() {
        return sharedEndAt;
    }
}