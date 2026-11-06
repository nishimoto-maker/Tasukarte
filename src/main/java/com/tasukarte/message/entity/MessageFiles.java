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
@Table (name = "message_files")

public class MessageFiles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attachment_file_id", nullable = false)
	private Integer attachmentFileId;
	
	@Column(name = "message_id", nullable = false)
	private Integer messageId;
	
	@Column(name = "file_name", nullable = false, length = 255)
	private String fileName;
	
	@Column(name = "saved_file_name", nullable = false, length = 255)
	private String savedFileName;
	
	@Column(name = "file_path", nullable = false, length = 500)
	private String filePath;
	
	@Column(name = "file_size", nullable = false)
	private  Long fileSize;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
}
