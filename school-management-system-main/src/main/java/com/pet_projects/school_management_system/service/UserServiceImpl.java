package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.dto.RegistrationDto;
import com.pet_projects.school_management_system.models.Role;
import com.pet_projects.school_management_system.models.User;
import com.pet_projects.school_management_system.repository.RoleRepository;
import com.pet_projects.school_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    @Override
    public void saveUser(RegistrationDto registrationDto) {

        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findByName("TEACHER");
        user.setRoles(Arrays.asList(role));
        // todo check username identity at repo
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
