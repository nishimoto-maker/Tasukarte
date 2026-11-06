package com.tasukarte.admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// エンティティの宣言
@Entity
// 参照テーブル物理名称
@Table(name = "system_admin")
// Getter自動生成
@Getter
// Setter自動生成
@Setter
// 引数なしのコンストラクタを自動生成
@NoArgsConstructor
public class SystemAdmins {

    // (主キー)
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
}
