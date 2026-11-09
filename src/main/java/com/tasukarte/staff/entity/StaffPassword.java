package com.tasukarte.staff.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
//import lombok.Setter;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
//import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="staff_passwords")
@NoArgsConstructor
public class StaffPassword {
	// 主キー（外部キーも兼ねる）
	@Id
	@Column(name="staff_id", nullable = false)
	//職員ID
	private Integer staffId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "staff_id")
	private Staff staff;
	
	@Column(name="password_hash", length = 255, nullable = false)
	//パスワードハッシュ
	private String passwordHash;

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	
}