package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.dto.RegistrationDto;

public interface RoleService {
    void setRole(RegistrationDto user, String roleString);
}
