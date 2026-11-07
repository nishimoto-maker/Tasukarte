package com.tasukarte.staff.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "staffs")
public class Staff {
	//idを主キーにする。
	@Id
	//idを自動生成             //PostgreSQLのIDENTITYによる自動採番を利用
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id")
	//職員ID
	private Integer staffId;
	
	//病院ID
	@Column(name = "hospital_id", nullable = false)
	private Integer hospitalId;
	
	//役職ID
	@Column(name = "position_id", nullable = false)
	private Integer positionId;
	
	//権限ID
	@Column(name = "role_id", nullable = false)
	private Integer roleId;
	
	//名前(漢字)
	@Column(name = "name_kanji", length = 50, nullable = false)
    private String nameKanji;
	
	//名前(カナ)
	@Column(name = "name_kana", length = 50, nullable = false)
    private String nameKana;
	
	//性別
	@Column(name = "gender", nullable = false)
    private short gender;
	
	//住所
	@Column(name = "address", length = 255, nullable = false)
    private String address;
	
	//郵便番号
	@Column(name = "post_code", length = 7, nullable = false)
    private String postCode;
	
	//電話番号
	@Column(name = "phone_number", length = 20, nullable = false)
    private String phoneNumber;
	
	//メールアドレス
	@Column(name = "email_address", length = 254, nullable = false)
    private String emailAddress;
	
	// 削除フラグ
	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;
	
	public Staff() {
	}
	
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	
	public Integer getHospitalId() {
	    return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
	    this.hospitalId = hospitalId;
	}
	
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
