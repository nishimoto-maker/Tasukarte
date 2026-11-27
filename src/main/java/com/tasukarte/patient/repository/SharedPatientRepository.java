package com.tasukarte.patient.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.patient.entity.SharedPatient;
 
public interface SharedPatientRepository
        extends JpaRepository<SharedPatient, Integer> {
 
}