package com.pet_projects.school_management_system_test_amigoscode.services;

import com.pet_projects.school_management_system_test_amigoscode.models.Course;
import com.pet_projects.school_management_system_test_amigoscode.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
