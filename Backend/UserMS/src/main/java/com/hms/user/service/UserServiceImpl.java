package com.hms.user.service;

import com.hms.user.dto.UserDTO;
import com.hms.user.entity.User;

import com.hms.user.exception.HmsException;


import com.hms.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service("userService" )
@Transactional
public class UserServiceImpl implements UserService  {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApiService apiService;

    @Override
    public void registerUser(UserDTO userDTO) throws HmsException {
       Optional<User> opt = userRepository.findByEmail(userDTO.getEmail());
       if (opt.isPresent()){
           throw new HmsException("USER_ALREADY_EXISTS");
       }
       userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
       Long profileId = apiService.addProfile(userDTO).block();
       System.out.println(profileId);
       userDTO.setProfileId(profileId);
       userRepository.save(userDTO.toEntity());
    }

    @Override
    public UserDTO loginUser(UserDTO userDTO) throws HmsException {
        User user = userRepository.findByEmail(userDTO.getEmail()).orElseThrow(()->new HmsException("USER_NOT_FOUND"));
        if(!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())){
            throw new HmsException("INVALID_CREDENTIALS");
        }
        user.setPassword(null);
        return user.toDTO();
    }

    @Override
    public UserDTO getUserById(Long id) throws HmsException {
        return userRepository.findById(id).orElseThrow(()-> new HmsException("USER_NOT_FOUND")).toDTO();
    }

    @Override
    public void updateUser(UserDTO userDTO) throws HmsException{
        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new HmsException("USER_NOT_FOUND"));

        // Encode new password if it's being updated
        if (userDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        // Update other fields (e.g., name, email)
        user.setName(userDTO.getName());
        userRepository.save(user);

    }

    @Override
    public UserDTO getUser(String email) throws HmsException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new HmsException("USER_NOT_FOUND"));

        System.out.println("[DEBUG] Retrieved password: " + user.getPassword());
        return user.toDTO();
    }
}
