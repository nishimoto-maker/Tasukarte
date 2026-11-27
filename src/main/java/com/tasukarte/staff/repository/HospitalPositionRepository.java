package com.tasukarte.staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.staff.entity.HospitalPosition;

// 役職情報を操作するRepository
public interface HospitalPositionRepository
        extends JpaRepository<HospitalPosition, Integer> {

}