package com.pet_projects.school_management_system.repository;

import com.pet_projects.school_management_system.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
