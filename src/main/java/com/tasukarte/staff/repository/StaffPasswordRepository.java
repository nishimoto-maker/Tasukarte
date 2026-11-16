package com.tasukarte.staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.staff.entity.StaffPassword;

public interface StaffPasswordRepository
		extends JpaRepository<StaffPassword, Integer> {

}
