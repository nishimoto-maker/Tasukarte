package com.tasukarte.hospital.entity;

import java.time.LocalDate;

import com.tasukarte.staff.entity.Staff;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hospital_managements")
public class HospitalManagement {

    // 病院ID（主キー）
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id", nullable = false)
    private Integer hospitalId;

    // 病院名
    @Column(name = "hospital_name", nullable = false)
    private String hospitalName;

    // 住所
    @Column(name = "address", nullable = false)
    private String address;

    // 郵便番号
    @Column(name = "post_code", nullable = false)
    private String postCode;

    // 電話番号
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    // 院長名
    @Column(name = "hospital_director_name", nullable = false)
    private String hospitalDirectorName;

    // 院長ID（Staffとの外部キー関連）
    @ManyToOne
    @JoinColumn(name = "hospital_director_id", nullable = false)
    private Staff hospitalDirector;

    // 設立日
    @Column(name = "established_date", nullable = false)
    private LocalDate establishedDate;

    // メールアドレス
    @Column(name = "hospital_mail", nullable = false)
    private String hospitalMail;

    // 削除フラグ
    @Column(name = "is_deleted")
    private Boolean isDeleted;
}