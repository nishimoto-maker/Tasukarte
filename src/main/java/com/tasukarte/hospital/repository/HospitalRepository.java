package com.tasukarte.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.hospital.entity.Hospital;

// hospitalsテーブルを操作するためのインターフェース
public interface HospitalRepository
        extends JpaRepository<Hospital, Integer> {

}