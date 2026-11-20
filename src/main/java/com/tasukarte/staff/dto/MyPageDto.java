package com.tasukarte.staff.dto;

// マイページに表示する職員情報をまとめるDTO
public class MyPageDto {

    // 氏名（漢字）
    private String nameKanji;

    // 氏名（カナ）
    private String nameKana;

    // 性別
    private short gender;

    // 住所
    private String address;

    // 郵便番号
    private String postCode;

    // 電話番号
    private String phoneNumber;

    // メールアドレス
    private String emailAddress;

    // 役職名
    private String positionName;

    // 権限名
    private String roleName;
    
    // コンストラクタ
    public MyPageDto(
            String nameKanji,
            String nameKana,
            short gender,
            String address,
            String postCode,
            String phoneNumber,
            String emailAddress,
            String positionName,
            String roleName) {

    	// 受け取った値をDTOのフィールドに保存する
        this.nameKanji = nameKanji;
        this.nameKana = nameKana;
        this.gender = gender;
        this.address = address;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.positionName = positionName;
        this.roleName = roleName;
    }

    // Getter
    public String getNameKanji() {
        return nameKanji;
    }

    public String getNameKana() {
        return nameKana;
    }

    public short getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPositionName() {
        return positionName;
    }

    public String getRoleName() {
        return roleName;
    }
}