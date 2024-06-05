package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.dto.UserDto;
import com.pet_projects.school_management_system.models.Role;
import com.pet_projects.school_management_system.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void setRole(UserDto userDto, String roleString) {
//        Role role = new Role(userDto.getRole());
//
//        role.setName(roleRepository.findByName(roleString).toString());
//        userDto.setRole(roleString);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
