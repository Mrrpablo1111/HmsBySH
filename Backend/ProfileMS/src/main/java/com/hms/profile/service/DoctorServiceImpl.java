package com.hms.profile.service;

import com.hms.profile.dto.DoctorDTO;
import com.hms.profile.exception.HmsException;
import com.hms.profile.repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Long addDoctor(DoctorDTO doctorDTO) throws HmsException {
        if(doctorDTO.getEmail()!=null && doctorRepository.findByEmail(doctorDTO.getEmail()).isPresent())throw new HmsException("DOCTOR_ALREADY_EXISTS");
        if(doctorDTO.getLicenseNo()!=null && doctorRepository.findByLicenseNo(doctorDTO.getLicenseNo()).isPresent())throw new HmsException("DOCTOR_ALREADY_EXISTS");
        return  doctorRepository.save(doctorDTO.toEntity()).getId();
    }

    @Override
    public DoctorDTO getDoctorById(Long id) throws HmsException {
        return doctorRepository.findById(id).orElseThrow(()->new HmsException(("DOCTOR_NOT_FOUND"))).toDTO();
    }

    @Override
    public DoctorDTO updateDoctor(DoctorDTO doctorDTO) throws HmsException {
        doctorRepository.findById(doctorDTO.getId()).orElseThrow(()->new HmsException("DOCTOR_NOT_FOUND")).toDTO();
        return doctorRepository.save(doctorDTO.toEntity()).toDTO();
    }
}
