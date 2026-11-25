package com.tasukarte.referral;

public final class ReferralStatus {

    // 下書き
    public static final String DRAFT = "DRAFT";

    // 差し戻し
    public static final String RETURNED = "RETURNED";

    // 送信前（登録済み）
    public static final String READY_TO_SEND = "READY_TO_SEND";

    // 承認待ち（送信済み）
    public static final String PENDING = "PENDING";

    // 共有中（承認済み）
    public static final String SHARED = "SHARED";

    // インスタンス化を防止
    private ReferralStatus() {
    }
}
