package com.tasukarte.patient.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.hospital.repository.HospitalRepository;
import com.tasukarte.patient.dto.AllergieRegisterDTO;
import com.tasukarte.patient.dto.PatientListDTO;
import com.tasukarte.patient.dto.PatientRegisterDTO;
import com.tasukarte.patient.dto.PatientSearchDTO;
import com.tasukarte.patient.entity.Allergie;
import com.tasukarte.patient.entity.Patient;
import com.tasukarte.patient.repository.PatientRepository;
import com.tasukarte.hospital.entity.Hospital;

@Service
public class PatientService {
	
	//フィールドを作成
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    public PatientService(PatientRepository patientRepository,HospitalRepository hospitalRepository) {
    	this.patientRepository = patientRepository;
    	this.hospitalRepository = hospitalRepository;
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
    
    
    //患者情報の登録
    public void registerPatient(PatientRegisterDTO dto) {
    	
    	//現在ログインしているユーザーの認証情報を取得
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	//ログインユーザーの情報を取得
    	LoginUser loginUser = (LoginUser) authentication.getPrincipal();
    	
    	//ログインユーザーが所属する病院IDを取得
    	Integer hospitalId = loginUser.getHospitalId();
    	
    	//Patientを作成
    	Patient patient = new Patient();
    	
    	//病院IDから病院情報を取得
    	Hospital hospital = hospitalRepository.findById(hospitalId)
    	        .orElseThrow(() -> new RuntimeException("病院情報が見つかりません"));
    	
    	//患者情報に病院を設定
    	patient.setHospital(hospital);
    	
    	
    	//DTOの値をEntityに移す
    	patient.setName(dto.getName());
    	patient.setKata(dto.getKata());
    	patient.setBirthDay(dto.getBirthDay());
    	patient.setGender(dto.getGender());
    	patient.setPhoneNumber(dto.getPhoneNumber());
    	patient.setEmailAddress(dto.getEmailAddress());
    	patient.setAddress(dto.getAddress());
    	patient.setPostCode(dto.getPostCode());
    	patient.setBloodType(dto.getBloodType());
    	patient.setRh(dto.getRh());
    	patient.setAllergy(dto.getAllergy());
    	patient.setWeight(dto.getWeight());
    	patient.setHeight(dto.getHeight());
    	
    	//アレルギーの有無の判別
    	if(Boolean.TRUE.equals(dto.getAllergy())) {
    		
    		//リストから1件ずつ取り出す(複数件の取得)
    		for(AllergieRegisterDTO allergyDTO : dto.getAllergies()) {
    			
    			Allergie allergie = new Allergie();
    			
    			allergie.setAllergyType(allergyDTO.getAllergyType());
    			allergie.setAllergySymptom(allergyDTO.getAllergySymptom());
    			
    			allergie.setPatient(patient);
    			
    	        //Patientのアレルギーリストに追加
    	        patient.getAllergies().add(allergie);
    			
    		}
    	}
    	
    }
    
}