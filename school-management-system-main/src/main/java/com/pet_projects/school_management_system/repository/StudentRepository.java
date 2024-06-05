package com.pet_projects.school_management_system.repository;

import com.pet_projects.school_management_system.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmail(String email);

    Student findFirstByEmail(String email);
}
