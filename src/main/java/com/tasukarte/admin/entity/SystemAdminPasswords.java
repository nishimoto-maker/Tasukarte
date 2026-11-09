package com.tasukarte.admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
//import lombok.Setter;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
//import lombok.Getter;
import lombok.NoArgsConstructor;

// エンティティの宣言
@Entity
// 参照テーブル物理名称
@Table(name = "system_admin_passwords")
// Getter自動生成
//@Getter
// Setter自動生成
//@Setter
// 引数なしのコンストラクタを自動生成
@NoArgsConstructor
public class SystemAdminPasswords {
    
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name ="admin_id")
    private SystemAdmins systemadmin;
    
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

	public SystemAdmins getSystemadmin() {
		return systemadmin;
	}

	public void setSystemadmin(SystemAdmins systemadmin) {
		this.systemadmin = systemadmin;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

    
}
