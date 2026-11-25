package com.tasukarte.staff.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tasukarte.staff.entity.Staff;

import jakarta.persistence.criteria.Predicate;

// staffsテーブルの操作を行うinterface
public interface StaffRepository
		extends JpaRepository<Staff, Integer>,
		JpaSpecificationExecutor<Staff> {
	//Specification JPAで動的な検索条件を組み立てるための仕組み
	// 受け取った検索条件をもとに、検索条件を作成するメソッド
	static Specification<Staff> search(
			Integer hospitalId,
			String name,
			Short gender,
			Integer positionId,
			Integer roleId,
			Boolean isDeleted) {

		// root 検索対象となるStaffエンティティの属性を参照する
		// query 検索全体の設定を扱う
		// cb 検索条件を作るための道具
		return (root, query, cb) -> {

			// 検索条件を複数保存するリスト
			List<Predicate> predicates = new ArrayList<>();

			// cb.equal() 2つの値が等しいことを条件として作成する
			// 病院IDが一致する条件
			// add() リストに要素を追加するメソッド
			predicates.add(
					cb.equal(
							// Staffが所属している病院のIDを取得する
							root.get("hospital").get("hospitalId"),
							// 検索条件として受け取った病院ID
							hospitalId));

			// cb.or() 複数条件のうち、どちらか一方が成立すればOK
			// cb.like() 文字列の部分一致検索を作成する
			// 漢字氏名またはカナ氏名のどちらかが部分一致する条件
			predicates.add(
					cb.or(
							// 漢字氏名を部分一致で検索
							cb.like(root.get("nameKanji"), "%" + name + "%"),
							// カナ氏名を部分一致で検索
							cb.like(root.get("nameKana"), "%" + name + "%")));

			// 性別が選択されている場合だけ条件を追加する
			if (gender != null) {
				predicates.add(
						cb.equal(
								// Staff自身が持つ性別を取得する
								root.get("gender"),
								// 検索条件として受け取った性別
								gender));
			}

			// 役職が選択されている場合だけ条件を追加する
			if (positionId != null) {
				predicates.add(
						cb.equal(
								// StaffからHospitalPositionをたどって、役職IDを取得する
								root.join("hospitalPosition").get("positionId"),
								positionId));
			}

			// 権限が選択されている場合だけ条件を追加する
			if (roleId != null) {
				predicates.add(
						cb.equal(
								// StaffからHospitalRoleをたどって、権限IDを取得する
								root.join("hospitalRole").get("roleId"),
								roleId));
			}

			// 登録状況が選択されている場合だけ条件を追加する
			if (isDeleted != null) {
				predicates.add(
						cb.equal(
								// Staff自身が持つ削除フラグを取得する
								root.get("isDeleted"),
								// 検索条件として受け取った削除フラグ
								isDeleted));
			}

			// 追加したすべての条件をANDで結合する
			// すべての条件を満たす職員が検索対象となる
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}

	// マイページに表示する職員情報を取得する
	// JPQLを使って職員情報を検索する
	// JOIN FETCHで役職と権限の情報も同時に取得する
	// 職員IDが一致する職員を検索する
	@Query("""
			SELECT s FROM Staff s
			JOIN FETCH s.hospitalPosition
			JOIN FETCH s.hospitalRole
			WHERE s.staffId = :staffId
			""")
	// 検索結果が存在しない場合も考慮して職員情報を受け取る
	Optional<Staff> findMyPageByStaffId(@Param("staffId") Integer staffId);

	// 職員情報確認画面に表示する職員情報を取得する
	// 職員IDと病院IDを使って職員情報を取得する
	// JOIN FETCHで役職と権限の情報も同時に取得する
	@Query("""
			SELECT s FROM Staff s
			JOIN FETCH s.hospitalPosition
			JOIN FETCH s.hospitalRole
			WHERE s.staffId = :staffId
			AND s.hospital.hospitalId = :hospitalId
			""")
	// 検索結果が存在しない場合も考慮して職員情報を受け取る
	Optional<Staff> findStaffDetailByStaffId(
			@Param("staffId") Integer staffId,
			@Param("hospitalId") Integer hospitalId);

	// メールアドレスが既に登録されているか確認する
	boolean existsByEmailAddress(String emailAddress);
}
