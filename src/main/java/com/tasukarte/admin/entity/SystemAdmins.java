package com.tasukarte.admin.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

// エンティティの宣言
@Entity
// 参照テーブル物理名称
@Table(name = "system_admin")
// Getter自動生成
//@Getter
// Setter自動生成
//@Setter
// 引数なしのコンストラクタを自動生成
@NoArgsConstructor
public class SystemAdmins {

    public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminNameKana() {
		return adminNameKana;
	}

	public void setAdminNameKana(String adminNameKana) {
		this.adminNameKana = adminNameKana;
	}

	public String getAdminMail() {
		return adminMail;
	}

	public void setAdminMail(String adminMail) {
		this.adminMail = adminMail;
	}

	public SystemAdminPasswords getSystemAdminPasswords() {
		return systemAdminPasswords;
	}

	public void setSystemAdminPasswords(SystemAdminPasswords systemAdminPasswords) {
		this.systemAdminPasswords = systemAdminPasswords;
	}

	// 管理者ID(主キー)
    @Id
    // 主キーをデータベース側で自動採番するための設定
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // カラム名
    @Column(name = "admin_id")
    private Integer adminId;
    
    @Column(name = "admin_name", length = 255, nullable = false)
	//名前(漢字)
    private String adminName;
    
    @Column(name = "admin_name_kana", length = 255, nullable = false)
	//名前(カナ)
    private String adminNameKana;

    @Column(name = "admin_mail", length = 255, nullable = false)
	//メールアドレス
    private String adminMail;
    
    @OneToOne(
    	// 主キーとして管理する側を指定
    	mappedBy = "system_admin",
    	// システム管理者に対する永続化、更新、削除等の操作を外部キー側にも連動させる
    	cascade = CascadeType.ALL,
    	// システム管理者エンティティからパスワードとの関連を解除した場合、パスワードエンティティを削除する
    	orphanRemoval = true
    		)
    private SystemAdminPasswords systemAdminPasswords;
}
