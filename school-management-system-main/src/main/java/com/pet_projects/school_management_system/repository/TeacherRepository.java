package com.pet_projects.school_management_system.repository;

import com.pet_projects.school_management_system.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByEmail(String email);

    Teacher findFirstByEmail(String email);
}
