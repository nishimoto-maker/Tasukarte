package com.tasukarte.staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.staff.entity.Staff;

//staffsテーブルの操作を行うinterface
public interface StaffRepository 
		extends JpaRepository<Staff, Integer>{
	
}
