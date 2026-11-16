package com.tasukarte.medicalrecord.service;

import org.springframework.stereotype.Service;

import com.tasukarte.medicalrecord.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {
	// 必要なRepositoryを持つためのフィールドを定義
	private MedicalRecordRepository medicalRecordRepository;
	
	// コンストラクタで注入
	public MedicalRecordService (
			MedicalRecordRepository medicalRecordRepository
			) {
		this.medicalRecordRepository = medicalRecordRepository;
		}
}