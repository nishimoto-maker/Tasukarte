package com.tasukarte.referral.dto;


//紹介状入力画面に表示する情報を保持するDTO
public class ReferralInputInfoDTO {

	//患者名
	private String patientName;
	
	//宛先病院
	private String recipientHospitalName;
	
	public ReferralInputInfoDTO(
			String patientName,
			String recipientHospitalName) {
		
		this.patientName = patientName;
		this.recipientHospitalName = recipientHospitalName;
	}
	
    //患者名取得
    public String getPatientName() {
        return patientName;
    }

    //宛先病院名取得
    public String getRecipientHospitalName() {
        return recipientHospitalName;
    }

}
