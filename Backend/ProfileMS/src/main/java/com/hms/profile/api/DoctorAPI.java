package com.hms.profile.api;

import com.hms.profile.dto.DoctorDTO;
import com.hms.profile.dto.DoctorDropdown;
import com.hms.profile.exception.HmsException;
import com.hms.profile.service.DoctorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/profile/doctor")
public class DoctorAPI {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity<Long> addDoctor(@RequestBody DoctorDTO doctorDTO) throws HmsException{
        return new ResponseEntity<>(doctorService.addDoctor(doctorDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) throws HmsException{
        return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<DoctorDTO> updateDoctor(@RequestBody DoctorDTO doctorDTO) throws HmsException{
        return new ResponseEntity<>(doctorService.updateDoctor(doctorDTO), HttpStatus.OK);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> doctorExists(@PathVariable Long id) throws HmsException{
        return new ResponseEntity<>(doctorService.doctorExists(id), HttpStatus.OK);
    }

    @GetMapping("/dropdowns")
    public ResponseEntity<List<DoctorDropdown>> getDoctorDropdown() throws  HmsException{
        return new ResponseEntity<>(doctorService.getDoctorDropdown(), HttpStatus.OK);
    }
}
