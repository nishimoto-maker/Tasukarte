package com.tasukarte.staff.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="staff_passwords")
public class StaffPassword {
	// 主キー（外部キーも兼ねる）
	@Id
	@Column(name="staff_id", nullable = false)
	//職員ID
	private Integer staffId;
	
	@Column(name="password_hash", length = 255, nullable = false)
	//パスワードハッシュ
	private String passwordHash;
	
	public StaffPassword() {
	}
	
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
}