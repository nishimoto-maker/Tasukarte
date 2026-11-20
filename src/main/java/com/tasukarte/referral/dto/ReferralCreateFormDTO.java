package com.tasukarte.referral.dto;

//紹介状作成画面からのリクエストを格納するDTO
public class ReferralCreateFormDTO {

    // 患者ID
    private Integer patientId;

    // 宛先病院ID
    private Integer recipientHospitalId;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getRecipientHospitalId() {
        return recipientHospitalId;
    }

    public void setRecipientHospitalId(Integer recipientHospitalId) {
        this.recipientHospitalId = recipientHospitalId;
    }
}