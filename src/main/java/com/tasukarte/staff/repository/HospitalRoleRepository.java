package com.tasukarte.staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.staff.entity.HospitalRole;

// 権限情報を操作するRepository
public interface HospitalRoleRepository
        extends JpaRepository<HospitalRole, Integer> {

}