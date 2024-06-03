package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.dto.RegistrationDto;
import com.pet_projects.school_management_system.models.Role;

public interface RoleService {
    void setRole(RegistrationDto user, String roleString);

    Role findByName(String name);
}
