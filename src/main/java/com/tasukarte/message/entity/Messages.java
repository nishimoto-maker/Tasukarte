package com.tasukarte.message.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "messages")
public class Messages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id", nullable = false)
	private Integer messageId;
	
	@Column(name = "sender_hospital_id", nullable = false)
	private Integer senderHospitalId;
	
	@Column(name = "recipient_hospital_id")
	private Integer recipientHospitalId;
	
	@Column(name = "staff_id", nullable = false)
	private Integer staffId;
	
	@Column(name = "subject", nullable = false,length = 200)
	private String subject;
	
	@Column(name = "main_text")
	private String mainText;
	
	@Column(name = "state", nullable = false)
	private Short state = 0;
	
	@Column(name = "send_at")
	private LocalDateTime sendAt;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "update_at", nullable = false)
	private LocalDateTime updateAt;
	
	
	
}
