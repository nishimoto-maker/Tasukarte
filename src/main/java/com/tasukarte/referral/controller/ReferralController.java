package com.tasukarte.referral.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tasukarte.common.security.LoginUser;
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
    		//患者IDを受け取る。存在しない場合でも関数内でエラーハンドリングしたいためrequiredをfalseに
    		@RequestParam(required = false) Integer patientId,
    		//宛先病院IDを受け取る。存在しない場合でも関数内でエラーハンドリングしたいためrequiredをfalseに
    		@RequestParam(required = false) Integer recipientHospitalId,
    		Model model) {
    	
    	//以下バリデーション系処理（js側でもバリデーションすること。特にデータの型。）
    	//患者が未選択の場合
    	if (patientId == null) {
    		model.addAttribute("errorMessage",
    				"患者を選択してください");
    		return "referral/referralCreate";
    	}
    	
    	//宛先病院が未選択の場合
    	if (recipientHospitalId == null) {
    		model.addAttribute("errorMessage",
    				"患者を選択してください");
    		return "referral/regferralCreate";
    	}
    	
    	//患者が空欄の場合（nullとは別）
    	//宛先病院が空欄の場合(nullとは別)
    	
    	//該当患者IDが存在しているか
    	//該当病院IDが存在しているか（削除フラグがtrueでないかも）
    	//送信された患者IDの所属病院IDとログインユーザーの所属病院IDが一致しているか
    	//宛先病院がログインユーザーの所属病院でないか
    	
    	//患者名、病院名を使いたいので、取得する
    	//リクエストで患者名と病院名も受け取って、
    	//IDから取得したデータと送られた名前が一致するか確かめたほうが良いか？(これはバリデーション系の処理になる)
    	
    	//以下次画面用処理(DTOの使用を選択するべきのはず)
    	
    	//患者名、病院名を入力画面へ渡す
    	
    	//選択されたIDを入力画面に渡す
        model.addAttribute("patientId", patientId);
        model.addAttribute("recipientHospitalId", recipientHospitalId);        
        
        //紹介状入力画面を表示する
        return "referral/referralInput";
    }
    
    //紹介状を一時保存する
    @PostMapping("referral/draft")
    public String saveDraft(
    		//formから送信されたデータをDTOに格納
    		@ModelAttribute ReferralDraftFormDTO form,
    		//ログインユーザー情報取得
    		@AuthenticationPrincipal LoginUser loginUser) {
    	referralService.saveDraft(form, loginUser);
    	
    	return "redirect:/referrals";
    }
    
    
}