package com.tasukarte.prescription.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasukarte.prescription.entity.Medication;

@Repository
public interface MedicationRepository
        extends JpaRepository<Medication, Integer> {

    // 診療記録IDから処方薬を取得
    List<Medication> findAllByMedicalRecordId(Integer medicalRecordId);
}