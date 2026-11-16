package com.tasukarte.medicalrecord.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tasukarte.medicalrecord.repository.MedicalRecordRepository;
import com.tasukarte.patient.entity.Patient;

@Service
public class MedicalRecordService {
	// 必要なRepositoryを持つためのフィールドを定義
	private MedicalRecordRepository medicalRecordRepository;
	private PatientRepository patientRepository;
	
	// コンストラクタで注入
	public MedicalRecordService (
			MedicalRecordRepository medicalRecordRepository
			PatientRepository patientRepository
			) {
		this.medicalRecordRepository = medicalRecordRepository;
		this.patientRepository = patientRepository;
	}
	
	// 診療記録を登録する患者の検索
	// PatientRepositoryに「List<Patient> findByNameContaining(String name)」を追加してもらう
	public List<Patient> searchPatients (String name) {
		return patientRepository.findByNameContaiging(name);
	}
}