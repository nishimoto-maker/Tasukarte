package com.tasukarte.message.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "message_files")

public class MessageFiles {
	
	//添付ファイルID
	//主キー、自動連番、カラム定義
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attachment_file_id", nullable = false)
	private Integer attachmentFileId;
	
	//メッセージID
	//必須、カラム定義
	@ManyToOne
	@JoinColumn(name = "message_id", nullable = false)
	private Integer messageId;
	
	//ファイル名
	//必須、255文字まで、カラム定義
	@Column(name = "file_name", nullable = false, length = 255)
	private String fileName;
	
	//保存ファイル名
	//必須、255文字まで、カラム定義
	@Column(name = "saved_file_name", nullable = false, length = 255)
	private String savedFileName;
	
	//ファイルパス
	//必須、500文字まで、カラム定義
	@Column(name = "file_path", nullable = false, length = 500)
	private String filePath;
	
	//ファイルサイズ
	//必須、カラム定義
	@Column(name = "file_size", nullable = false)
	private  Long fileSize;
	
	//登録日時
	//必須、カラム定義
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	

	//getter/setter
	public Integer getAttachmentFileId() {
		return attachmentFileId;
	}

	public void setAttachmentFileId(Integer attachmentFileId) {
		this.attachmentFileId = attachmentFileId;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSavedFileName() {
		return savedFileName;
	}

	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
}
