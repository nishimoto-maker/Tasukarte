package com.tasukarte.message.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
	
	
	//getter/setter
	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getSenderHospitalId() {
		return senderHospitalId;
	}

	public void setSenderHospitalId(Integer senderHospitalId) {
		this.senderHospitalId = senderHospitalId;
	}

	public Integer getRecipientHospitalId() {
		return recipientHospitalId;
	}

	public void setRecipientHospitalId(Integer recipientHospitalId) {
		this.recipientHospitalId = recipientHospitalId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMainText() {
		return mainText;
	}

	public void setMainText(String mainText) {
		this.mainText = mainText;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public LocalDateTime getSendAt() {
		return sendAt;
	}

	public void setSendAt(LocalDateTime sendAt) {
		this.sendAt = sendAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}
	
	
	
}
