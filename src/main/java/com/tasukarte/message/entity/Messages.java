package com.tasukarte.message.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "messages")
public class Messages {
	
	//メッセージID
	//主キー、自動連番、カラム定義
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id", nullable = false)
	private Integer messageId;
	
	//送信元病院ID
	//必須、カラム定義
	@NotNull
	@Column(name = "sender_hospital_id", nullable = false)
	private Integer senderHospitalId;
	
	//送信先病院ID
	//カラム定義
	@NotNull
	@Column(name = "recipient_hospital_id")
	private Integer recipientHospitalId;
	
	//作成者ID
	//必須、カラム定義
	@NotNull
	@Column(name = "staff_id", nullable = false)
	private Integer staffId;
	
	//件名
	//200字まで、カラム定義
	@NotBlank
	@Size(max=200)
	@Column(name = "subject", nullable = false,length = 200)
	private String subject;
	
	//本文
	//カラム定義
	@NotBlank
	@Column(name = "main_text")
	private String mainText;
	
	//状態
	//必須、初期値0、カラム定義
	@NotNull
	@Column(name = "state", nullable = false)
	private Short state = 0;
	
	//送信日時
	//カラム定義
	@NotNull
	@Column(name = "send_at")
	private LocalDateTime sendAt;
	
	//作成日時
	//必須、カラム定義
	@NotNull
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	//更新日時
	//必須、カラム定義
	@NotNull
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
