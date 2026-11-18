package com.tasukarte.patient.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.patient.entity.Patient;

public interface PatientRepository 
        extends JpaRepository<Patient, Integer>{
	
	//患者名で部分一致検索
	//Spring Data　JPAがメソッド名から検索条件を判断する
	//findBy:検索する
	//Name:患者氏名を対象にする(entityのフィールド名から先頭大文字にする)
	//Containing:部分一致
	//And:And条件
	//Hospital_HospitalId:ログインユーザーの所属病院で絞り込む
	//PatientEntityで関連付けされているため、
	//"_"でPatientのHospitalフィールドの関連先のHospitalIdをたどることを指定。(Hospital.hospitalIdと同義)
	//空文字の場合全病院が候補になるため、service側で空文字、未入力チェックをすること
	List<Patient> findByNameContainingAndHospital_HospitalId(
			String name,
			Integer hospitalId
			);

}
