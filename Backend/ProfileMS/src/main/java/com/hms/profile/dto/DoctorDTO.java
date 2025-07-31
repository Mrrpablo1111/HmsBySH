package com.hms.profile.dto;

import com.hms.profile.entity.Doctor;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private LocalDate dob;
    private String gender;
    private String licenseNo;
    private String specialization;
    private String department;
    private Integer totalExp;

    public Doctor toEntity(){
        return new Doctor(this.id, this.name, this.email, this.address, this.phone, this.dob, this.gender, this.licenseNo, this.specialization, this.department, this.totalExp);
    }
}
