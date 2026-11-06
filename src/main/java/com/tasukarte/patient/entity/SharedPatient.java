
package com.tasukarte.patient.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "shared_patients")
public class SharedPatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "share_patient_id", nullable = false)
    private Integer sharePatientId;

    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    @Column(name = "shared_by_hospital_id", nullable = false)
    private Integer sharedByHospitalId;

    @Column(name = "share_with_hospital_id", nullable = false)
    private Integer shareWithHospitalId;

    @Column(name = "referral_id", nullable = false)
    private Integer referralId;

    @Column(name = "shared_start_at", nullable = false)
    private LocalDate sharedStartAt;

    @Column(name = "shared_end_at")
    private LocalDate sharedEndAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

}
