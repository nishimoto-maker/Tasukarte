
package com.tasukarte.staff.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasukarte.staff.entity.Staff;

public interface StaffRepository
        extends JpaRepository<Staff, Integer> {

    // 職員IDと病院IDから職員を検索
    Optional<Staff> findByStaffIdAndHospital_HospitalId(
            Integer staffId,
            Integer hospitalId
    );
}
