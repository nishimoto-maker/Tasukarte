package com.tasukarte.referral.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.referral.entity.Referral;

//紹介状リポジトリ
public interface ReferralRepository
        extends JpaRepository<Referral, Integer> {

	//送信元病院に紐づく、削除されていない紹介状を登録日時の降順で取得
	//findBy:条件に一致するデータを取得
	//SenderHospital_HospitalId:送信元病院の病院ID
	//And:And条件
	//Status:紹介状の状態
	//And
	//IsDeletedFalse:削除フラグがfalse
	//OrderByCreatedAtDesc:作成日時の降順
    List<Referral> findBySenderHospital_HospitalIdAndStatusAndIsDeletedFalseOrderByCreatedAtDesc(
            Integer hospitalId, String status);
	
}