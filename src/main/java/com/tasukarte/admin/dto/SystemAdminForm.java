package com.tasukarte.admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/* システム管理者登録画面用DTO */
//@Getter
//@Setter
public class SystemAdminForm {

    /* 管理者氏名 */
    @NotBlank
    private String adminName;

    /* 管理者氏名（カナ） */
    @NotBlank
    private String adminNameKana;

    /* メールアドレス */
    @NotBlank
    @Email
    private String adminMail;

    /* パスワード */
    @NotBlank
    private String password;

    /* パスワード確認 */
    @NotBlank
    private String confirmPassword;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminNameKana() {
		return adminNameKana;
	}

	public void setAdminNameKana(String adminNameKana) {
		this.adminNameKana = adminNameKana;
	}

	public String getAdminMail() {
		return adminMail;
	}

	public void setAdminMail(String adminMail) {
		this.adminMail = adminMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}