package com.tasukarte.common.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasukarte.staff.entity.Staff;
import com.tasukarte.staff.entity.StaffPassword;
import com.tasukarte.staff.repository.StaffPasswordRepository;
import com.tasukarte.staff.repository.StaffRepository;

import lombok.RequiredArgsConstructor;

//spring securityから自動で呼び出され、職員IDでDBを検索し、LoginUserを生成
@Service
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {

    private final StaffRepository staffRepository;
    private final StaffPasswordRepository staffPasswordRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // ① 職員IDをIntegerに変換
        Integer staffId;

        try {
            staffId = Integer.valueOf(username);
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("職員が存在しません。");
        }

        // ② 職員情報を取得
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("職員が存在しません。"));

        // ③ 削除済み職員はログイン不可
        if (staff.getIsDeleted()) {
            throw new UsernameNotFoundException("職員が存在しません。");
        }

        // ④ 所属病院を確認
        if (staff.getHospital() == null ||
                staff.getHospital().isDeleted()) {
            throw new UsernameNotFoundException("職員が存在しません。");
        }

        // ⑤ パスワード情報を取得
        StaffPassword staffPassword =
                staffPasswordRepository.findById(staffId)
                        .orElseThrow(() ->
                                new UsernameNotFoundException("職員が存在しません。"));

        // ⑥ LoginUserを作成
        return new LoginUser(
                staff.getStaffId(),
                staff.getNameKanji(),

                staff.getHospital().getHospitalId(),
                staff.getHospital().getHospitalName(),

                staff.getHospitalPosition().getPositionId(),
                staff.getHospitalPosition().getPositionName(),

                staff.getHospitalRole().getRoleId(),
                staff.getHospitalRole().getRoleName(),

                staffPassword.getPasswordHash()
        );
    }
}