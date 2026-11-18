package com.tasukarte.referral.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//紹介状系コントローラー
@Controller
public class ReferralController {

    // 紹介状一覧画面を表示する
    @GetMapping("/referrals")
    public String showReferralList() {
        return "referral/list";
    }
}