
package com.tasukarte.referral.entity;

import java.time.LocalDateTime;

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
@Table(name = "referrals")
// Getter自動生成
@Getter
// Setter自動生成
@Setter
// 引数なしのコンストラクタを自動生成
@NoArgsConstructor
public class Referrals {

    // (主キー)
    @Id
    // 主キーをデータベース側で自動採番するための設定
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // カラム名
    @Column(name = "referral_id")
    private Integer referralId;

    // 患者ID
    // nullableは非null制約の物に付ける
    // 外部キーの物には
    // @ManyToOne
    // @JoinColumn
    // private Patient patientId;
    // のように記述
    // Integer型のメリット：自動採番前はnullであるため、null利用が可能であること
    // デメリット：nullを許容するため、nullエラー時のハンドリングが必要
    @Column(name ="patient_id", nullable = false)
    private Integer patientId;

    // 送信元病院ID
    @Column(name = "sender_hospital_id", nullable = false)
    private Integer senderHospitalId;

    // 送信先病院ID
    @Column(name = "recipient_hospital_id", nullable = false)
    private Integer recipientHospitalId;

    // 職員ID
    @Column(name = "staff_id", nullable = false)
    private Integer staffId;

    // 紹介目的
    @Column(name = "referral_purpose", nullable = false, columnDefinition = "TEXT")
    private String referralPurpose;

    // 依頼内容
    @Column(name = "request_content", nullable = false, columnDefinition = "TEXT")
    private String requestContent;

    // 備考
    @Column(name = "remarks", nullable = false, columnDefinition = "TEXT")
    private String remarks;

    // 作成日
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // 申請日
    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    // 承認日
    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    // 状態
    @Column(name = "status", length = 20, nullable = false)
    private String status;

    // 削除フラグ
    @Column(name = "is_deleted")
    private Boolean deleted;

    // 共有開始日
    @Column(name = "shared_start_at")
    private LocalDateTime sharedStartAt;

    // 共有終了日
    @Column(name = "shared_end_at")
    private LocalDateTime sharedEndAt;
}
