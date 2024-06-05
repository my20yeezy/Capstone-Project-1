package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.dto.UserDto;
import com.pet_projects.school_management_system.models.SomeUser;

public interface UserService {

//    void saveUser(RegistrationDto registrationDto);
    void saveUser(UserDto userDto);

    SomeUser findByEmail(String email);

}
