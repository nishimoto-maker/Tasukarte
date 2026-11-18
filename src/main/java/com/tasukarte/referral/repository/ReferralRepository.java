package com.tasukarte.referral.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.referral.entity.Referral;

//紹介状リポジトリ
public interface ReferralRepository
        extends JpaRepository<Referral, Integer> {

}