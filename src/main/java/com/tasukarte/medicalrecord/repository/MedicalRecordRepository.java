package com.tasukarte.medicalrecord.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.medicalrecord.entity.MedicalRecord;
public interface MedicalRecordRepository
	extends JpaRepository<MedicalRecord, Integer> {
	// 指定した患者に関連する診療記録を診療記録日の降順で取得
	List<MedicalRecord> findByPatientIdOrderByMedicalRecordDateDesc(
			Integer recordId
	);
}