package com.hms.profile.entity;


import com.hms.profile.dto.BloodGroupDTO;
import com.hms.profile.dto.DoctorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String address;
    private String phone;
    private LocalDate dob;
    @Column(unique = true)
    private String gender;
    private String licenseNo;
    private String specialization;
    private String department;
    private Integer totalExp;

    public DoctorDTO toDTO(){
        return new DoctorDTO(this.id, this.name, this.email, this.address, this.phone, this.dob, this.gender, this.licenseNo, this.specialization, this.department, this.totalExp);
    }

}
