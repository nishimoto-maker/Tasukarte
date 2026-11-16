package com.tasukarte.hospital.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

//複合主キーを管理するクラス
//JPAの複合主キー用クラスに必要なSerializableインターフェースを実装
//シリアライズ・・・オブジェクトの状態を保存・転送できる形式に変換
@Embeddable
public class HospitalBedTypeId implements Serializable {

	//病院ID
    private Integer hospitalId;

    //病床種別ID
    private Integer bedTypeId;
    
    // equalsメソッドをオーバーライド
    // 2つのオブジェクトが同じ主キーの組み合わせかどうかを比較する
    //どのようなオブジェクトでも比較対象として受け取れるように引数をObject型に設定
    @Override
    public boolean equals(Object o) {
    	
    	// 同じオブジェクトならtrueを返す
        if (this == o) return true;
        
        //instanceofで指定したクラスのインスタンスかどうか判断
        // 比較対象がHospitalBedTypeId型でなければfalseを返す
        if (!(o instanceof HospitalBedTypeId)) return false;
        
        // 比較対象をHospitalBedTypeId型に変換しthatに代入
        HospitalBedTypeId that = (HospitalBedTypeId) o;
        
        //現在のオブジェクトと比較対象のオブジェクトが一致しているか
        // 病院IDと病床種別IDの両方が一致すればtrueを返す
        return Objects.equals(hospitalId, that.hospitalId)
                && Objects.equals(bedTypeId, that.bedTypeId);
    }
    
    // hashCodeメソッドをオーバーライド
    // 主キーの組み合わせからハッシュ値を生成する
    @Override
    public int hashCode() {
        return Objects.hash(hospitalId, bedTypeId);
    }

    public HospitalBedTypeId() {
    }
    
    // 病院IDと病床種別IDを受け取るコンストラクタ
    public HospitalBedTypeId(Integer hospitalId, Integer bedTypeId) {
        this.hospitalId = hospitalId;
        this.bedTypeId = bedTypeId;
    }

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Integer getBedTypeId() {
		return bedTypeId;
	}

	public void setBedTypeId(Integer bedTypeId) {
		this.bedTypeId = bedTypeId;
	}
}