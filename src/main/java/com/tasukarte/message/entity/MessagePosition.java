package com.tasukarte.message.entity;

import com.tasukarte.staff.entity.HospitalPosition;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table (name = "message_positions")

public class MessagePosition {
	
	//宛先役職ID
	//主キー、自動連番、カラム定義
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipient_position_id", nullable = false)
	private Integer recipientPositionId;
	
	//メッセージID
	//必須、カラム定義
	@NotNull
	@ManyToOne
	@JoinColumn(name ="message_id", nullable = false)
	private Message message;
	
	//役職ID
	//必須、カラム定義
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "position_id", nullable = false)
	private HospitalPosition position;

	
	//getter/setter
	public Integer getRecipientPositionId() {
		return recipientPositionId;
	}

	public void setRecipientPositionId(Integer recipientPositionId) {
		this.recipientPositionId = recipientPositionId;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public HospitalPosition getPosition() {
		return position;
	}

	public void setPosition(HospitalPosition position) {
		this.position = position;
	}
}
