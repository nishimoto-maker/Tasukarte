package com.tasukarte.referral.constant;

//紹介状の状態を規定するクラス
public class ReferralStatus {
	
	//下書き
	public static final String DRAFT = "DRAFT";

	//承認待ち
    public static final String PENDING = "PENDING";

    //共有中
    public static final String SHARING = "SHARING";

    //差戻し
    public static final String RETURNED = "RETURNED";

    private ReferralStatus() {
    	
    }

}
