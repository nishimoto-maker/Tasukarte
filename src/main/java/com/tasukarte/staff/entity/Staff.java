package com.tasukarte.staff.entity;


import com.tasukarte.hospital.entity.HospitalManagement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
//import lombok.Getter;
import lombok.NoArgsConstructor;
//import lombok.Setter;

@Entity
@Table(name = "staffs")
//Getter自動生成
//@Getter
//Setter自動生成
//@Setter
//引数なしのコンストラクタを自動生成
@NoArgsConstructor
public class Staff {
	
	//idを主キーにする。
	@Id
	//idを自動生成             //PostgreSQLのIDENTITYによる自動採番を利用
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id")
	//職員ID
	private Integer staffId;
	
	// 病院情報管理
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalManagement hospital;

    // 院内役職
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private HospitalPosition hospitalPosition;

    // 院内権限
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private HospitalRole hospitalRole;
    
    // パスワード情報
    @OneToOne(mappedBy = "staff", fetch = FetchType.LAZY)
    private StaffPassword staffPassword;

    // 名前（漢字）
    @NotBlank
    @Size(max = 50)
    @Column(name = "name_kanji", length = 50, nullable = false)
    private String nameKanji;

    // 名前（カナ）
    @NotBlank
    @Size(max = 50)
    @Column(name = "name_kana", length = 50, nullable = false)
    private String nameKana;

    // 性別
    @Column(name = "gender", nullable = false)
    private short gender;

    // 住所
    @NotBlank
    @Size(max = 255)
    @Column(name = "address", length = 255, nullable = false)
    private String address;

    // 郵便番号
    @NotBlank
    @Size(max = 7)
    @Column(name = "post_code", length = 7, nullable = false)
    private String postCode;

    // 電話番号
    @NotBlank
    @Size(max = 20)
    @Column(name = "phone_number", length = 20, nullable = false)
    private String phoneNumber;

    // メールアドレス
    @NotBlank
    @Size(max = 254)
    @Column(name = "email_address", length = 254, nullable = false)
    private String emailAddress;

    // 削除フラグ
    @Column(name = "is_deleted")
    private boolean isDeleted;

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public HospitalManagement getHospital() {
		return hospital;
	}


	public void setHospitalManagement(HospitalManagement hospital) {
		this.hospital = hospital;
	}


	public HospitalPosition getHospitalPosition() {
		return hospitalPosition;
	}


	public void setHospitalPosition(HospitalPosition hospitalPosition) {
		this.hospitalPosition = hospitalPosition;
	}


	public HospitalRole getHospitalRole() {
		return hospitalRole;
	}


	public void setHospitalRole(HospitalRole hospitalRole) {
		this.hospitalRole = hospitalRole;
	}

	public StaffPassword getStaffPassword() {
		return staffPassword;
	}

	public void setStaffPassword(StaffPassword staffPassword) {
		this.staffPassword = staffPassword;
	}

	public String getNameKanji() {
		return nameKanji;
	}

	public void setNameKanji(String nameKanji) {
		this.nameKanji = nameKanji;
	}

	public String getNameKana() {
		return nameKana;
	}

	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}

	public short getGender() {
		return gender;
	}

	public void setGender(short gender) {
		this.gender = gender;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
