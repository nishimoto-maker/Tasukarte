package com.tasukarte.hospital.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "hospital_managements")
public class HospitalManagement {

    // 病院ID（主キー）
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id", nullable = false)
    private Integer hospitalId;

    // 病院名
    @NotBlank
    @Column(name = "hospital_name", nullable = false)
    private String hospitalName;

    // 住所
    @NotBlank
    @Column(name = "address", nullable = false)
    private String address;

    // 郵便番号
    @NotBlank
    @Column(name = "post_code", nullable = false)
    private String postCode;

	// 電話番号
    @NotBlank
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    // 院長名
    @NotBlank
    @Column(name = "hospital_director_name", nullable = false)
    private String hospitalDirectorName;

    // 設立日
    @NotNull
    @Column(name = "established_date", nullable = false)
    private LocalDate establishedDate;

    // メールアドレス
    @NotBlank
    @Column(name = "hospital_mail", nullable = false)
    private String hospitalMail;

    // 削除フラグ
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;
    
    public HospitalManagement() {
    }
    
    public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHospitalDirectorName() {
		return hospitalDirectorName;
	}

	public void setHospitalDirectorName(String hospitalDirectorName) {
		this.hospitalDirectorName = hospitalDirectorName;
	}

	public LocalDate getEstablishedDate() {
		return establishedDate;
	}

	public void setEstablishedDate(LocalDate establishedDate) {
		this.establishedDate = establishedDate;
	}

	public String getHospitalMail() {
		return hospitalMail;
	}

	public void setHospitalMail(String hospitalMail) {
		this.hospitalMail = hospitalMail;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}