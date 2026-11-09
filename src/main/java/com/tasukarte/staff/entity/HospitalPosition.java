package com.tasukarte.staff.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="hospital_positions")
public class HospitalPosition {
	//idを主キーにする。
	@Id
	//idを自動生成             //PostgreSQLのIDENTITYによる自動採番を利用
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//役職ID
	@Column(name = "position_id")
	private Integer positionId;
	
	//役職名
	@Column(name="position_name", length = 4, nullable = false)
	private String positionName;
	
	public HospitalPosition() {
	}
	
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
}