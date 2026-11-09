
package com.tasukarte.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasukarte.staff.entity.Staff;
import com.tasukarte.staff.entity.StaffPassword;
import com.tasukarte.staff.repository.StaffPasswordRepository;
import com.tasukarte.staff.repository.StaffRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final StaffRepository staffRepository;

    private final StaffPasswordRepository staffPasswordRepository;

    @Transactional(readOnly = true)
    public Staff login(Integer staffId, String password) {

        // ① メールアドレスから職員を検索
        Staff staff = staffRepository
                .findById(staffId)
                .orElse(null);

        // 職員が存在しない場合
        if (staff == null) {
            return null;
        }

        // ② 職員の削除フラグを確認
        if (staff.getIsDeleted()) {
            return null;
        }

        // ③ 職員IDからパスワード情報を取得
        StaffPassword staffPassword =
                staffPasswordRepository
                        .findById(staff.getStaffId())
                        .orElse(null);

        // パスワード情報が存在しない場合
        if (staffPassword == null) {
            return null;
        }

        // ④ パスワードを照合（現在は平文でのテスト用）
        if (password == null ||
                !password.equals(staffPassword.getPasswordHash())) {
            return null;
        }

        // ⑤ 所属病院の削除フラグを確認
        if (staff.getHospital() == null ||
                staff.getHospital().isDeleted()) {
            return null;
        }

        // 認証成功
        return staff;
    }
}
