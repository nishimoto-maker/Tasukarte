package com.tasukarte.referral.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.hospital.dto.HospitalSearchDTO;
import com.tasukarte.patient.dto.PatientSearchReferralDTO;
import com.tasukarte.referral.service.PatientHospitalSearchService;


//通常のコントローラーではなく、メソッドの戻り値をJSONなどのレスポンスデータとして返す
//javascriptで遷移なしにデータをやり取りする場合に使用
@RestController
//このコントローラーで利用するURLの共通部分を指定。各メソッドではそれ以降を指定すればよい
@RequestMapping("/referrals/search")
public class PatientReferralSearchController {

	private final PatientHospitalSearchService searchService;
	
	//コンストラクタ
	public PatientReferralSearchController(
			PatientHospitalSearchService searchService) {
		this.searchService = searchService;
	}
	
	//患者検索コントローラー
	@GetMapping("/patients")
	public List<PatientSearchReferralDTO> serachPatients(
			//URLの喰えりパラメーターから取得
			@RequestParam String keyword,
			//SpringSecurityで認証済みのログインユーザー情報を取得(common>security>LoginUser)
			@AuthenticationPrincipal LoginUser loginUser) {
		
		return searchService.searchPatients(keyword, loginUser);
	}
	
	//宛先病院検索コントローラー
	@GetMapping("/hospitals")
	public List<HospitalSearchDTO> searchHospitals(
			//URLの喰えりパラメーターから取得
			@RequestParam String keyword,
			//SpringSecurityで認証済みのログインユーザー情報を取得
			@AuthenticationPrincipal LoginUser loginUser) {
		
		return searchService.searchHospitals(keyword,loginUser);
	}

}
