package com.tasukarte.referral.dto;

import java.time.LocalDate;

public class ReferralCreateDTO {

	//紹介状ID(下書き等変更用)
	private Integer referralId;
    //患者ID
    private Integer patientId;

    //宛先病院ID
    private Integer recipientHospitalId;

    //紹介目的
    private String referralPurpose;

    //依頼内容
    private String requestContent;

    //備考
    private String remarks;

    //共有開始日
    private LocalDate sharedStartAt;

    //共有終了日
    private LocalDate sharedEndAt;

    //getter / setter
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

    public Integer getRecipientHospitalId() {
        return recipientHospitalId;
    }

    public void setRecipientHospitalId(Integer recipientHospitalId) {
        this.recipientHospitalId = recipientHospitalId;
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
}