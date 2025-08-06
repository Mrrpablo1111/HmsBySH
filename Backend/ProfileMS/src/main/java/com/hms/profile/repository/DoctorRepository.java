package com.hms.profile.repository;

import com.hms.profile.dto.DoctorDropdown;
import com.hms.profile.entity.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends CrudRepository<Doctor, Long > {
    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findByLicenseNo(String licenseNo);

    @Query("SELECT d.id AS id, CONCAT(d.name,' ', d.name) AS name FROM Doctor d")
    List<DoctorDropdown> findAllDoctorDropdowns();
}
