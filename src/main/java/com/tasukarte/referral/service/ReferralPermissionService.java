package com.tasukarte.referral.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.referral.ReferralStatus;
import com.tasukarte.referral.entity.Referral;

@Service
public class ReferralPermissionService {

	//医師を表す役職ID
	//未設定の場合は-1として、医師として扱わない。
	@Value("${tasukarte.position.doctor-id:-1}")
	private Integer doctorPositionId;
	
	//ログインユーザーが医師であるか確認
	public boolean isDoctor(LoginUser loginUser) {
		
		//ログインユーザーが存在していて、
		//ログインユーザーの役職IDが設定されていて、
		//ログインユーザーの役職IDと医師のIDが一致するか
		return loginUser != null
				&& loginUser.getPositionId() != null
				&& loginUser.getPositionId().equals(doctorPositionId);
	}
	
	//新規作成出来るか確認
	public boolean canCreate(LoginUser loginUser) {
		
		//isDoctorを呼び出すことで、医師であればtrueで作成可能となる
		return isDoctor(loginUser);
	}
	
	//編集できるか確認
	public boolean canEdit(Referral referral, LoginUser loginUser) {
		
		//紹介状が存在するか、紹介状が削除されていないか
		//ログインユーザーが存在するか
		if (!isActiveReferral(referral) || loginUser == null) {
	        return false;
	    }
		
		//紹介状作成者本人か
		//送信元病院に所属しているか
		//紹介状は編集可能な状態か
		return isCreator(referral, loginUser)
				&& isSenderHospital(referral, loginUser)
				&& isEditableStatus(referral);
	}
	
	//削除できるか確認
	public boolean canDelete(Referral referral, LoginUser loginUser) {
		
		//削除条件ｊは編集条件と同様であるため、canEditに渡す
		//ただし、別の条件が発生した場合は変更する必要がある
		return canEdit(referral, loginUser);
	}
	
	//一時保存できるか確認
	//referralがnullの場合は新規作成として扱う
	public boolean canSaveDraft(Referral referral, LoginUser loginUser) {

		//医者でないか
        if (!isDoctor(loginUser)) {
            return false;
        }

        //新規作成時はreferralエンティティのインスタンスが存在しないため、
        //後の判定に回さずにtrueを返す
        if (referral == null) {
            return true;
        }
        
        //既存紹介状の場合、削除済みでないか確認
        if(!isActiveReferral(referral)) {
        	return false;
        }

        //作成者本人か
        //送信元病院に所属しているか
        //紹介状は編集可能な状態か
        return isCreator(referral, loginUser)
                && isSenderHospital(referral, loginUser)
                && isEditableStatus(referral);
    }
	
	//登録できるか確認
	public boolean canRegister(Referral referral, LoginUser loginUser) {
		
		//紹介状が存在するか、紹介状が削除されていないか
		//ログインユーザーは医者か、関数内でログインユーザーが存在するのかも確認している。
		if (!isActiveReferral(referral) || !isDoctor(loginUser)) {
	        return false;
	    }
		
		//作成者本人か
		//送信元病院に所属しているか
		//紹介状の状態が下書きまたは差し戻しか
		return isCreator(referral, loginUser)
				&& isSenderHospital(referral, loginUser)
				&& (ReferralStatus.DRAFT.equals(referral.getStatus())
						|| ReferralStatus.RETURNED.equals(referral.getStatus()));
	}
	
	//送信できるか確認
	public boolean canSend(Referral referral, LoginUser loginUser) {
		
		//紹介状が存在するか、紹介状が削除されていないか
		//ログインユーザーは医者か、関数内でログインユーザーが存在するのかも確認している。
		if (!isActiveReferral(referral) || !isDoctor(loginUser)) {
	        return false;
	    }
		
		//送信元病院に所属しているか
		//紹介状の状態が送信前か
		return isSenderHospital(referral, loginUser)
				&& ReferralStatus.READY_TO_SEND.equals(referral.getStatus());
	}
	
	//承認できるか確認
	public boolean canApprove(Referral referral, LoginUser loginUser) {
		
		//紹介状が存在するか、紹介状が削除されていないか
		//ログインユーザーは医者か、関数内でログインユーザーが存在するのかも確認している。
		if (!isActiveReferral(referral) || !isDoctor(loginUser)) {
	        return false;
	    }

		//送信先病院に所属しているか
		//紹介状の状態が承認待ちか
        return isRecipientHospital(referral, loginUser)
                && ReferralStatus.PENDING.equals(referral.getStatus());
	}
	
	//拒否できるか確認
    public boolean canReject(Referral referral, LoginUser loginUser) {

        //承認と同じ条件のため、canApproveを呼び出す
        return canApprove(referral, loginUser);
    }
    
    //閲覧できるか確認
    public boolean canView(Referral referral, LoginUser loginUser) {

    	//referralインスタンスがないか
    	//ログインユーザーが存在しないか
    	//紹介状の削除フラグがtrueか
        if (referral == null || loginUser == null || referral.getIsDeleted()) {
            return false;
        }
        
        //紹介状の状態を取得
        String status = referral.getStatus();

        //下書き：送信元病院の作成者本人のみ
        if (ReferralStatus.DRAFT.equals(status)) {
            return isCreator(referral, loginUser)
            		&& isSenderHospital(referral, loginUser);
        }

        //差し戻し・送信待ち：送信元病院
        if (ReferralStatus.RETURNED.equals(status)
                || ReferralStatus.READY_TO_SEND.equals(status)) {
            return isSenderHospital(referral, loginUser);
        }

        //承認待ち：送信元・送信先病院
        if (ReferralStatus.PENDING.equals(status)) {
            return isSenderHospital(referral, loginUser)
                    || isRecipientHospital(referral, loginUser);
        }

        //共有中：送信元・送信先病院
        //共有終了日を過ぎている場合は閲覧不可
        if (ReferralStatus.SHARED.equals(status)) {
        	
        	//送信元病院か送信先病院
            boolean isRelatedHospital =
                    isSenderHospital(referral, loginUser)
                    || isRecipientHospital(referral, loginUser);
            
            //共有終了日
            LocalDate endAt = referral.getSharedEndAt();

            //今日が共有終了日より前か
            return isRelatedHospital
                    && (endAt == null || !endAt.isBefore(LocalDate.now()));
        }

        return false;
    }
    
    //編集可能なステータスか確認
    private boolean isEditableStatus(Referral referral) {
    	
    	//下書き状態か
    	//差し戻し状態か
    	//送信前状態か
        return ReferralStatus.DRAFT.equals(referral.getStatus())
                || ReferralStatus.RETURNED.equals(referral.getStatus())
                || ReferralStatus.READY_TO_SEND.equals(referral.getStatus());
    }
    
    //作成者本人であるか確認
    private boolean isCreator(Referral referral, LoginUser loginUser) {
    	
    	//紹介状のスタッフ情報がnullではなく、
    	//紹介状に紐づいたスタッフ情報の職員IDがnullでなく、
    	//ログインユーザーの職員IDと紹介状に紐づいた職員ＩＤが同じか
        return referral.getStaff() != null
                && referral.getStaff().getStaffId() != null
                && referral.getStaff().getStaffId().equals(loginUser.getStaffId());
    }
    
    //送信元病院に所属しているか確認
    private boolean isSenderHospital(Referral referral, LoginUser loginUser) {
    	
    	//紹介状の送信元病院情報がnullではなく
    	//紹介状に紐づいた送信病院情報のIDがnullではなく
    	//ログインユーザーの所属病院IDと紹介状に紐づいた送信元病院IDが同じか
        return referral.getSenderHospital() != null
                && referral.getSenderHospital().getHospitalId() != null
                && referral.getSenderHospital().getHospitalId()
                        .equals(loginUser.getHospitalId());
    }
    
    //送信先病院に所属しているか確認
    private boolean isRecipientHospital(Referral referral, LoginUser loginUser) {
    	
    	//紹介状の送信先病院情報がnullではなく
    	//紹介状に紐づいた送信先病院情報のIDがnullではなく
    	//ログインユーザーの所属病院IDと紹介状に紐づいた送信先病院IDが同じか
        return referral.getRecipientHospital() != null
                && referral.getRecipientHospital().getHospitalId() != null
                && referral.getRecipientHospital().getHospitalId()
                        .equals(loginUser.getHospitalId());
    }
    
    //紹介状が削除されていないか確認
    private boolean isActiveReferral(Referral referral) {
    	
    	//referralがnullでなく、isDeletedがfalse
        return referral != null && !referral.getIsDeleted();
    }
}
