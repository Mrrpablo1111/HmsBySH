package com.hms.appointment.dto;


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


}
