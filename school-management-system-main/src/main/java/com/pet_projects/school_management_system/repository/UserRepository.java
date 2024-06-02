package com.pet_projects.school_management_system.repository;

import com.pet_projects.school_management_system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findFirstByEmail(String email);
}