package com.tasukarte.hospital.dto;

//病院検索結果をやり取りするDTO
public class HospitalSearchDTO {
	
	private Integer hospitalId;
	
	private String hospitalName;
	
	private String hospitalDirectorName;
	
	public HospitalSearchDTO(
			Integer hospitalId,
			String hospitalName,
			String hospitalDirectorName) {
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.hospitalDirectorName = hospitalDirectorName;
	}
	
	public Integer getHospitalId() {
		return hospitalId;
	}
	
	public String getHospitalName() {
		return hospitalName;
	}
	
	public String getHospitalDirectorName() {
		return hospitalDirectorName;
	}
}
