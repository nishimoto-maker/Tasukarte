package com.tasukarte.medicalrecord.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="medical_records")
public class MedicalRecord {
	// 診療記録ID
	// 主キー、自動採番、カラム定義
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="medical_record_id")
	private Integer medicalRecordId;
	
	// 患者
	// 必須、患者テーブルから参照、カラム定義
	@ManyToOne
	@NotNull
	@JoinColumn(name="patient_id" nullable=false)
	private Patient patient;
	
	// 担当医師
	// 必須、職員テーブルから参照、カラム定義
	@ManyToOne
	@NotNull
	@JoinColumn(name="staff_id", nullable=false)
	private Staff staff;
	
	// 傷病名
	// 必須、100字まで、カラム定義
	@NotBlank
	@Size(max=100)
	@Column(name="desease_name", nullable=false, length=100)
	private String deseaseName;
	
	// 転帰
	// 10字まで、カラム定義
	@Size(max=10)
	@Column(name="outcome", length=10)
	private String outcome;
	
	// 転帰日
	// カラム定義
	@Column(name="outcome_date")
	private LocalDate outcomeDate;

	// 経過(S)
	// 必須、カラム定義
	@NotBlank
	@Column(name="progressS")
	private String progressS;
	
	// 経過(O)
	// 必須、カラム定義
	@NotBlank
	@Column(name="progressO")
	private String progressO;
	
	// 経過(A)
	// 必須、カラム定義
	@NotBlank
	@Column(name="progressA")
	private String progressA;
	
	// 経過(P)
	// 必須、カラム定義
	@NotBlank
	@Column(name="progressP")
	private String progressP;
	
	// 処置
	// 必須、カラム定義
	@NotBlank
	@Column(name="procedure")
	private String procedure;
	
	// 診察画像
	// この診療記録に紐づくものを配列で管理
	@OneToMany(mappedBy="medicalRecord")
	private List<Image> images;
	
	// 再診予定日
	// カラム定義
	@Column(name="follow_up_date")
	private LocalDate followUpDate;
	
	// 診療日
	// 必須、カラム定義
	// @PrePersistで自動取得
	@NotNull
	@Column(name="medical_record_date")
	private LocalDate medicalRecordDate;

	// 処方箋有無
	// カラム定義
	@Column(name="has_prescription")
	private boolean hasPrescription;

	// 以下各フィールドのgetter/setter
	public Integer getMedicalRecordId() {
		return medicalRecordId;
	}
	public void setMedicalRecordId(Integer medicalRecordId) {
		this.medicalRecordId = medicalRecordId;
	}

	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getDeseaseName() {
		return deseaseName;
	}
	public void setDeseaseName(String deseaseName) {
		this.deseaseName = deseaseName;
	}

	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public LocalDate getOutcomeDate() {
		return outcomeDate;
	}
	public void setOutcomeDate(LocalDate outcomeDate) {
		this.outcomeDate = outcomeDate;
	}

	public String getProgressS() {
		return progressS;
	}
	public void setProgressS(String progressS) {
		this.progressS = progressS;
	}

	public String getProgressO() {
		return progressO;
	}
	public void setProgressO(String progressO) {
		this.progressO = progressO;
	}

	public String getProgressA() {
		return progressA;
	}
	public void setProgressA(String progressA) {
		this.progressA = progressA;
	}

	public String getProgressP() {
		return progressP;
	}
	public void setProgressP(String progressP) {
		this.progressP = progressP;
	}

	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}

	public LocalDate getFollowUpDate() {
		return followUpDate;
	}
	public void setFollowUpDate(LocalDate followUpDate) {
		this.followUpDate = followUpDate;
	}

	public LocalDate getMedicalRecordDate() {
		return medicalRecordDate;
	}
	public void setMedicalRecordDate(LocalDate medicalRecordDate) {
		this.medicalRecordDate = medicalRecordDate;
	}

	public boolean isHasPrescription() {
		return hasPrescription;
	}
	public void setHasPrescription(boolean hasPrescription) {
		this.hasPrescription = hasPrescription;
	}
	// ここまで
	
	// 診療記録を登録する日時を自動取得し格納する
	@PrePersist
	public void prePersist() {
		medicalRecordDate = LocalDate.now();
	}
}
