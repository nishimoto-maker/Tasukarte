package com.tasukarte.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.hospital.entity.Hospital;

//病院リポジトリ
public interface HospitalRepository
        extends JpaRepository<Hospital, Integer> {
	
	//病院名で部分一致検索
	//Spring Data　JPAがメソッド名から検索条件を判断する
	//findBy:検索する
	//HospitalName:病院名を対象にする(entityのフィールド名から先頭大文字にする)
	//Containing:部分一致
	//And:And条件
	//IsDeletedFalse:削除フラグがfalse
	//HospitalIdNot:指定した病院IDではない
	//空文字の場合全病院が候補になるため、service側で空文字、未入力チェックをすること
	List<Hospital> findByHospitalNameContainingAndIsDeletedFalseAndHospitalIdNot(
			String hospitalName,
			Integer hospitalId
			);
}