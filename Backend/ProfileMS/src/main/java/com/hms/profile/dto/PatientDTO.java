package com.hms.profile.dto;

import com.hms.profile.entity.Patient;
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



    public Patient toEntity(){
        return new Patient(this.id, this.name, this.email, this.address, this.phone, this.dob, this.identificationNumber, this.gender, this.bloodGroup, this.allergies, this.chronicDisease);
    }
}

