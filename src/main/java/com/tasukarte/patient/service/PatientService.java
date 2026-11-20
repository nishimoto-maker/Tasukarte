package com.tasukarte.patient.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tasukarte.patient.dto.PatientListDTO;
import com.tasukarte.patient.dto.PatientSearchDTO;
import com.tasukarte.patient.entity.Patient;
import com.tasukarte.patient.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    
    //性別のをShortからStringに変換
    private String convertGender(Short gender) {
        if (gender == 1) {
            return "男";
        } else if (gender == 2) {
            return "女";
        }

        return "";
    }
    
    //誕生日の計算生。年月日を年齢に変換
    private Integer calculateAge(LocalDate birthDay) {
    	
        return Period.between(birthDay, LocalDate.now()).getYears();
    }
    
    //登録日を日付だけに
    private LocalDate convertCreatedAt(LocalDateTime createAt) {

        return createAt.toLocalDate();
    }
    
    // 患者情報一覧の取得
    public List<PatientListDTO> searchPatients(PatientSearchDTO searchDto) {
    	// 検索条件がすべて未入力か確認
    	boolean noConditions =
    	        (searchDto.getName() == null || searchDto.getName().isBlank())
    	        && searchDto.getGender() == null
    	        && searchDto.getAge() == null
    	        && searchDto.getStartDate() == null
    	        && searchDto.getEndDate() == null;

    	// すべて未入力なら空のリストを返す
    	if (noConditions) {
    	    return new ArrayList<>();
    	}
    	
    	//patientRepositoryから患者情報を取得
        List<Patient> patients = patientRepository.findAll();
        
        //DTOを格納する空のリストを作成
        List<PatientListDTO> patientList = new ArrayList<>();
        
        //patientsに格納されている患者情報を、1人ずつ取り出して処理
        for (Patient patient : patients) {
        	
        	//DTOに変換
            PatientListDTO dto = convertToPatientListDTO(patient);
            
            //変換したDTOをリストに追加
            patientList.add(dto);
        }
        //リストを繰り返す
        return patientList;
    }
    
    private PatientListDTO convertToPatientListDTO(Patient patient) {

        PatientListDTO dto = new PatientListDTO();

        dto.setName(patient.getName());
        dto.setGender(convertGender(patient.getGender()));
        dto.setAge(calculateAge(patient.getBirthDay()));
        dto.setCreatedAt(convertCreatedAt(patient.getCreateAt()));

        return dto;
    }
}