package com.tasukarte.message.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table (name = "message_positions")

public class MessagePositions {
	
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
	private Integer messageId;
	
	//役職ID
	//必須、カラム定義
	@NotNull
	@Column(name = "position_id", nullable = false)
	private Integer positionId;

	
	//getter/setter
	public Integer getRecipientPositionId() {
		return recipientPositionId;
	}

	public void setRecipientPositionId(Integer recipientPositionId) {
		this.recipientPositionId = recipientPositionId;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
}
