package com.hms.appointment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private LocalDate dob;
    private String identificationNumber;
    private String gender;
    private BloodGroupDTO bloodGroup;
    private String allergies;
    private String chronicDisease;

}

