package com.tasukarte.prescription.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasukarte.prescription.entity.Prescription;
import com.tasukarte.prescription.repository.PrescriptionRepository;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    // Repositoryを受け取る
    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    // ① 処方箋の新規作成
    @Transactional
    public Prescription createPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    // ② 処方箋情報の編集
    @Transactional
    public Prescription updatePrescription(
            Integer medicalRecordId, Prescription updatedPrescription) {

        Prescription existingPrescription =
                prescriptionRepository.findById(medicalRecordId)
                .orElseThrow(() ->
                    new IllegalArgumentException("処方箋が見つかりません。"));

        existingPrescription.setRemarks(updatedPrescription.getRemarks());
        existingPrescription.setIssueAt(updatedPrescription.getIssueAt());
        existingPrescription.setUpdatedAt(updatedPrescription.getUpdatedAt());

        return prescriptionRepository.save(existingPrescription);
    }

    // ③ 処方箋の詳細取得
    @Transactional(readOnly = true)
    public Optional<Prescription> getPrescription(Integer medicalRecordId) {
        return prescriptionRepository.findById(medicalRecordId);
    }
}