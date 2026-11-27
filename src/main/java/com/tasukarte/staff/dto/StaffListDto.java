package com.tasukarte.staff.dto;

// 職員一覧画面に表示する情報をまとめるDTO
public class StaffListDto {

	// 職員ID
	private Integer staffId;

	// 職員名（漢字）
	private String nameKanji;

	// 性別
	private short gender;

	// 役職名
	private String positionName;

	public StaffListDto(
			Integer staffId,
			String nameKanji,
			short gender,
			String positionName) {

		this.staffId = staffId;
		this.nameKanji = nameKanji;
		this.gender = gender;
		this.positionName = positionName;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public String getNameKanji() {
		return nameKanji;
	}

	public short getGender() {
		return gender;
	}

	public String getPositionName() {
		return positionName;
	}
}