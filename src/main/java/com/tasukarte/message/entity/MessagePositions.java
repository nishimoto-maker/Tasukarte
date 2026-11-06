package com.tasukarte.message.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "message_positions")

public class MessagePositions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipient_position_id", nullable = false)
	private Integer recipientPositionId;
	
	@Column(name ="message_id", nullable = false)
	private Integer messageId;
	
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
