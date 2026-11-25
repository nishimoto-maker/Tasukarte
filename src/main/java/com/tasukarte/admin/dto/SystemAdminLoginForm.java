package com.tasukarte.admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/* システム管理者ログイン画面用DTO */
//@Getter
//@Setter
public class SystemAdminLoginForm {

    /* メールアドレス */
    @NotBlank
    @Email
    private String adminMail;

    /* パスワード */
    @NotBlank
    private String password;

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
    
}