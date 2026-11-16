package com.tasukarte.prescription.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasukarte.prescription.entity.Medication;
import com.tasukarte.prescription.repository.MedicationRepository;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;

    // Repositoryを受け取る
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    // ① 処方薬の新規登録
    @Transactional
    public Medication createMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    // ② 処方薬の情報編集
    @Transactional
    public Medication updateMedication(
            Integer medicationId, Medication updatedMedication) {

        Medication existingMedication =
                medicationRepository.findById(medicationId)
                .orElseThrow(() ->
                    new IllegalArgumentException("処方薬が見つかりません。"));

        existingMedication.setMedicationName(
                updatedMedication.getMedicationName());

        existingMedication.setUsageDosage(
                updatedMedication.getUsageDosage());

        existingMedication.setPrescriptionDays(
                updatedMedication.getPrescriptionDays());

        return medicationRepository.save(existingMedication);
    }

    // ③ 診療記録IDから処方薬を取得
    @Transactional(readOnly = true)
    public List<Medication> getMedications(Integer medicalRecordId) {
        return medicationRepository.findAllByMedicalRecordId(medicalRecordId);
    }

    // ④ 処方薬IDから詳細を取得
    @Transactional(readOnly = true)
    public Medication getMedication(Integer medicationId) {
        return medicationRepository.findById(medicationId)
                .orElseThrow(() ->
                    new IllegalArgumentException("処方薬が見つかりません。"));
    }
}
