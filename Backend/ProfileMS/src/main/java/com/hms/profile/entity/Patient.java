package com.hms.profile.entity;



import com.hms.profile.dto.BloodGroupDTO;
import com.hms.profile.dto.PatientDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Patient {
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
    private String identificationNumber;
    private String gender;
    private BloodGroupDTO bloodGroup;
    private String allergies;
    private String chronicDisease;


    public PatientDTO toDTO(){
        return new PatientDTO(this.id, this.name, this.email, this.address, this.phone, this.dob, this.identificationNumber,this.gender, this.bloodGroup, this.allergies, this.chronicDisease);
    }
}
