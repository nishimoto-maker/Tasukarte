package com.tasukarte.prescription.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasukarte.prescription.entity.Prescription;

@Repository
public interface PrescriptionRepository
        extends JpaRepository<Prescription, Integer> {

}
