package com.tasukarte.medicalrecord.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.medicalrecord.dto.MedicalRecordRegisterDto;
import com.tasukarte.medicalrecord.entity.MedicalRecord;
import com.tasukarte.medicalrecord.repository.MedicalRecordRepository;
import com.tasukarte.patient.service.PatientService;
import com.tasukarte.staff.service.StaffService;

@Service
public class MedicalRecordService {
	// 必要なServiceを持つためのフィールドを定義
	private PatientService patientService;
	private StaffService staffService;
	// 必要なRepositoryを持つためのフィールドを定義
	private MedicalRecordRepository medicalRecordRepository;
	
	// コンストラクタで注入
	public MedicalRecordService (
			PatientService patientService,
			StaffService staffService,
			MedicalRecordRepository medicalRecordRepository
			) {
		this.patientService = patientService;
		this.staffService = staffService;
		this.medicalRecordRepository = medicalRecordRepository;
		}
	
	// 診療記録の登録
	public void registerMedicalRecord(
			LoginUser loginUser,
			// ↓省略してコーディングするので、終わったら置換してちょ
			MedicalRecordRegisterDto mRRD,
			Integer patientId
			) {
		// 開発中毎回medicalRecordって打つのめんどいから略でコーディングします
		// 終わったら置換してください
		MedicalRecord mr = new MedicalRecord();
		
		mr.setPatient(patientService.findPatientById(patientId));
		
		mr.setStaff(staffService.findStaffById(loginUser.getStaffId()));
		
		mr.setDiseaseName(mRRD.getDiseaseName());
		
		mr.setOutcome(mRRD.getOutcome());
		
		mr.setOutcomeDate(mRRD.getOutcomeDate());
		
		mr.setProgressS(mRRD.getProgressS());
		mr.setProgressO(mRRD.getProgressO());
		mr.setProgressA(mRRD.getProgressA());
		mr.setProgressP(mRRD.getProgressP());
		
		mr.setProcedure(mRRD.getProcedure());
		
		mr.setFollowUpDate(mRRD.getFollowUpDate());
		
		mr.setHasPrescription(mRRD.isHasPrescription());
		
		medicalRecordRepository.save(mr);
	}
	
	// IDで診療記録を1件取得
	public MedicalRecord findMedicalRecord(Integer medicalRecordId) {
		return medicalRecordRepository.findById(medicalRecordId).orElse(null);
	}
	
	// ↑で取得した記録以外の記録を取得
	public List<MedicalRecord> findOtherRecords(
			Integer patientId,
			Integer medicalRecordId
			) {
		return medicalRecordRepository
					.findByPatientIdAndMedicalRecordIdNot(
							patientId, medicalRecordId
						);
	}
}