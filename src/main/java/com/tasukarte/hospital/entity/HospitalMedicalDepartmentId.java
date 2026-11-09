package com.tasukarte.hospital.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

//複合主キーを管理するクラス
//JPAの複合主キー用クラスに必要なSerializableインターフェースを実装
//シリアライズ・・・オブジェクトの状態を保存・転送できる形式に変換
@Embeddable
public class HospitalMedicalDepartmentId implements Serializable {

	//病院ID
    private Integer hospitalId;

    //診療科ID
    private Integer medicalDepartmentId;
    
    public HospitalMedicalDepartmentId() {
    }

    // 病院IDと診療科IDを受け取り、複合主キーを設定するコンストラクタ
    public HospitalMedicalDepartmentId(Integer hospitalId, Integer medicalDepartmentId) {
        this.hospitalId = hospitalId;
        this.medicalDepartmentId = medicalDepartmentId;
    }
    
    // equalsメソッドをオーバーライド
    // 2つのオブジェクトが同じ主キーの組み合わせかどうかを比較する
    //どのようなオブジェクトでも比較対象として受け取れるように引数をObject型に設定
    @Override
    public boolean equals(Object o) {
    	
    	// 同じオブジェクトならtrueを返す
        if (this == o) return true;
        
        //instanceofで指定したクラスのインスタンスかどうか判断
        // 比較対象がHospitalMedicalDepartmentId型でなければfalseを返す
        if (!(o instanceof HospitalMedicalDepartmentId)) return false;
        
        // 比較対象をHospitalMedicalDepartmentId型に変換しthatに代入
        HospitalMedicalDepartmentId that = (HospitalMedicalDepartmentId) o;
        
        //現在のオブジェクトと比較対象のオブジェクトが一致しているか
        // 病院IDと診療科IDの両方が一致すればtrueを返す
        return Objects.equals(hospitalId, that.hospitalId)
                && Objects.equals(medicalDepartmentId, that.medicalDepartmentId);
    }
    
    // hashCodeメソッドをオーバーライド
    // 主キーの組み合わせからハッシュ値を生成する
    @Override
    public int hashCode() {
        return Objects.hash(hospitalId, medicalDepartmentId);
    }

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Integer getMedicalDepartmentId() {
		return medicalDepartmentId;
	}

	public void setMedicalDepartmentId(Integer medicalDepartmentId) {
		this.medicalDepartmentId = medicalDepartmentId;
	}
}