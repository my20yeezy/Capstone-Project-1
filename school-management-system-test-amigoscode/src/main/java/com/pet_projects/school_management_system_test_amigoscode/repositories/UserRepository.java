package com.pet_projects.school_management_system_test_amigoscode.repositories;

import com.pet_projects.school_management_system_test_amigoscode.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
