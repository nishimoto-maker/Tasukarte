package com.tasukarte.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasukarte.admin.entity.SystemAdmin;

// システム管理者リポジトリ
@Repository
public interface SystemAdminRepository
        extends JpaRepository<SystemAdmin, Integer> {

    // メールアドレスによるシステム管理者の取得
    Optional<SystemAdmin> findByAdminMail(String adminMail);

    // メールアドレスの重複確認
    boolean existsByAdminMail(String adminMail);

}