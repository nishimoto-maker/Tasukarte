package com.tasukarte.medicalrecord.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.medicalrecord.dto.MedicalRecordRegisterDto;
import com.tasukarte.medicalrecord.dto.MedicalRecordSearchDto;
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
	
	// 指定された条件で検索
	// Specification作って条件組み立てる
	// Sortで記録日の降順にするのと、
	// ログインユーザーが所属する病院と、
	// 記録の担当医師が所属する病院が一致するデータのみ取得する
	public List<MedicalRecord> searchMedicalRecords(MedicalRecordSearchDto searchDto) {
		return new ArrayList<MedicalRecord>();
	}
	
	// IDで診療記録を1件取得
	public MedicalRecord findMedicalRecordById(Integer medicalRecordId) {
		return medicalRecordRepository
				.findById(medicalRecordId)
				.orElse(null);
	}
	
	// 指定した患者に関連する記録を診療記録日の降順で取得
	public List<MedicalRecord> findPatientRecords(
			Integer patientId
			) {
		return medicalRecordRepository
					.findByPatientIdOrderByMedicalRecordDateDesc(patientId);
	}
}