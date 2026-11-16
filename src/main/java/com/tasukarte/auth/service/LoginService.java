
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
    public Staff login(Integer userId, Integer hospitalId, String password) {

        // ① ユーザーIDと病院IDから職員を検索
        Staff staff = staffRepository
                .findByStaffIdAndHospital_HospitalId(userId, hospitalId)
                .orElse(null);

        // 職員が存在しない場合
        if (staff == null) {
            return null;
        }

        // ② 職員の削除フラグを確認
        if (staff.getIsDeleted()) {
            return null;
        }

        // ③ 所属病院の削除フラグを確認
        if (staff.getHospital() == null ||
                staff.getHospital().isDeleted()) {
            return null;
        }

        // ④ 職員IDからパスワード情報を取得
        StaffPassword staffPassword = staffPasswordRepository
                .findById(staff.getStaffId())
                .orElse(null);

        // パスワード情報が存在しない場合
        if (staffPassword == null) {
            return null;
        }

        // ⑤ パスワードを照合
        if (password == null ||
                !password.equals(staffPassword.getPasswordHash())) {
            return null;
        }

        // 認証成功
        return staff;
    }
}
