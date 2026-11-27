package com.tasukarte.staff.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// 職員登録画面の入力情報をまとめるDTO
public class StaffRegisterDto {

	// 職員名（漢字）
	// @NotBlank：null、空文字、空白だけの入力を禁止する
	// @Size：文字数を最大50文字に制限する
	@NotBlank(message = "氏名（漢字）は必須です")
	@Size(max = 50, message = "氏名（漢字）は50文字以内で入力してください")
	private String nameKanji;

	// 職員名（カナ）
	@NotBlank(message = "氏名（カナ）は必須です")
	@Size(max = 50, message = "氏名（カナ）は50文字以内で入力してください")
	private String nameKana;

	// 性別（0：その他、1：男性、2：女性）
	// Short型：整数を扱うラッパークラス
	// @NotNull：値がnullであることを禁止する
	// @Min、@Max：入力できる数値の範囲を0～2に制限する
	@NotNull(message = "性別を選択してください")
	@Min(value = 0, message = "性別の値が不正です")
	@Max(value = 2, message = "性別の値が不正です")
	private Short gender;

	// 住所
	@NotBlank(message = "住所は必須です")
	@Size(max = 255, message = "住所は255文字以内で入力してください")
	private String address;

	// 郵便番号
	@NotBlank(message = "郵便番号は必須です")
	@Size(max = 7, message = "郵便番号は7文字以内で入力してください")
	private String postCode;

	// 電話番号
	@NotBlank(message = "電話番号は必須です")
	@Size(max = 20, message = "電話番号は20文字以内で入力してください")
	private String phoneNumber;

	// メールアドレス
	// @Email：メールアドレスとして適切な形式かを検証する
	@NotBlank(message = "メールアドレスは必須です")
	@Email(message = "正しいメールアドレスを入力してください")
	@Size(max = 254, message = "メールアドレスは254文字以内で入力してください")
	private String emailAddress;

	// 役職ID
	@NotNull(message = "役職を選択してください")
	private Integer positionId;

	// 権限ID
	@NotNull(message = "権限を選択してください")
	private Integer roleId;

	// 仮パスワード
	@NotBlank(message = "仮パスワードは必須です")
	private String temporaryPassword;

	// 引数なしコンストラクタ
	public StaffRegisterDto() {
	}

	// Getter・Setter
	public String getNameKanji() {
		return nameKanji;
	}

	public void setNameKanji(String nameKanji) {
		this.nameKanji = nameKanji;
	}

	public String getNameKana() {
		return nameKana;
	}

	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}

	public Short getGender() {
		return gender;
	}

	public void setGender(Short gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getTemporaryPassword() {
		return temporaryPassword;
	}

	public void setTemporaryPassword(String temporaryPassword) {
		this.temporaryPassword = temporaryPassword;
	}
}