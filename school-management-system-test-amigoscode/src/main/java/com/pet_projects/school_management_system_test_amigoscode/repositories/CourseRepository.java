package com.pet_projects.school_management_system_test_amigoscode.repositories;

import com.pet_projects.school_management_system_test_amigoscode.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
