package com.tasukarte.medicalrecord.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="images")
public class Image {
	// 画像ID
	// 主キー、自動採番、カラム定義
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="image_id")
	private Integer imageId;
	
	// 元ファイル名
	// 必須、255字まで、カラム定義
	@NotBlank
	@Size(max=255)
	@Column(name="original_file_name", nullable=false, length=255)
	private String originalFileName;
	
	// 保存ファイル名
	// 必須、255字まで、カラム定義
	@NotBlank
	@Size(max=255)
	@Column(name="stored_file_name", nullable=false, length=255)
	private String storedFileName;
	
	// ファイルパス
	// 必須、500字まで、カラム定義
	@NotBlank
	@Size(max=500)
	@Column(name="file_path", nullable=false, length=500)
	private String filePath;
	
	// 表示順
	// 必須、カラム定義
	@NotNull
	@Column(name="display_order", nullable=false)
	private int displayOrder;
	
	// 画像説明
	// カラム定義
	@Column(name="image_description")
	private String imageDescription;
	
	// 紐づける診療記録のID
	@ManyToOne
	@JoinColumn(name="medical_record_id", nullable=false)
	private MedicalRecord medicalRecord;

	// 以下、各フィールドのgetter/setter
	public Integer getImageId() {
		return imageId;
	}
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getStoredFileName() {
		return storedFileName;
	}
	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getImageDescription() {
		return imageDescription;
	}
	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}
	// 以上
}
