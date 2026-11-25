package com.tasukarte.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tasukarte.common.security.LoginUser;
import com.tasukarte.hospital.entity.Hospital;
import com.tasukarte.hospital.repository.HospitalRepository;
import com.tasukarte.staff.dto.MyPageDto;
import com.tasukarte.staff.dto.StaffDetailDto;
import com.tasukarte.staff.dto.StaffListDto;
import com.tasukarte.staff.dto.StaffRegisterDto;
import com.tasukarte.staff.entity.HospitalPosition;
import com.tasukarte.staff.entity.HospitalRole;
import com.tasukarte.staff.entity.Staff;
import com.tasukarte.staff.repository.HospitalPositionRepository;
import com.tasukarte.staff.repository.HospitalRoleRepository;
import com.tasukarte.staff.repository.StaffPasswordRepository;
import com.tasukarte.staff.repository.StaffRepository;

// 職員情報に関する処理を担当するService
@Service
public class StaffService {
	// StaffRepositoryを利用するための変数
	private final StaffRepository staffRepository;

	// 役職情報を取得するRepository
	private final HospitalPositionRepository hospitalPositionRepository;

	// 権限情報を取得するRepository
	private final HospitalRoleRepository hospitalRoleRepository;

	// 病院情報を取得するRepository
	private final HospitalRepository hospitalRepository;

	// パスワード情報を保存するRepository
	private final StaffPasswordRepository staffPasswordRepository;

	// コンストラクタでRepositoryを受け取る
	public StaffService(
			StaffRepository staffRepository,
			HospitalPositionRepository hospitalPositionRepository,
			HospitalRoleRepository hospitalRoleRepository,
			HospitalRepository hospitalRepository,
			StaffPasswordRepository staffPasswordRepository) {

		this.staffRepository = staffRepository;
		this.hospitalPositionRepository = hospitalPositionRepository;
		this.hospitalRoleRepository = hospitalRoleRepository;
		this.hospitalRepository = hospitalRepository;
		this.staffPasswordRepository = staffPasswordRepository;
	}

	// 職員を検索するメソッド
	public List<StaffListDto> searchStaff(
			String name,
			Short gender,
			Integer positionId,
			Integer roleId,
			Boolean isDeleted) {

		// ログイン中の認証情報を取得
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// ログイン中のユーザー情報を取得
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();

		// ログイン中の職員の病院IDを取得
		Integer hospitalId = loginUser.getHospitalId();

		// Repositoryのsearchメソッドで検索条件を作成する
		Specification<Staff> specification = StaffRepository.search(
				hospitalId,
				name,
				gender,
				positionId,
				roleId,
				isDeleted);
		// 作成した検索条件を使って職員情報を検索する
		List<Staff> staffList = staffRepository.findAll(specification);
		// 画面表示用DTOを格納するリスト
		List<StaffListDto> dtoList = new ArrayList<>();

		// 職員情報を1件ずつ取り出す
		for (Staff staff : staffList) {

			// 職員情報から必要な項目だけをDTOに詰め替える
			StaffListDto dto = new StaffListDto(
					staff.getStaffId(),
					staff.getNameKanji(),
					staff.getGender(),
					staff.getHospitalPosition().getPositionName());

			// DTOをリストに追加
			dtoList.add(dto);
		}
		// DTOのリストをControllerに返す
		return dtoList;
	}

	// ログイン中の職員本人のマイページ情報を取得する
	public MyPageDto getMyPage() {

		// ログイン中の認証情報を取得
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// ログイン中のユーザー情報を取得
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();

		// ログイン中の職員IDを取得
		Integer staffId = loginUser.getStaffId();

		// ログイン中の職員IDを使ってRepositoryから本人の情報を取得
		Staff staff = staffRepository.findMyPageByStaffId(staffId)
				// Optionalに検索結果が存在しない場合は例外を発生させる
				.orElseThrow(() -> new IllegalStateException(
						"ログイン中の職員情報が見つかりません。"));

		// 本人の職員情報からマイページに必要な項目をDTOに詰め替える
		MyPageDto dto = new MyPageDto(
				staff.getNameKanji(),
				staff.getNameKana(),
				staff.getGender(),
				staff.getAddress(),
				staff.getPostCode(),
				staff.getPhoneNumber(),
				staff.getEmailAddress(),
				staff.getHospitalPosition().getPositionName(),
				staff.getHospitalRole().getRoleName());

		// DTOをControllerに返す
		return dto;
	}

	// 職員情報確認画面に表示する情報を取得する
	public StaffDetailDto getStaffDetail(Integer staffId) {

		// ログイン中の認証情報を取得
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// ログイン中のユーザー情報を取得
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();

		// ログイン中の職員の病院IDを取得
		Integer hospitalId = loginUser.getHospitalId();

		// 職員IDと病院IDを使って職員情報を取得
		Staff staff = staffRepository.findStaffDetailByStaffId(
				staffId, hospitalId)
				.orElseThrow(() -> new IllegalStateException(
						"職員情報が見つかりません。"));

		// 職員情報をDTOに詰め替える
		StaffDetailDto dto = new StaffDetailDto(
				staff.getStaffId(),
				staff.getNameKanji(),
				staff.getNameKana(),
				staff.getGender(),
				staff.getAddress(),
				staff.getPostCode(),
				staff.getPhoneNumber(),
				staff.getEmailAddress(),
				staff.getHospitalPosition().getPositionName(),
				staff.getHospitalRole().getRoleName(),
				staff.getIsDeleted());

		return dto;
	}

	// 役職一覧を取得する
	public List<HospitalPosition> getPositionList() {

		// Repositoryから役職情報をすべて取得する
		return hospitalPositionRepository.findAll();
	}

	// 権限一覧を取得する
	public List<HospitalRole> getRoleList() {

		// Repositoryから権限情報をすべて取得する
		return hospitalRoleRepository.findAll();
	}

	// 職員情報を登録する
	public Staff createStaff(
	        StaffRegisterDto dto,
	        Integer hospitalId) {
		
		// メールアドレスが既に登録されているか確認する
		if (staffRepository.existsByEmailAddress(dto.getEmailAddress())) {
		    throw new IllegalArgumentException(
		            "このメールアドレスは既に登録されています。");
		}

		// 病院情報を取得する
		Hospital hospital = hospitalRepository.findById(hospitalId)
				.orElseThrow(() -> new IllegalArgumentException(
						"病院情報が見つかりません。"));

		// 役職情報を取得する
		HospitalPosition position = hospitalPositionRepository.findById(dto.getPositionId())
				.orElseThrow(() -> new IllegalArgumentException(
						"役職情報が見つかりません。"));

		// 権限情報を取得する
		HospitalRole role = hospitalRoleRepository.findById(dto.getRoleId())
				.orElseThrow(() -> new IllegalArgumentException(
						"権限情報が見つかりません。"));

		// Staffエンティティを作成する
		Staff staff = new Staff();

		// 病院・役職・権限を設定する
		staff.setHospital(hospital);
		staff.setHospitalPosition(position);
		staff.setHospitalRole(role);

		// 入力された職員情報を設定する
		staff.setNameKanji(dto.getNameKanji());
		staff.setNameKana(dto.getNameKana());
		staff.setGender(dto.getGender());
		staff.setAddress(dto.getAddress());
		staff.setPostCode(dto.getPostCode());
		staff.setPhoneNumber(dto.getPhoneNumber());
		staff.setEmailAddress(dto.getEmailAddress());

		// 新規登録なので削除フラグをfalseにする
		staff.setIsDeleted(false);

		return staff;
	}
	
	// メールアドレスが既に登録されているか確認する
	public boolean isEmailAddressRegistered(String emailAddress) {
	    return staffRepository.existsByEmailAddress(emailAddress);
	}
}