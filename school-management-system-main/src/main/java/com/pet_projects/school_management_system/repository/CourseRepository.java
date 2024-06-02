package com.pet_projects.school_management_system.repository;

import com.pet_projects.school_management_system.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByTeacher(String teacher);

    Optional<Course> findByName(String name);

}
