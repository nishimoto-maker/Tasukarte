package com.tasukarte.referral.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.hospital.entity.Hospital;
import com.tasukarte.hospital.repository.HospitalRepository;
import com.tasukarte.patient.entity.Patient;
import com.tasukarte.patient.repository.PatientRepository;
import com.tasukarte.referral.dto.ReferralDraftFormDTO;
import com.tasukarte.referral.entity.Referral;
import com.tasukarte.referral.repository.ReferralRepository;
import com.tasukarte.staff.entity.Staff;
import com.tasukarte.staff.repository.StaffRepository;


//紹介状関係サービス
@Service
@Transactional
public class ReferralService {
	
	//各種使用リポジトリを定義
	private final ReferralRepository referralRepository;
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    private final StaffRepository staffRepository;

    //コンストラクタ
    public ReferralService(
            ReferralRepository referralRepository,
            PatientRepository patientRepository,
            HospitalRepository hospitalRepository,
            StaffRepository staffRepository) {

        this.referralRepository = referralRepository;
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
        this.staffRepository = staffRepository;
    }
    
    //新規の紹介状を下書きとして一時保存する
    public void saveDraft(
    		ReferralDraftFormDTO form,
    		LoginUser loginUser) {
    	
    	//以下、取得及びバリデーション、エラーハンドリング
    	//患者IDが未設定の場合
    	if (form.getPatientId() == null) {
    		//このエラー発生時の処理も必ず実装すること
    		throw new IllegalArgumentException("患者が選択されていません");
    	}
    	
    	//宛先病院IDが未設定の場合
    	if (form.getRecipientHospitalId() == null) {
    		//このエラー発生時の処理も必ず実装すること
    		throw new IllegalArgumentException("宛先病院が選択されていません");
    	}
    	
    	//患者を取得
    	Patient patient = patientRepository
    			.findById(form.getPatientId())
    			.orElseThrow(() ->
    			//このエラーが発生時の処理も必ず実装すること
    					new IllegalArgumentException(
    							"患者が存在しません。"));
    	
    	//ログインユーザーの所属病院に登録されている患者か確認
    	//これを含む、権限系の確認機能を共通部品化できないか考える事）
    	if (!patient.getHospital().getHospitalId()
    			.equals(loginUser.getHospitalId())) {
    		//このエラー発生時の処理も必ず実装すること
    		throw new IllegalArgumentException(
    				//もしこのメッセージを渡すなら、内容を変更すること。（患者が存在することはわかるのでリスク）
    				"この患者を選択する権限がありません");
    	}
    	
    	//宛先病院を取得
    	Hospital recipientHospital = hospitalRepository
    			.findById(form.getRecipientHospitalId())
    			.orElseThrow(() ->
    					new IllegalArgumentException("宛先病院が存在しません"));
    	
    	//宛先病院が削除済み、あるいはログインユーザーの所属病院でないか確認
    	if (recipientHospital.isDeleted()
    			|| recipientHospital.getHospitalId()
    				.equals(loginUser.getHospitalId())) {
    		//このエラー発生時の処理も必ず実装すること
    		throw new IllegalArgumentException(
    				"指定された宛先病院は利用できません");
    	}
    	
    	 //ログインユーザーの所属病院を取得
        Hospital senderHospital = hospitalRepository
                .findById(loginUser.getHospitalId())
                //このエラー発生時の処理も必ず実装すること（どの画面に何をどう渡して何を表示するのか）
                .orElseThrow(() ->
                        new IllegalArgumentException("所属病院が存在しません。"));

        //ログインユーザーの職員情報を取得
        Staff staff = staffRepository
                .findById(loginUser.getStaffId())
              //このエラー発生時の処理も必ず実装すること（どの画面に何をどう渡して何を表示するのか）
                .orElseThrow(() ->
                        new IllegalArgumentException("職員情報が存在しません。"));
        
        //以下、一時保存用entityインスタンスの作成
        //ReferralEntityインスタンスを生成
        Referral referral = new Referral();
        
        //患者・病院・職員を設定
        referral.setPatient(patient);
        referral.setSenderHospital(senderHospital);
        referral.setRecipientHospital(recipientHospital);
        referral.setStaff(staff);

        // フォームから受け取った入力内容を設定
        referral.setReferralPurpose(form.getReferralPurpose());
        referral.setRequestContent(form.getRequestContent());
        referral.setRemarks(form.getRemarks());
        referral.setSharedStartAt(form.getSharedStartAt());
        referral.setSharedEndAt(form.getSharedEndAt());
        
        //初回登録日時(編集すると更新する想定)
        //↓Entityで設定済みのためいらない
        //referral.setCreatedAt(LocalDateTime.now());

        //一時保存時点では未依頼・未承認
        referral.setRequestedAt(null);
        referral.setApprovedAt(null);

        //ステータスを下書きに設定
        referral.setStatus("DRAFT");
        
        //削除フラグをfalseに設定(初期値falseで設定してはいる)
        referral.setIsDeleted(false);
        
        //DBに保存
        referralRepository.save(referral);
    }
}
