package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.dto.CourseDto;
import com.pet_projects.school_management_system.models.Course;

import java.util.List;

public interface CourseService {

    List<CourseDto> findAllCourses();

    Course saveCourse(CourseDto courseDto);

    CourseDto findCourseById(long id);

    void updateCourse(CourseDto course);

}
