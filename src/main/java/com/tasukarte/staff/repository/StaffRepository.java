package com.tasukarte.staff.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tasukarte.staff.entity.Staff;

import jakarta.persistence.criteria.Predicate;

// staffsテーブルの操作を行うinterface
public interface StaffRepository 
		extends JpaRepository<Staff, Integer>,
		JpaSpecificationExecutor<Staff> {
	
	static Specification<Staff> search(
	        Integer hospitalId,
	        String name,
	        Short gender,
	        Integer positionId,
	        Integer roleId,
	        Boolean isDeleted) {
		
		// root 検索対象のStaffの情報を参照する
		// query 検索全体の設定を扱う
		// cb 検索条件を作るための道具
	    return (root, query, cb) -> {
	    	
	    	// 検索条件を複数保存するリスト
            List<Predicate> predicates = new ArrayList<>();
	    	
	    	// cb.equal() 左右の値が等しいかどうかを判定する検索条件を作成する
	    	// 病院IDが一致する条件
            // add() リストに要素を追加するメソッド
	    	predicates.add(
			    cb.equal(
			    	// Staffに関連付けられているHospitalの病院IDを取得する
			    	root.get("hospital").get("hospitalId"),
			    	// 検索条件として受け取った病院ID
			    	hospitalId
			    )
		    );
		    	
	    	// 漢字氏名またはカナ氏名のどちらかが部分一致する条件
            predicates.add(
                cb.or(
                    // 漢字氏名を部分一致で検索
                    cb.like(root.get("nameKanji"), "%" + name + "%"),
                    // カナ氏名を部分一致で検索
                    cb.like(root.get("nameKana"), "%" + name + "%")
                )
            );
            
            // 性別が選択されている場合だけ条件を追加する
            if (gender != null) {
                predicates.add(
                    cb.equal(
                        // Staff自身が持つ性別を取得する
                        root.get("gender"),
                        // 検索条件として受け取った性別
                        gender
                    )
                );
            }
            
            // 役職が選択されている場合だけ条件を追加する
            if (positionId != null) {
                predicates.add(
                    cb.equal(
                    	// StaffからHospitalPositionをたどって、役職IDを取得する
                        root.join("hospitalPosition").get("positionId"),
                        positionId
                    )
                );
            }
            
            // 権限が選択されている場合だけ条件を追加する
            if (roleId != null) {
                predicates.add(
                    cb.equal(
                    	// StaffからHospitalRoleをたどって、権限IDを取得する
                        root.join("hospitalRole").get("roleId"),
                        roleId
                    )
                );
            }
            
            // 登録状況が選択されている場合だけ条件を追加する
            if (isDeleted != null) {
                predicates.add(
                    cb.equal(
                        // Staff自身が持つ削除フラグを取得する
                        root.get("isDeleted"),
                        // 検索条件として受け取った削除フラグ
                        isDeleted
                    )
                );
            }

            // 追加したすべての条件をANDで結合して返す
            return cb.and(predicates.toArray(new Predicate[0]));
	    };
	}
}
