package com.tasukarte.medicalrecord.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.medicalrecord.entity.MedicalRecord;
public interface MedicalRecordRepository
	extends JpaRepository<MedicalRecord, Integer> {

}