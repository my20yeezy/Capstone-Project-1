package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.dto.UserDto;
import com.pet_projects.school_management_system.models.Role;

public interface RoleService {
    void setRole(UserDto user, String roleString);

    Role findByName(String name);
}
