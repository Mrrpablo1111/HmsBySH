package com.hms.user.clients;

import com.hms.user.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "ProfileMS", configuration = FeignClientsConfiguration.class)
public interface ProfileClient  {

   @PostMapping("/profile/doctor/add")
    Long addDoctor(@RequestBody UserDTO userDTO);

   @PostMapping("/profile/patient/add")
    Long addPatient(@RequestBody UserDTO userDTO);
}
