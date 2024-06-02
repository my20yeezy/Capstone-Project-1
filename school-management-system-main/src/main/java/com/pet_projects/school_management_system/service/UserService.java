package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.dto.RegistrationDto;
import com.pet_projects.school_management_system.models.User;

public interface UserService {

    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);

}