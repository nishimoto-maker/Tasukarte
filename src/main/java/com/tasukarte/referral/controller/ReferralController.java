package com.tasukarte.referral.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.referral.dto.ReferralCreateFormDTO;
import com.tasukarte.referral.dto.ReferralDraftFormDTO;
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
    public String showReferralList() {
        return "referral/referralList";
    }
    
    //紹介状作成画面を表示する
    @GetMapping("/referrals/create")
    public String showReferralCreate() {
    	return "referral/referralCreate";
    }
    
    //患者・宛先病院の選択状況を確認してから入力画面へ進む
    @PostMapping("/referrals/input")
    public String showReferralInput(
    		@ModelAttribute ReferralCreateFormDTO form,
    		@AuthenticationPrincipal LoginUser loginUser,
    		Model model) {
    	
    	//以下バリデーション系処理（js側でもバリデーションすること。特にデータの型。）
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
    	
    	//以下を実装する際には、Controllerで直接Repositoryを追加せず、Serviceでやる方が分離が出来ている。
    	//TODO:該当患者IDが存在しているか
    	//TODO:該当病院IDが存在しているか
    	//TODO:送信された患者IDの所属病院IDとログインユーザーの所属病院IDが一致しているか
    	//TODO:宛先病院がログインユーザーの所属病院でなく、削除済みでないか
    	
    	//以下次画面用処理
    	
    	//選択されたIDを入力画面に渡す
        model.addAttribute("patientId", form.getPatientId());
        model.addAttribute("recipientHospitalId", form.getRecipientHospitalId());        
        
        //TODO:患者IDから患者情報を取得し、患者名をModelに追加
        //TODO:病院IDから病院情報を取得し、病院名をModelに追加
        
        //紹介状入力画面を表示する
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
    
    
}