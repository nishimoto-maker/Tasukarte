package com.tasukarte.referral.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.referral.dto.ReferralCreateFormDTO;
import com.tasukarte.referral.dto.ReferralDraftFormDTO;
import com.tasukarte.referral.dto.ReferralEditDTO;
import com.tasukarte.referral.dto.ReferralInputInfoDTO;
import com.tasukarte.referral.dto.ReferralListDTO;
import com.tasukarte.referral.service.ReferralService;

//紹介状系コントローラー
@Controller
public class ReferralController {
	
	//紹介状サービス
	private final ReferralService referralService;
	
	//コンストラクタ
	public ReferralController(ReferralService referralService) {
		this.referralService = referralService;
	}

	//全メソッド共通:各画面へ遷移する権限があるか確認する処理を後で必ず実装すること
	
    // 紹介状一覧画面を表示する
    @GetMapping("/referrals")
    public String showReferralList(
    		@AuthenticationPrincipal LoginUser loginUser,
    		Model model
    		) {
    	//紹介状一覧を取得（区別なし）
    	List<ReferralListDTO> referralList =
    			referralService.getReferralList(loginUser);
    	
    	//取得したリストをModelに追加
    	model.addAttribute("referralList", referralList);
        return "referral/referralList";
    }
    
    //紹介状作成画面を表示する
    @GetMapping("/referrals/create")
    public String showReferralCreate() {
    	return "referral/referralCreate";
    }
    
    //紹介状入力画面を表示する
    @PostMapping("/referrals/input")
    public String showReferralInput(
            @ModelAttribute ReferralCreateFormDTO form,
            @AuthenticationPrincipal LoginUser loginUser,
            Model model) {

        //患者が未選択の場合
        if (form.getPatientId() == null) {
            model.addAttribute("errorMessage",
                    "患者を選択してください");
            return "referral/referralCreate";
        }

        //宛先病院が未選択の場合
        if (form.getRecipientHospitalId() == null) {
            model.addAttribute("errorMessage",
                    "宛先病院を選択してください");
            return "referral/referralCreate";
        }

        //患者名・宛先病院名を取得
        //TODO:service側でバリデーションを行っているため、エラー時の処理を@ControllerAdvice等で整備する
        ReferralInputInfoDTO inputInfo =
                referralService.getInputInfo(form, loginUser);

        //入力画面に表示する情報をModelに追加
        model.addAttribute("patientId", form.getPatientId());
        model.addAttribute("recipientHospitalId",
                form.getRecipientHospitalId());
        model.addAttribute("patientName",
                inputInfo.getPatientName());
        model.addAttribute("recipientHospitalName",
                inputInfo.getRecipientHospitalName());

        return "referral/referralInput";
    }
    
    //紹介状を一時保存する
    @PostMapping("/referrals/draft")
    public String saveDraft(
    		//formから送信されたデータをDTOに格納
    		@ModelAttribute ReferralDraftFormDTO form,
    		//ログインユーザー情報取得
    		@AuthenticationPrincipal LoginUser loginUser) {
    	referralService.saveDraft(form, loginUser);
    	
    	return "redirect:/referrals";
    }
    
    //下書き編集画面を表示する
    @GetMapping("/referrals/{referralId}/edit")
    public String showReferralEdit(
            @PathVariable Integer referralId,
            @AuthenticationPrincipal LoginUser loginUser,
            Model model) {

        //編集対象の下書きを取得(エラー時の処理を行うこと)
        ReferralEditDTO editInfo =
                referralService.getDraftForEdit(referralId, loginUser);

        //取得した情報をModelに追加
        model.addAttribute("edit", editInfo);

        return "referral/referralEdit";
    }
    
    @PostMapping("/referrals/{referralId}/update")
    public String updateDraft(
    		@PathVariable Integer referralId,
    		@ModelAttribute ReferralDraftFormDTO form,
    		@AuthenticationPrincipal LoginUser loginUser) {
    	
    	//Serviceに更新処理を依頼
    	referralService.updateDraft(referralId, form, loginUser);
    	
    	//更新後は紹介状一覧画面へ戻る
    	return "redirect:/referrals";
    }
    
    
}