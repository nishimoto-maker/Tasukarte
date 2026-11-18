package com.tasukarte.referral.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.hospital.dto.HospitalSearchDTO;
import com.tasukarte.hospital.entity.Hospital;
import com.tasukarte.hospital.repository.HospitalRepository;
import com.tasukarte.patient.dto.PatientSearchReferralDTO;
import com.tasukarte.patient.entity.Patient;
import com.tasukarte.patient.repository.PatientRepository;


@Service
//読み込みのみ許可
@Transactional(readOnly = true)
//紹介状作成で、病院患者をオートコンプリートで検索するクラス
public class PatientHospitalSearchService {
	
	//patientのリポジトリで検索を利用する
	private final PatientRepository patientRepository;
	
	//hospitalのリポジトリで検索を利用する
	private final HospitalRepository hospitalRepository;
	
	//コンストラクタで使用リポジトリのインスタンス生成、finalのため変更させない
	public PatientHospitalSearchService(
			PatientRepository patientRepository,
			HospitalRepository hospitalRepository
			) {
		
		this.patientRepository = patientRepository;
		this.hospitalRepository = hospitalRepository;
		
	}
	
	//患者検索
	public List<PatientSearchReferralDTO> searchPatients(
			String keyword,
			LoginUser loginUser) {
		
		//未入力の場合検索させずに空のリストを返す
		if (keyword == null || keyword.isBlank()) {
			return List.of();
		}
		
		//ログインユーザーの所属病院に登録されている患者を検索
		List<Patient> patients =
				patientRepository.findByNameContainingAndHospital_HospitalId(
						keyword.trim(),
						loginUser.getHospitalId());
		
		//取得したPatient型のデータをDTOに変換しリスト化して返す
		return patients.stream()
				.map(patient -> new PatientSearchReferralDTO(
						patient.getPatientId(),
						patient.getName(),
						patient.getBirthDay()
						)).toList();
	}
	
	//宛先病院検索
	public List<HospitalSearchDTO> searchHospitals(
			String keyword,
			LoginUser loginUser) {
		
		//未入力の場合検索させずに空のリストを返す
		if (keyword == null || keyword.isBlank()) {
			return List.of();
		}
		
		//削除済み病院とログインユーザーの所属病院を除外
		List<Hospital> hospitals = 
				hospitalRepository
					.findByHospitalNameContainingAndIsDeletedFalseAndHospitalIdNot(
							keyword.trim(),
							loginUser.getHospitalId());
		
		//取得したHospital型のデータをDTOに変換しリスト化して返す
		return hospitals.stream()
				.map(hospital -> new HospitalSearchDTO(
						hospital.getHospitalId(),
						hospital.getHospitalName(),
						hospital.getHospitalDirectorName()
						))
						.toList();
	}
}
