package com.tasukarte.staff.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="staff_passwords")
public class StaffPassword {
	// 主キー（外部キーも兼ねる）	
	@Id
	@Column(name = "staff_id")
	private Integer staffId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "staff_id", nullable = false)
	private Staff staff;
	
	@NotBlank
	@Size(max = 255)
	@Column(name="password_hash", length = 255, nullable = false)
	//パスワードハッシュ
	private String passwordHash;
	
	public StaffPassword() {
	}
	
	public Integer getStaffId() {
        return staffId;
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