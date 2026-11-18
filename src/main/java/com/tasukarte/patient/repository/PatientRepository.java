package com.tasukarte.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.patient.entity.Patient;

public interface PatientRepository 
        extends JpaRepository<Patient, Integer>{

}
