package com.tasukarte.referral.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tasukarte.referral.entity.Referral;

//紹介状リポジトリ
public interface ReferralRepository
        extends JpaRepository<Referral, Integer> {

	//下書き一覧取得
	//findBy:条件に一致するデータを取得
	//SenderHospital_HospitalId:送信元病院IDが引数と一致
	//And
	//Staff_StaffId:作成者IDが引数と同じ
	//And
	//Status:ステータスが引数と一致
	//And
	//IsDeletedFalse:削除されていない
	//OrderByCreatedAtDesc:作成日で降順
	List<Referral> findBySenderHospital_HospitalIdAndStaff_StaffIdAndStatusAndIsDeletedFalseOrderByCreatedAtDesc(
			Integer hospitalId,
			Integer staffId,
			String status);
	
	//送信元病院の紹介状一覧
	//送信元病院の職員が閲覧する送信前、申請中、差し戻しで使用
    //送信元病院・ステータスが一致し、
    //削除されていない紹介状を作成日時の降順で取得
    List<Referral> findBySenderHospital_HospitalIdAndStatusAndIsDeletedFalseOrderByCreatedAtDesc(
            Integer hospitalId,
            String status);

    //送信先病院の紹介状一覧
    //送信先病院の職員が承認待ちを確認する際に使用
    //送信先病院・ステータスが一致し、
    //削除されていない紹介状を作成日時の降順で取得
    List<Referral> findByRecipientHospital_HospitalIdAndStatusAndIsDeletedFalseOrderByCreatedAtDesc(
            Integer hospitalId,
            String status);
    
    //共有中の紹介状一覧
    //送信元病院と送信先病院が共有中を表示する時に使用
    //送信元病院または送信先病院が一致し、
    //削除されておらず、共有終了日が今日以降または未設定の紹介状を取得(共有中の状態で共有終了日が未設定であることはないが一応)
    @Query("""
            SELECT r
            FROM Referral r
            WHERE r.isDeleted = false
              AND (
                    r.senderHospital.hospitalId = :hospitalId
                    OR r.recipientHospital.hospitalId = :hospitalId
                  )
              AND r.status = :status
              AND (
                    r.sharedEndAt IS NULL
                    OR r.sharedEndAt >= CURRENT_DATE
                  )
            ORDER BY r.createdAt DESC
            """)
    List<Referral> findSharedReferrals(
            @Param("hospitalId") Integer hospitalId,
            @Param("status") String status);	
}