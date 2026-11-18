package com.tasukarte.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tasukarte.hospital.entity.Hospital;
import com.tasukarte.hospital.repository.HospitalRepository;

// 病院情報に関する処理を担当するService
@Service
public class HospitalService {

    // HospitalRepositoryを利用するための変数
    private final HospitalRepository hospitalRepository;

    // HospitalRepositoryを受け取るコンストラクタ
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    // 病院情報を全件取得する
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }
}