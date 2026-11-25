package com.tasukarte.medicalrecord.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.medicalrecord.entity.MedicalRecord;
public interface MedicalRecordRepository
	extends JpaRepository<MedicalRecord, Integer> {
	List<MedicalRecord> findByPatientIdAndMedicalRecordIdNot(
			Integer patientId,
			Integer recordId
	);
}