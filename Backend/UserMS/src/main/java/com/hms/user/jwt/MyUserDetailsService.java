package com.hms.user.jwt;

import com.hms.user.dto.UserDTO;
import com.hms.user.exception.HmsException;
import com.hms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            UserDTO dto = userService.getUser(email);

            // Critical debug output
            System.out.println("[DEBUG] Password retrieved from DB: " + dto.getPassword());
            System.out.println("[DEBUG] Password length: " + (dto.getPassword() != null ? dto.getPassword().length() : "null"));

            return new CustomUserDetails(
                    dto.getId(),
                    dto.getEmail(),  // this will be set as username
                    dto.getEmail(),  // this will be set as email
                    dto.getPassword(),
                    dto.getRole(),
                    dto.getProfileId(),
                    dto.getName(), null);

        } catch (HmsException e) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}