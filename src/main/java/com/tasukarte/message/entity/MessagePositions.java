package com.tasukarte.message.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
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
}
