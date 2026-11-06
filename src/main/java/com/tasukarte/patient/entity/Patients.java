
package com.tasukarte.patient.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "patients")
public class Patients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "hospital_id", nullable = false)
    private Integer hospitalId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "kata", nullable = false, length = 255)
    private String kata;

    @Column(name = "birth_day", nullable = false)
    private LocalDate birthDay;

    @Column(name = "gender", nullable = false)
    private Short gender;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "email_address", length = 255)
    private String emailAddress;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "post_code", nullable = false, length = 7)
    private String postCode;

    @Column(name = "blood_type", length = 2)
    private String bloodType;

    @Column(name = "rh")
    private Boolean rh;

    @Column(name = "allergy", nullable = false)
    private Boolean allergy;

    @Column(name = "weight", precision = 5, scale = 2)
    private BigDecimal weight;

    @Column(name = "height", precision = 5, scale = 2)
    private BigDecimal height;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

}
