package com.tasukarte.hospital.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "bed_types")
public class BedType {
	//idを主キーにする。
	@Id
	//idを自動生成             //PostgreSQLのIDENTITYによる自動採番を利用
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bed_type_id")
	//病床種別ID
	private Integer bedTypeId;
	
	//病床種別名
	@NotBlank
	@Column(name = "bed_type_name", nullable = false)
	private String bedTypeName;
	
	public BedType() {
	}
	
	public Integer getBedTypeId() {
		return bedTypeId;
	}
	public void setBedTypeId(Integer bedTypeId) {
		this.bedTypeId = bedTypeId;
	}
	
	public String getBedTypeName() {
		return bedTypeName;
	}
	public void setBedTypeName(String bedTypeName) {
		this.bedTypeName = bedTypeName;
	}
}