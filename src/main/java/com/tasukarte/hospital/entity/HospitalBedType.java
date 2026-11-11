package com.tasukarte.hospital.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "hospital_bed_type")
public class HospitalBedType {
	// 複合主キーを管理する
    @EmbeddedId
    private HospitalBedTypeId id;

    // 病院Entityとの関連付け
    // 複合主キーのhospitalIdと対応させる
    @MapsId("hospitalId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalManagement hospital;

    // 病床種別Entityとの関連付け
    // 複合主キーのbedTypeIdと対応させる
    @MapsId("bedTypeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bed_type_id", nullable = false)
    private BedType bedType;

    // 許可病床数
    @Column(name = "authorized_bed_count", nullable = false)
    private Integer authorizedBedCount = 0;

    // 稼働病床数
    @Column(name = "operational_bed_count", nullable = false)
    private Integer operationalBedCount = 0;
	
	public HospitalBedType() {
	}

	public HospitalBedTypeId getId() {
		return id;
	}

	public void setId(HospitalBedTypeId id) {
		this.id = id;
	}

	public HospitalManagement getHospital() {
		return hospital;
	}

	public void setHospital(HospitalManagement hospital) {
		this.hospital = hospital;
	}

	public BedType getBedType() {
		return bedType;
	}

	public void setBedType(BedType bedType) {
		this.bedType = bedType;
	}

	public Integer getAuthorizedBedCount() {
		return authorizedBedCount;
	}

	public void setAuthorizedBedCount(Integer authorizedBedCount) {
		this.authorizedBedCount = authorizedBedCount;
	}

	public Integer getOperationalBedCount() {
		return operationalBedCount;
	}

	public void setOperationalBedCount(Integer operationalBedCount) {
		this.operationalBedCount = operationalBedCount;
	}
}