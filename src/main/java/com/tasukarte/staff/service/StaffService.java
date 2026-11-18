package com.tasukarte.staff.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tasukarte.common.security.LoginUser;
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
    public List<Staff> searchStaff(
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
    	
    	// 検索条件を作成する
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
    	// findAll() 条件に一致するデータをすべて取得する
    	// 検索を実行して結果を返す
    	return staffRepository.findAll(specification);
    }
}