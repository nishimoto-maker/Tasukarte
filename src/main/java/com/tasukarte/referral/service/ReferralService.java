package com.tasukarte.referral.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.hospital.entity.Hospital;
import com.tasukarte.hospital.repository.HospitalRepository;
import com.tasukarte.patient.entity.Patient;
import com.tasukarte.patient.repository.PatientRepository;
import com.tasukarte.referral.ReferralStatus;
import com.tasukarte.referral.dto.ReferralCreateFormDTO;
import com.tasukarte.referral.dto.ReferralDraftFormDTO;
import com.tasukarte.referral.dto.ReferralEditDTO;
import com.tasukarte.referral.dto.ReferralInputInfoDTO;
import com.tasukarte.referral.dto.ReferralListDTO;
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
    
    
    //紹介状入力画面に表示する情報を取得する関数
    public ReferralInputInfoDTO getInputInfo(
            ReferralCreateFormDTO form,
            LoginUser loginUser) {

        //患者・宛先病院を取得し、利用可否を確認
        ReferralSelection selection = validateAndGetSelection(
                form.getPatientId(),
                form.getRecipientHospitalId(),
                loginUser);

        //患者名・宛先病院名をDTOに格納して返す
        return new ReferralInputInfoDTO(
                selection.patient.getName(),
                selection.recipientHospital.getHospitalName());
    }
    
    
    //新規の紹介状を下書きとして一時保存する(下書きを修正して再保存は別)
    public void saveDraft(
    		ReferralDraftFormDTO form,
    		LoginUser loginUser) {
    	
    	
    	//患者・宛先病院を取得し、利用可否を確認
    	ReferralSelection selection = validateAndGetSelection(
    	        form.getPatientId(),
    	        form.getRecipientHospitalId(),
    	        loginUser);

    	//取得した患者
    	Patient patient = selection.patient;

    	//取得した宛先病院
    	Hospital recipientHospital = selection.recipientHospital;
    	
    	 //ログインユーザーの所属病院を取得
        Hospital senderHospital = hospitalRepository
                .findById(loginUser.getHospitalId())
                //TODO:このエラー発生時の処理も必ず実装すること（どの画面に何をどう渡して何を表示するのか）
                .orElseThrow(() ->
                        new IllegalArgumentException("所属病院が存在しません。"));

        //ログインユーザーの職員情報を取得
        Staff staff = staffRepository
                .findById(loginUser.getStaffId())
              //TODO:このエラー発生時の処理も必ず実装すること（どの画面に何をどう渡して何を表示するのか）
                .orElseThrow(() ->
                        new IllegalArgumentException("職員情報が存在しません。"));
        
        //共有開始日・終了日の前後関係を確認
        validateSharedPeriod(form);
        //TODO:共有開始日・終了日の必須条件を確認
        
        //以下、一時保存用entityインスタンスの作成
        //ReferralEntityインスタンスを生成
        Referral referral = new Referral();
        
        //患者・病院・職員を設定
        referral.setPatient(patient);
        referral.setSenderHospital(senderHospital);
        referral.setRecipientHospital(recipientHospital);
        referral.setStaff(staff);

        //フォームから受け取った入力内容を設定
        referral.setReferralPurpose(form.getReferralPurpose());
        referral.setRequestContent(form.getRequestContent());
        referral.setRemarks(form.getRemarks());
        referral.setSharedStartAt(form.getSharedStartAt());
        referral.setSharedEndAt(form.getSharedEndAt());
        
        //初回登録日時(編集時も更新せずに維持する想定)
        //↓Entityで設定済みのためいらない
        //referral.setCreatedAt(LocalDateTime.now());

        //一時保存時点では未依頼・未承認
        referral.setRequestedAt(null);
        referral.setApprovedAt(null);

        //ステータスを下書きに設定
        referral.setStatus(ReferralStatus.DRAFT);
        
        //削除フラグをfalseに設定(初期値falseで設定してはいる)
        referral.setIsDeleted(false);
        
        //DBに保存
        referralRepository.save(referral);
    }
    
    
    //紹介状一覧画面に表示するデータを取得する
    @Transactional(readOnly = true)
    public List<ReferralListDTO> getReferralList(LoginUser loginUser) {

        //ログインユーザーの所属病院IDを取得
        Integer hospitalId = loginUser.getHospitalId();

        //所属病院が送信元になっている、削除されていない下書き状態の紹介状を取得
        List<Referral> referrals = referralRepository
                .findBySenderHospital_HospitalIdAndStatusAndIsDeletedFalseOrderByCreatedAtDesc(
                        hospitalId, ReferralStatus.DRAFT);

        
        //取得したEntityを一覧表示用DTOに変換
        return referrals.stream()
                .map(referral -> new ReferralListDTO(
                        referral.getReferralId(),
                        referral.getPatient().getName(),
                        referral.getRecipientHospital().getHospitalName(),
                        referral.getCreatedAt(),
                        referral.getStatus()))
                .toList();
    }
    
    
    //患者・宛先病院の取得と利用可否の確認、バリデーション
    private ReferralSelection validateAndGetSelection(
            Integer patientId,
            Integer recipientHospitalId,
            LoginUser loginUser) {

        //患者IDが未設定の場合
        if (patientId == null) {
            throw new IllegalArgumentException(
                    "患者が選択されていません");
        }

        //宛先病院IDが未設定の場合
        if (recipientHospitalId == null) {
            throw new IllegalArgumentException(
                    "宛先病院が選択されていません");
        }

        //患者を取得
        Patient patient = patientRepository
                .findById(patientId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "患者が存在しません"));

        //患者がログインユーザーの所属病院に登録されているか確認
        if (!patient.getHospital().getHospitalId()
                .equals(loginUser.getHospitalId())) {

            throw new IllegalArgumentException(
                    "この患者を選択する権限がありません");
        }

        //宛先病院を取得
        Hospital recipientHospital = hospitalRepository
                .findById(recipientHospitalId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "宛先病院が存在しません"));

        //宛先病院が削除済み、または自院でないか確認
        if (recipientHospital.isDeleted()
                || recipientHospital.getHospitalId()
                        .equals(loginUser.getHospitalId())) {

            throw new IllegalArgumentException(
                    "指定された宛先病院は利用できません");
        }

        //取得した患者・宛先病院をまとめて返す
        return new ReferralSelection(patient, recipientHospital);
    }
    
  //編集対象の下書きを取得する
    @Transactional(readOnly = true)
    public ReferralEditDTO getDraftForEdit(
            Integer referralId,
            LoginUser loginUser) {

        //紹介状IDから紹介状を取得
        Referral referral = referralRepository.findById(referralId)
                .orElseThrow(() ->
                //TODO:エラー時処理実装
                        new IllegalArgumentException("紹介状が存在しません。"));

        //ログインユーザーの所属病院が送信元であるか確認
        if (!referral.getSenderHospital().getHospitalId()
                .equals(loginUser.getHospitalId())) {
        	//TODO:エラー時処理実装
            throw new IllegalArgumentException(
                    "この紹介状を編集する権限がありません。");
        }

        //削除済みの紹介状ではないか確認
        if (referral.getIsDeleted()) {
        	//TODO:エラー時処理実装
            throw new IllegalArgumentException(
                    "この紹介状は削除されています。");
        }

        //下書き状態であるか確認
        if (!ReferralStatus.DRAFT.equals(referral.getStatus())) {
        	//TODO:エラー時処理実装
            throw new IllegalArgumentException(
                    "編集できません");
        }

        //編集画面に渡すDTOに変換
        return new ReferralEditDTO(
                referral.getReferralId(),
                referral.getPatient().getPatientId(),
                referral.getPatient().getName(),
                referral.getRecipientHospital().getHospitalId(),
                referral.getRecipientHospital().getHospitalName(),
                referral.getReferralPurpose(),
                referral.getRequestContent(),
                referral.getRemarks(),
                referral.getSharedStartAt(),
                referral.getSharedEndAt());
    }
    
    //共有期間の前後関係を確認する
    private void validateSharedPeriod(ReferralDraftFormDTO form) {
    	
    	//DTOから共有開始日と終了日を取得
        LocalDate startAt = form.getSharedStartAt();
        LocalDate endAt = form.getSharedEndAt();

        // 開始日と終了日の両方が入力されている場合のみ確認
        if (startAt != null && endAt != null) {

            // 終了日が開始日より前の場合
            if (endAt.isBefore(startAt)) {
                throw new IllegalArgumentException(
                        "共有終了日は共有開始日以降の日付を指定してください。");
            }
        }
    }

    
    //このクラス内でのみ利用する、患者・宛先病院の情報をまとめて保持する内部クラス
    private static class ReferralSelection {

        private final Patient patient;

        private final Hospital recipientHospital;

        private ReferralSelection(
                Patient patient,
                Hospital recipientHospital) {

            this.patient = patient;
            this.recipientHospital = recipientHospital;
        }
    }
}
