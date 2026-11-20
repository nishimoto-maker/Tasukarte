package com.tasukarte.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.staff.dto.StaffListDto;
import com.tasukarte.staff.entity.Staff;
import com.tasukarte.staff.repository.StaffRepository;

// 職員情報に関する処理を担当するService
@Service
public class StaffService {
	// StaffRepositoryを利用するための変数
    private final StaffRepository staffRepository;

    // コンストラクタでStaffRepositoryを受け取る
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }
    
 // 職員を検索するメソッド
    public List<StaffListDto> searchStaff(
            String name,
            Short gender,
            Integer positionId,
            Integer roleId,
            Boolean isDeleted) {
    	
    	// ログイン中の認証情報を取得
    	Authentication authentication =
    	        SecurityContextHolder.getContext().getAuthentication();

    	// ログイン中のユーザー情報を取得
    	LoginUser loginUser =
    	        (LoginUser) authentication.getPrincipal();

    	// ログイン中の職員の病院IDを取得
    	Integer hospitalId = loginUser.getHospitalId();
    	
    	// Repositoryで作成した検索条件をspecificationに格納
    	Specification<Staff> specification =
    	        StaffRepository.search(
    	                hospitalId,
    	                name,
    	                gender,
    	                positionId,
    	                roleId,
    	                isDeleted
    	        );
    	// 検索条件に一致する職員情報を取得する
    	List<Staff> staffList = staffRepository.findAll(specification);
    	// 画面表示用DTOを格納するリスト
    	List<StaffListDto> dtoList = new ArrayList<>();

    	// 職員情報を1件ずつ取り出す
    	for (Staff staff : staffList) {

    	    // 職員情報から必要な項目だけをDTOに詰め替える
    	    StaffListDto dto = new StaffListDto(
    	            staff.getNameKanji(),
    	            staff.getGender(),
    	            staff.getHospitalPosition().getPositionName()
    	    );

    	    // DTOをリストに追加
    	    dtoList.add(dto);
    	}
    	// DTOのリストをControllerに返す
        return dtoList;
    }
}