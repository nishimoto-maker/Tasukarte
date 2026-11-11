
package com.tasukarte.patient.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.tasukarte.hospital.entity.HospitalManagement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "patients")
public class Patient {
	
	//患者ID
	//主キー、自動連番、カラム定義
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer patientId;
    
    // アレルギー情報
    // 1人の患者に複数のアレルギー情報が紐づく
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Allergie> allergies;
    
    //病院ID
    //必須、カラム定義
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalManagement hospital;

    //氏名
    //必須、255文字まで、カラム定義
    @NotBlank
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    //フリガナ
    //必須、255文字まで、カラム定義
    @NotBlank
    @Column(name = "kata", nullable = false, length = 255)
    private String kata;

    //生年月日
    //必須、カラム定義
    @NotNull
    @Column(name = "birth_day", nullable = false)
    private LocalDate birthDay;

    //性別
    //必須、カラム定義
    @NotNull
    @Column(name = "gender", nullable = false)
    private Short gender;

    //電話番号
    //必須、20文字まで、カラム定義
    @NotBlank
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    //メールアドレス
    //255文字まで、カラム定義
    @Column(name = "email_address", length = 255)
    private String emailAddress;

    //住所
    //必須、255文字まで、カラム定義
    @NotBlank
    @Column(name = "address", nullable = false, length = 255)
    private String address;

    //郵便番号
    //必須、7文字まで、カラム定義
    @NotBlank
    @Column(name = "post_code", nullable = false, length = 7)
    private String postCode;

    //血液型
    //必須、2文字まで、カラム定義
    @NotBlank
    @Column(name = "blood_type", nullable = false, length = 2)
    private String bloodType;

    //RH
    //必須、カラム定義
    @NotNull
    @Column(name = "rh", nullable = false)
    private Boolean rh;

    //アレルギー有無
    //必須、カラム定義
    @NotNull
    @Column(name = "allergy", nullable = false)
    private Boolean allergy;

    //体重
    //必須、合計5桁まで小数点2桁まで、カラム定義
    @NotNull
    @Column(name = "weight", nullable = false, precision = 5, scale = 2)
    private BigDecimal weight;

    //身長
    //必須、合計5桁まで小数点2桁まで、カラム定義
    @NotNull
    @Column(name = "height",  nullable = false,precision = 5, scale = 2)
    private BigDecimal height;

    //登録日
    //必須、カラム定義
    @NotNull
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    //更新日
    //カラム定義
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    //setter/getter
	public Integer getPatientId() {
		return patientId;
	}
	
	public List<Allergie> getAllergies() {
	    return allergies;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	
	public void setAllergies(List<Allergie> allergies) {
	    this.allergies = allergies;
	}

	public HospitalManagement getHospital() {
		return hospital;
	}

	public void setHospital(HospitalManagement hospital) {
		this.hospital = hospital;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKata() {
		return kata;
	}

	public void setKata(String kata) {
		this.kata = kata;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public Short getGender() {
		return gender;
	}

	public void setGender(Short gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public Boolean getRh() {
		return rh;
	}

	public void setRh(Boolean rh) {
		this.rh = rh;
	}

	public Boolean getAllergy() {
		return allergy;
	}

	public void setAllergy(Boolean allergy) {
		this.allergy = allergy;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}
	
}
